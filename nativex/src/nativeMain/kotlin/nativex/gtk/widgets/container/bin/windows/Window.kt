package nativex.gtk.widgets.container.bin.windows

import gtk.*
import gtk.GtkWindowPosition.*
import gtk.GtkWindowType.GTK_WINDOW_POPUP
import gtk.GtkWindowType.GTK_WINDOW_TOPLEVEL
import kotlinx.cinterop.*
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.gtk.Application
import nativex.gtk.Signals
import nativex.gtk.bool
import nativex.gtk.gtk
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

	override fun destroy() {
		TODO("")
	}

	fun setTransientFor() {
		TODO("")
	}

	fun setDisplay() {
		TODO("")
	}

	fun isActive(): Boolean {
		TODO("")
	}

	fun isMaximized(): Boolean {
		TODO("")
	}

	fun isFullScreen(): Boolean {
		TODO("")
	}

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

	fun minimize() {
		TODO("")
	}

	fun unMinimize() {
		TODO("")
	}

	fun maximize() {
		TODO("")
	}

	fun unMaximize() {
		TODO("")
	}

	fun fullScreen() {
		TODO("")
	}

	fun fullScreenOnMonitor() {
		TODO("")
	}

	fun unFullScreen() {
		TODO("")
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


	val activeDefaultSignal: Flow<*> by lazy {
		callbackSignalFlow(Signals.ACTIVATE_DEFAULT)
	}

	val activeFocusSignal: Flow<*> by lazy {
		callbackSignalFlow(Signals.ACTIVATE_FOCUS)
	}

	val closeRequestSignal: Flow<Boolean>
		get() = TODO()

	val enableDebuggingSignal: Flow<Boolean>
		get() = TODO()

	val keysChanged: Flow<*> by lazy {
		callbackSignalFlow(Signals.KEYS_CHANGED)
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