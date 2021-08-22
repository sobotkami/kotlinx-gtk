package nativex.gdk

import glib.gpointer
import glib.guintVar
import gobject.GCallback
import gobject.GValue
import gtk.*
import kotlinx.cinterop.*
import nativex.Closeable
import nativex.async.staticBooleanCallback
import nativex.async.staticCStringCallback
import nativex.gdk.AppLaunchContext.Companion.wrap
import nativex.gdk.Clipboard.Companion.wrap
import nativex.gdk.Seat.Companion.wrap
import nativex.gdk.wayland.Monitor
import nativex.gdk.wayland.Monitor.Companion.wrap
import nativex.gio.ListModel
import nativex.gio.ListModel.Companion.wrap
import nativex.glib.*
import nativex.gobject.KGObject
import nativex.gobject.KGValue
import nativex.gobject.KGValue.Companion.wrap
import nativex.gobject.Signals
import nativex.gobject.addSignalCallback

/**
 * @see <a href="https://docs.gtk.org/gdk4/class.Display.html">GdkDisplay</a>
 */
class Display(
	val displayPointer: CPointer<GdkDisplay>
) : KGObject(displayPointer.reinterpret()), Closeable {

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.beep.html"></a>
	 */
	fun beep() {
		gdk_display_beep(displayPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.close.html"></a>
	 */
	override fun close() {
		gdk_display_close(displayPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.device_is_grabbed.html"></a>
	 */
	fun isDeviceGrabbed(device: Device): Boolean =
		gdk_display_device_is_grabbed(displayPointer, device.pointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.flush.html"></a>
	 */
	fun flush() {
		gdk_display_flush(displayPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.get_clipboard.html"></a>
	 */
	val appLaunchContext: AppLaunchContext
		get() = gdk_display_get_app_launch_context(displayPointer)!!.wrap()

	/**
	 * @see <a href=""></a>
	 */
	val clipboard: Clipboard
		get() = gdk_display_get_clipboard(displayPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.get_default_seat.html"></a>
	 */
	val defaultSeat: Seat
		get() = gdk_display_get_default_seat(displayPointer)!!.wrap()

	/**
	 * @see <a href=""></a>
	 */
	fun getMonitorAtSurface(surface: Surface): Monitor =
		gdk_display_get_monitor_at_surface(displayPointer, surface.surfacePointer)!!.wrap()

	/**
	 * @see <a href=""></a>
	 */
	val monitors: ListModel
		get() = gdk_display_get_monitors(displayPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gdk3/stable/GdkDisplay.html#gdk-display-get-name">
	 *     gdk_display_get_name</a>
	 */
	val name: String
		get() = gdk_display_get_name(displayPointer)!!.toKString()

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.get_primary_clipboard.html"></a>
	 */
	val primaryClipboard: Clipboard
		get() = gdk_display_get_primary_clipboard(displayPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.get_setting.html"></a>
	 */
	fun getSetting(name: String): KGValue? = memScoped {
		val value = cValue<GValue>()
		if (gdk_display_get_setting(displayPointer, name, value).bool)
			value.ptr.wrap()
		else null
	}

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.get_startup_notification_id.html"></a>
	 */
	val startupNotificationId: String?
		get() = gdk_display_get_startup_notification_id(displayPointer)?.toKString()

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.is_closed.html"></a>
	 */
	val isClosed: Boolean
		get() = gdk_display_is_closed(displayPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.is_composited.html"></a>
	 */
	val isComposited: Boolean
		get() = gdk_display_is_composited(displayPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.is_rgba.html"></a>
	 */
	val isRGBA: Boolean
		get() = gdk_display_is_rgba(displayPointer).bool


	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.list_seats.html"></a>
	 */
	val seats: Sequence<Seat>
		get() = gdk_display_list_seats(displayPointer).asKSequence<GdkSeat, Seat> {
			it.wrap()
		}

	data class MappedKeycodeValues(
		val array: List<KeymapKey>,
		val keyvals: List<UInt>
	)

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.map_keycode.html"></a>
	 */
	fun mapKeycode(keycode: UInt): MappedKeycodeValues? = memScoped {
		val keys = cValue<CPointerVar<GdkKeymapKey>>()
		val keyvals = cValue<CPointerVar<guintVar>>()
		val nEntries = cValue<IntVar>()

		if (gdk_display_map_keycode(displayPointer, keycode, keys, keyvals, nEntries).bool) {
			val size = nEntries.ptr.pointed.value
			MappedKeycodeValues(
				keys.ptr.toWrappedList(size) { KeymapKey(it) },
				keyvals.ptr.toList(size)
			)
		} else
			null
	}

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.map_keyval.html"></a>
	 */
	fun mapKeyval(keyval: UInt): List<KeymapKey> = memScoped {
		val keys = cValue<CPointerVar<GdkKeymapKey>>()
		val nKeys = cValue<IntVar>()

		return if (gdk_display_map_keyval(displayPointer, keyval, keys, nKeys).bool) {
			val size = nKeys.ptr.pointed.value
			val r = keys.ptr.toWrappedList(size) { KeymapKey(it) }
			keys.ptr.free()
			r
		} else listOf()
	}

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.notify_startup_complete.html"></a>
	 */
	fun notifyStartupComplete(startupId: String) {
		gdk_display_notify_startup_complete(displayPointer, startupId)
	}

	// @Throws(KGError::class) fun prepareGL(): Boolean{}
	// Documentation specifies that this function never needs to be called

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.put_event.html"></a>
	 */
	fun putEvent(event: Event) =
		gdk_display_put_event(displayPointer, event.eventPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.supports_input_shapes.html"></a>
	 */
	val supportsInputShapes: Boolean
		get() = gdk_display_supports_input_shapes(displayPointer).bool


	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.sync.html"></a>
	 */
	fun sync() {
		gdk_display_sync(displayPointer)
	}

	data class TranslatedKey(
		val keyval: UInt,
		val effectiveGroup: Int,
		val level: Int,
		val consumed: ModifierType
	)

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/method.Display.translate_key.html"></a>
	 */
	fun translateKey(keycode: UInt, modifierType: ModifierType, group: Int): TranslatedKey? =
		memScoped {
			val keyval = cValue<guintVar>()
			val effectiveGroup = cValue<IntVar>()
			val level = cValue<IntVar>()
			val consumed = cValue<GdkModifierTypeVar>()
			if (gdk_display_translate_key(
					displayPointer,
					keycode,
					modifierType.gdk,
					group,
					keyval,
					effectiveGroup,
					level,
					consumed
				).bool
			) {
				TranslatedKey(
					keyval.ptr.pointed.value,
					effectiveGroup.ptr.pointed.value,
					level.ptr.pointed.value,
					ModifierType.valueOf(consumed.ptr.pointed.value)!!
				)
			} else null
		}

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/signal.Display.closed.html"></a>
	 */
	fun addOnClosedCallback(action: (@ParameterName("isError") Boolean) -> Unit) =
		addSignalCallback(Signals.CLOSED, action, staticBooleanCallback)

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/signal.Display.opened.html"></a>
	 */
	fun addOnOpenedCallback(action: () -> Unit) =
		addSignalCallback(Signals.OPENED, action)

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/signal.Display.seat-added.html"></a>
	 */
	fun addOnSeatAddedCallback(action: (Seat) -> Unit) =
		addSignalCallback(Signals.SEAT_ADDED, action, staticSeatCallback)

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/signal.Display.seat-removed.html"></a>
	 */
	fun addOnSeatRemovedCallback(action: (Seat) -> Unit) =
		addSignalCallback(Signals.SEAT_REMOVED, action, staticSeatCallback)

	/**
	 * @see <a href="https://docs.gtk.org/gdk4/signal.Display.setting-changed.html"></a>
	 */
	fun addOnSettingChangedCallback(action: (@ParameterName("setting") String) -> Unit) =
		addSignalCallback(Signals.SETTING_CHANGED, action, staticCStringCallback)

	companion object {
		val staticSeatCallback: GCallback =
			staticCFunction { _: gpointer?, arg1: CPointer<GdkSeat>, data: gpointer? ->
				data?.asStableRef<(Seat) -> Unit>()
					?.get()
					?.invoke(arg1.wrap())
				Unit
			}.reinterpret()

		/**
		 * @see <a href="https://developer.gnome.org/gdk3/stable/GdkDisplay.html#gdk-display-get-default>
		 *     gdk_display_get_default</a>
		 */
		val default: Display?
			get() = gdk_display_get_default().wrap()

		inline fun CPointer<GdkDisplay>?.wrap() =
			this?.wrap()

		inline fun CPointer<GdkDisplay>.wrap() =
			Display(this)
	}
}