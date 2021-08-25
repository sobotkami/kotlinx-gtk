package org.gtk.gtk.widgets.windows

import glib.gboolean
import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import org.gtk.gdk.wayland.Monitor
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.glib.MutableWrappedKList.Companion.asMutableList
import org.gtk.gobject.SignalManager
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback
import org.gtk.gtk.Application
import org.gtk.gtk.Application.Companion.wrap
import org.gtk.gtk.WindowGroup
import org.gtk.gtk.WindowGroup.Companion.wrap
import org.gtk.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 08 / 02 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html">
 *     GtkWindow</a>
 */
open class Window(val windowPointer: CPointer<GtkWindow>) : Widget(windowPointer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.Window.new.html">
	 *     gtk_window_new</a>
	 */
	constructor() : this(gtk_window_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-title">
	 *     gtk_window_get_title</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-title">
	 *     gtk_window_set_title</a>
	 */
	var title: String?
		get() = gtk_window_get_title(windowPointer)?.toKString()
		set(value) {
			gtk_window_set_title(windowPointer, value)
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-resizable">
	 *     gtk_window_get_resizable</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-resizable">
	 *     gtk_window_set_resizable</a>
	 */
	var isResizable: Boolean
		get() = gtk_window_get_resizable(windowPointer).bool
		set(value) = gtk_window_set_resizable(
			windowPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-modal">
	 *     gtk_window_get_modal</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-modal">
	 *     gtk_window_set_modal</a>
	 */
	var isModal: Boolean
		get() = gtk_window_get_modal(windowPointer).bool
		set(value) = gtk_window_set_modal(
			windowPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-default-size">
	 *     gtk_window_get_default_size</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-default-size">
	 *     gtk_window_set_default_size</a>
	 */
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

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-destroy-with-parent">
	 *     gtk_window_get_destroy_with_parent</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-destroy-with-parent">
	 *     gtk_window_set_destroy_with_parent</a>
	 */
	var destroyWithParent: Boolean
		get() = gtk_window_get_destroy_with_parent(windowPointer).bool
		set(value) = gtk_window_set_destroy_with_parent(
			windowPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-application">
	 *     gtk_window_get_application</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-application">
	 *     gtk_window_set_application</a>
	 */
	var application: Application?
		get() = gtk_window_get_application(windowPointer).wrap()
		set(value) = gtk_window_set_application(windowPointer, value?.applicationPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-titlebar">
	 *     gtk_window_get_titlebar</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-titlebar">
	 *     gtk_window_set_titlebar</a>
	 */
	var titleBar: Widget?
		get() = gtk_window_get_titlebar(windowPointer)?.wrap()
		set(value) {
			value?.widgetPointer?.let {
				gtk_window_set_titlebar(
					windowPointer,
					it
				)
			}
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-focus-visible">
	 *     gtk_window_get_focus_visible</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-focus-visible">
	 *     gtk_window_set_focus_visible</a>
	 */
	var isFocusVisible: Boolean
		get() = gtk_window_get_focus_visible(windowPointer).bool
		set(value) = gtk_window_set_focus_visible(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-focus">
	 *     gtk_window_get_focus</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-focus">
	 *     gtk_window_set_focus</a>
	 */
	var focus: Widget?
		get() = gtk_window_get_focus(windowPointer).wrap()
		set(value) = gtk_window_set_focus(windowPointer, value?.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-default-widget">gtk_window_get_default_widget</a>
	 */
	val defaultWidget: Widget?
		get() = gtk_window_get_default_widget(windowPointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-decorated">
	 *     gtk_window_get_decorated</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-decorated">
	 *     gtk_window_set_decorated</a>
	 */
	var decorated: Boolean
		get() = gtk_window_get_decorated(windowPointer).bool
		set(value) = gtk_window_set_decorated(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-deletable">
	 *     gtk_window_get_deletable</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-deletable">
	 *     gtk_window_set_deletable</a>
	 */
	var deletable: Boolean
		get() = gtk_window_get_deletable(windowPointer).bool
		set(value) = gtk_window_set_deletable(
			windowPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-transient-for">
	 *     gtk_window_get_transient_for</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-transient-for">
	 *     gtk_window_set_transient_for</a>
	 */
	var transientFor: Window?
		get() = gtk_window_get_transient_for(windowPointer).wrap()
		set(value) = gtk_window_set_transient_for(windowPointer, value?.windowPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-has-group">
	 *     gtk_window_has_group</a>
	 */
	val hasGroup: Boolean
		get() = gtk_window_has_group(windowPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-icon-name">
	 *     gtk_window_get_icon_name</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-icon-name">
	 *     gtk_window_set_icon_name</a>
	 */
	var iconName: String?
		get() = gtk_window_get_icon_name(windowPointer)?.toKString()
		set(value) = gtk_window_set_icon_name(windowPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-mnemonics-visible">
	 *     gtk_window_get_mnemonics_visible</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-mnemonics-visible">
	 *     gtk_window_set_mnemonics_visible</a>
	 */
	var areMnemonicsVisible: Boolean
		get() = gtk_window_get_mnemonics_visible(windowPointer).bool
		set(value) = gtk_window_set_mnemonics_visible(windowPointer, value.gtk)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-is-active">
	 *     gtk_window_is_active</a>
	 */
	val isActive: Boolean
		get() = gtk_window_is_active(windowPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-is-maximized">
	 *     gtk_window_is_maximized</a>
	 */
	val isMaximized: Boolean
		get() = gtk_window_is_maximized(windowPointer).bool


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-present">
	 *     gtk_window_present</a>
	 */
	fun present() {
		gtk_window_present(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-present-with-time">
	 *     gtk_window_present_with_time</a>
	 */
	fun presentWithTime(timestamp: UInt) {
		gtk_window_present_with_time(windowPointer, timestamp)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-close">
	 *     gtk_window_close</a>
	 */
	fun close() {
		gtk_window_close(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-maximize">
	 *     gtk_window_maximize</a>
	 */
	fun maximize() {
		gtk_window_maximize(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-unmaximize">
	 *     gtk_window_unmaximize</a>
	 */
	fun unMaximize() {
		gtk_window_unmaximize(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-fullscreen">
	 *     gtk_window_fullscreen</a>
	 */
	fun fullScreen() {
		gtk_window_fullscreen(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-fullscreen-on-monitor">
	 *     gtk_window_fullscreen_on_monitor</a>
	 */
	fun fullScreenOnMonitor(monitor: Monitor) {
		gtk_window_fullscreen_on_monitor(windowPointer, monitor.monitorPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-unfullscreen">
	 *     gtk_window_unfullscreen</a>
	 */
	fun unFullScreen() {
		gtk_window_unfullscreen(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-startup-id">
	 *     gtk_window_set_startup_id</a>
	 */
	fun setStartupId(startupId: String) {
		gtk_window_set_startup_id(windowPointer, startupId)
	}


	/**
	 * @see <a href=""https://developer.gnome.org/gtk3/stable/GtkWindow.html#GtkWindow-activate-default>
	 *     activate-default</a>
	 */
	fun addOnActivateDefaultCallback(action: () -> Unit): SignalManager =
		addSignalCallback(Signals.ACTIVATE_DEFAULT, StableRef.create(action).asCPointer())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#GtkWindow-activate-focus">
	 *     activate-focus</a>
	 */
	fun addOnActivateFocusCallback(action: () -> Unit): SignalManager =
		addSignalCallback(Signals.ACTIVATE_FOCUS, StableRef.create(action).asCPointer())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#GtkWindow-enable-debugging">
	 *     enable-debugging</a>
	 */
	fun addOnEnableDebuggingCallback(action: (Boolean) -> Unit): SignalManager =
		addSignalCallback(
			Signals.ENABLE_DEBUGGING,
			StableRef.create(action).asCPointer(),
			staticEnableDebuggingCallback
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#GtkWindow-keys-changed">
	 *     keys-changed</a>
	 */
	fun addOnKeysChangedCallback(action: () -> Unit): SignalManager =
		addSignalCallback(Signals.KEYS_CHANGED, StableRef.create(action).asCPointer())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#GtkWindow-set-focus">
	 *     set-focus</a>
	 */
	fun addOnSetFocusCallback(action: (Widget?) -> Unit): SignalManager =
		addSignalCallback(Signals.SET_FOCUS, action, staticSetFocusCallback)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-group">
	 *     gtk_window_get_group</a>
	 */
	val group: WindowGroup
		get() = gtk_window_get_group(windowPointer)!!.wrap()

	companion object {
		val staticEnableDebuggingCallback: GCallback =
			staticCFunction { _: gpointer?, toggle: gboolean, data: gpointer? ->
				data?.asStableRef<(Boolean) -> Unit>()?.get()
					?.invoke(toggle.bool)
				Unit
			}.reinterpret()

		val staticSetFocusCallback: GCallback =
			staticCFunction { _: gpointer?, widget: CPointer<GtkWidget>?, data: gpointer? ->
				data?.asStableRef<(Widget?) -> Unit>()?.get()
					?.invoke(widget.wrap())
				Unit
			}.reinterpret()

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-auto-startup-notification">
		 *     gtk_window_set_auto_startup_notification</a>
		 */
		fun setAutoStartupNotification(setting: Boolean) {
			gtk_window_set_auto_startup_notification(setting.gtk)
		}

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-interactive-debugging">
		 *     gtk_window_set_interactive_debugging</a>
		 */
		fun setInteractiveDebugging(enable: Boolean) {
			gtk_window_set_interactive_debugging(enable.gtk)
		}

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-default-icon-name">
		 *     gtk_window_get_default_icon_name</a>
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-default-icon-name">
		 *     gtk_window_set_default_icon_name</a>
		 */
		var defaultIconName: String
			get() = gtk_window_get_default_icon_name()!!.toKString()
			set(value) = gtk_window_set_default_icon_name(value)

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-list-toplevels">
		 *     gtk_window_list_toplevels</a>
		 */
		val topLevels: MutableList<Widget>
			get() =
				gtk_window_list_toplevels()!!
					.asMutableList(
						{ reinterpret<GtkWidget>().wrap() },
						{ pointer }
					)

		inline fun CPointer<GtkWindow>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkWindow>.wrap() =
			Window(this)
	}
}