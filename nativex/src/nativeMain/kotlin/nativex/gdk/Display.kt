package nativex.gdk

import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.Closeable
import nativex.async.signalFlow
import nativex.async.staticBooleanCallback
import nativex.gdk.Event.Companion.wrap
import nativex.gdk.Seat.Companion.wrap
import nativex.gdk.Window.Companion.wrap
import nativex.gdk.wayland.Monitor
import nativex.gdk.wayland.Monitor.Companion.wrap
import nativex.gobject.KObject
import nativex.glib.asKSequence
import nativex.glib.bool
import nativex.gobject.Signals

class Display(
	val displayPointer: CPointer<GdkDisplay>
) : KObject(displayPointer.reinterpret()), Closeable {

	/**
	 * @see <a href="https://developer.gnome.org/gdk3/stable/GdkDisplay.html#gdk-display-get-name">
	 *     gdk_display_get_name</a>
	 */
	val name: String
		get() = gdk_display_get_name(displayPointer)!!.toKString()


	fun isDeviceGrabbed(device: Device): Boolean =
		gdk_display_device_is_grabbed(displayPointer, device.pointer).bool

	fun beep() {
		gdk_display_beep(displayPointer)
	}

	fun sync() {
		gdk_display_sync(displayPointer)
	}

	fun flush() {
		gdk_display_flush(displayPointer)
	}

	val isClosed: Boolean
		get() = gdk_display_is_closed(displayPointer).bool

	val event: Event?
		get() = gdk_display_get_event(displayPointer).wrap()

	fun peekEvent(): Event? =
		gdk_display_peek_event(displayPointer).wrap()

	fun putEvent(event: Event) =
		gdk_display_put_event(displayPointer, event.eventPointer)

	val hasPending: Boolean
		get() = gdk_display_has_pending(displayPointer).bool

	/**
	 * Field will be 0 on default, setting a new value will update the field.
	 * Field getter is not tied with gtk
	 */
	var doubleClickTime: UInt = 0u
		set(value) {
			gdk_display_set_double_click_time(displayPointer, value)
			field = value
		}

	/**
	 * Field will be 0 on default, setting a new value will update the field.
	 * Field getter is not tied with gtk
	 */
	var doubleClickDistance: UInt = 0u
		set(value) {
			gdk_display_set_double_click_distance(displayPointer, value)
			field = value
		}

	val supportsCursorColor: Boolean
		get() = gdk_display_supports_cursor_color(displayPointer).bool

	val supportsCursorAlpha: Boolean
		get() = gdk_display_supports_cursor_alpha(displayPointer).bool

	val supportsCursorSize: UInt
		get() = gdk_display_get_default_cursor_size(displayPointer)

	val maximalCursorSize: Pair<UInt, UInt>
		get() = memScoped {
			val w = cValue<UIntVar>()
			val h = cValue<UIntVar>()
			gdk_display_get_maximal_cursor_size(displayPointer, w, h)
			w.ptr.pointed.value to h.ptr.pointed.value
		}

	val defaultGroup: Window
		get() = gdk_display_get_default_group(displayPointer)!!.wrap()

	val supportsSelectionNotification: Boolean
		get() = gdk_display_supports_selection_notification(displayPointer).bool

	val supportsClipboardPersistence: Boolean
		get() = gdk_display_supports_clipboard_persistence(displayPointer).bool

	val supportsShapes: Boolean
		get() = gdk_display_supports_shapes(displayPointer).bool

	val supportsInputShapes: Boolean
		get() = gdk_display_supports_input_shapes(displayPointer).bool

	fun notifyStartupComplete(startupId: String) {
		gdk_display_notify_startup_complete(displayPointer, startupId)
	}

	val defaultSeat: Seat
		get() = gdk_display_get_default_seat(displayPointer)!!.wrap()

	val seats: Sequence<Seat>
		get() = gdk_display_list_seats(displayPointer).asKSequence<GdkSeat, Seat> {
			it.wrap()
		}

	val monitorCount: Int
		get() = gdk_display_get_n_monitors(displayPointer)

	fun getMonitor(monitorNum: Int): Monitor? =
		gdk_display_get_monitor(displayPointer, monitorNum).wrap()

	val primaryMonitor: Monitor?
		get() = gdk_display_get_primary_monitor(displayPointer).wrap()

	fun getMonitor(x: Int, y: Int): Monitor =
		gdk_display_get_monitor_at_point(displayPointer, x, y)!!.wrap()

	fun getMonitor(window: Window): Monitor =
		gdk_display_get_monitor_at_window(displayPointer, window.windowPointer)!!.wrap()


	@ExperimentalCoroutinesApi
	val closedSignal: Flow<Boolean> by signalFlow(Signals.CLOSE, staticBooleanCallback)

	@ExperimentalCoroutinesApi
	val monitorAddedSignal: Flow<Monitor> by signalFlow(Signals.MONITOR_ADDED, staticMonitorCallback)

	@ExperimentalCoroutinesApi
	val monitorRemovedSignal: Flow<Monitor> by signalFlow(Signals.MONITOR_REMOVED, staticMonitorCallback)

	@ExperimentalCoroutinesApi
	val openedSignal: Flow<Unit> by signalFlow(Signals.OPENED)

	@ExperimentalCoroutinesApi
	val seatAddedSignal: Flow<Seat> by signalFlow(Signals.SEAT_ADDED, staticSeatCallback)

	@ExperimentalCoroutinesApi
	val seatRemovedSignal: Flow<Seat> by signalFlow(Signals.SEAT_REMOVED, staticSeatCallback)

	companion object {
		val staticMonitorCallback: GCallback =
			staticCFunction { _: gpointer?, arg1: CPointer<GdkMonitor>, data: gpointer? ->
				data?.asStableRef<(Monitor) -> Unit>()
					?.get()
					?.invoke(arg1.wrap())
				Unit
			}.reinterpret()

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
			this?.let { Display(it) }

		inline fun CPointer<GdkDisplay>.wrap() =
			Display(this)
	}

	override fun close() {
		gdk_display_close(displayPointer)
	}


}