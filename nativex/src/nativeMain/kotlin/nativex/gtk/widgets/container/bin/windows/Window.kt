package nativex.gtk.widgets.container.bin.windows

import gtk.*
import gtk.GtkWindowPosition.*
import gtk.GtkWindowType.GTK_WINDOW_POPUP
import gtk.GtkWindowType.GTK_WINDOW_TOPLEVEL
import kotlinx.cinterop.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.async.signalFlow
import nativex.gdk.Screen
import nativex.gdk.Screen.Companion.wrap
import nativex.glib.Variant
import nativex.gtk.*
import nativex.gtk.GtkWindowGroup
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.bin.Bin

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
open class Window internal constructor(
	internal val windowPointer: CPointer<GtkWindow>
) : Bin(windowPointer.reinterpret()) {

	constructor(type: Type) : this(gtk_window_new(type.gtk)!!.reinterpret())

	var title: String?
		get() = gtk_window_get_title(windowPointer)?.toKString()
		set(value) {
			gtk_window_set_title(windowPointer, value)
		}

	var isResizable: Boolean
		get() = gtk_window_get_resizable(windowPointer).bool
		set(value) = gtk_window_set_resizable(
			windowPointer,
			value.gtk
		)

	var isModal: Boolean
		get() = gtk_window_get_modal(windowPointer).bool
		set(value) = gtk_window_set_modal(
			windowPointer,
			value.gtk
		)

	var defaultSize: Pair<Int, Int>
		set(value) {
			gtk_window_set_default_size(
				windowPointer,
				value.first,
				value.second
			)
		}
		get() {
			val int1: CValue<IntVarOf<Int>> = cValue()
			val int2: CValue<IntVarOf<Int>> = cValue()

			gtk_window_get_default_size(
				window = windowPointer,
				width = int1,
				height = int2
			)

			return memScoped {
				int1.ptr.pointed.value to int2.ptr.pointed.value
			}
		}

	var hideOnClose: Boolean
		get() = TODO()
		set(_) = TODO()

	var destroyWithParent: Boolean
		get() = gtk_window_get_destroy_with_parent(windowPointer).bool
		set(value) = gtk_window_set_destroy_with_parent(
			windowPointer,
			value.gtk
		)


	var application: Application
		get() = TODO()
		set(_) = TODO()

	var titleBar: Widget?
		get() = gtk_window_get_titlebar(windowPointer)?.let { Widget(it) }
		set(value) {
			value?.widgetPointer?.let {
				gtk_window_set_titlebar(
					windowPointer,
					it
				)
			}
		}

	var isFocusVisible: Boolean
		get() = TODO()
		set(_) = TODO()
	var focus: Widget
		get() = TODO()
		set(_) = TODO()
	var defaultWidget: Widget
		get() = TODO()
		set(_) = TODO()
	var decorated: Boolean
		get() = TODO()
		set(_) = TODO()

	var deletable: Boolean
		get() = gtk_window_get_deletable(windowPointer).bool
		set(value) = gtk_window_set_deletable(
			windowPointer,
			value.gtk
		)


	var defaultIconName: String
		get() = TODO()
		set(_) = TODO()
	val transientFor: Window
		get() = TODO()

	val hasGroup: Boolean
		get() = TODO()
	var iconName: String
		get() = TODO()
		set(_) = TODO()
	val areMnemonicsVisible: Boolean
		get() = TODO()

	var windowScreen: Screen
		get() = gtk_window_get_screen(windowPointer)!!.wrap()
		set(value) = gtk_window_set_screen(windowPointer, value.screenPointer)

	fun setTransientFor(window: Window) {
		gtk_window_set_transient_for(windowPointer, window.windowPointer)
	}

	val isActive: Boolean
		get() = gtk_window_is_active(windowPointer).bool

	val isMaximized: Boolean
		get() = gtk_window_is_maximized(windowPointer).bool

	fun getTopLevels() {
		TODO("")
	}

	fun listTopLevels() {
		TODO("")
	}

	fun present() {
		TODO("")
	}

	fun presentWithTime() {
		TODO("")
	}

	fun close() {
		TODO("")
	}

	fun maximize() {
		gtk_window_maximize(windowPointer)
	}

	fun unMaximize() {
		gtk_window_unmaximize(windowPointer)
	}

	fun fullScreen() {
		gtk_window_fullscreen(windowPointer)
	}

	fun fullScreenOnMonitor(screen: Screen, monitor: Int) {
		gtk_window_fullscreen_on_monitor(windowPointer, screen.screenPointer, monitor)
	}

	fun unFullScreen() {
		gtk_window_unfullscreen(windowPointer)
	}

	fun setStartupId() {
		TODO("")
	}

	fun getModal(): Boolean {
		TODO("")
	}

	fun getGroup(): GtkWindowGroup {
		TODO("")
	}

	fun setAutoStartupNotification() {
		TODO("")
	}

	fun setInteractiveDebugging() {
		TODO("")
	}


	@ExperimentalCoroutinesApi
	val activeDefaultSignal: Flow<Unit> by signalFlow(Signals.ACTIVATE_DEFAULT)


	@ExperimentalCoroutinesApi
	val activeFocusSignal: Flow<Unit> by signalFlow(Signals.ACTIVATE_FOCUS)



	@ExperimentalCoroutinesApi
	val enableDebuggingSignal: Flow<Boolean> by signalFlow(
		Signals.ENABLE_DEBUGGING,
		staticEnableDebuggingCallback
	)



	@ExperimentalCoroutinesApi
	val keysChangedSignal: Flow<Unit> by
		signalFlow(Signals.KEYS_CHANGED)



	@ExperimentalCoroutinesApi
	val setFocusSignal: Flow<Unit> by
	signalFlow(
		Signals.SET_FOCUS,
		staticSetFocusCallback
	)


	companion object {
		internal val staticEnableDebuggingCallback: GCallback =
			staticCFunction { _: gpointer?, toggle: gboolean, data: gpointer? ->
				data?.asStableRef<(Boolean) -> Unit>()?.get()
					?.invoke(toggle.bool)
				Unit
			}.reinterpret()

		internal val staticSetFocusCallback: GCallback =
			staticCFunction { _: gpointer?, widget: CPointer<GtkWidget>?, data: gpointer? ->
				data?.asStableRef<(Widget?) -> Unit>()?.get()
					?.invoke(widget?.let { Widget(it) })
				Unit
			}.reinterpret()


		internal inline fun CPointer<GtkWindow>?.wrap() =
			this?.let { Window(it) }

		internal inline fun CPointer<GtkWindow>.wrap() =
			Window(this)
	}

	enum class Type(val key: Int, internal val gtk: GtkWindowType) {
		TOP_LEVEL(0, GTK_WINDOW_TOPLEVEL),
		POPUP(1, GTK_WINDOW_POPUP);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			fun valueOf(gtk: GtkWindowType) = values().find { it.gtk == gtk }
		}
	}

	enum class Position(val key: Int, internal val gtk: GtkWindowPosition) {
		NONE(0, GTK_WIN_POS_NONE),
		CENTER(1, GTK_WIN_POS_CENTER),
		MOUSE(2, GTK_WIN_POS_MOUSE),
		CENTER_ALWAYS(3, GTK_WIN_POS_CENTER_ALWAYS),
		CENTER_ON_PARENT(4, GTK_WIN_POS_CENTER_ON_PARENT);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			fun valueOf(gtk: GtkWindowPosition) =
				values().find { it.gtk == gtk }
		}
	}
}