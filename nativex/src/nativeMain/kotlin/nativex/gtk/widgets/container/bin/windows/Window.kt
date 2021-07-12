package nativex.gtk.widgets.container.bin.windows

import glib.GError
import glib.gboolean
import glib.gpointer
import gobject.GCallback
import gtk.*
import gtk.GtkWindowPosition.*
import gtk.GtkWindowType.GTK_WINDOW_POPUP
import gtk.GtkWindowType.GTK_WINDOW_TOPLEVEL
import kotlinx.cinterop.*
import nativex.gdk.Event
import nativex.gdk.Geometry
import nativex.gdk.Pixbuf
import nativex.gdk.Pixbuf.Companion.wrap
import nativex.gdk.Screen
import nativex.gdk.Screen.Companion.wrap
import nativex.gdk.Window.*
import nativex.glib.KGError
import nativex.glib.bool
import nativex.glib.gtk
import nativex.glib.unwrap
import nativex.gmodule.WrappedList
import nativex.gmodule.WrappedList.Companion.asMutableList
import nativex.gobject.SignalManager
import nativex.gobject.Signals
import nativex.gobject.signalManager
import nativex.gtk.AccelGroup
import nativex.gtk.Application
import nativex.gtk.Application.Companion.wrap
import nativex.gtk.WindowGroup
import nativex.gtk.WindowGroup.Companion.wrap
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.bin.Bin

/**
 * kotlinx-gtk
 *
 * 08 / 02 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html">
 *     GtkWindow</a>
 */
open class Window(val windowPointer: CPointer<GtkWindow>) : Bin(windowPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-new">
	 *     gtk_window_new</a>
	 */
	constructor(type: Type) : this(gtk_window_new(type.gtk)!!.reinterpret())

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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-size">
	 *     gtk_window_get_size</a>
	 */
	val size: Pair<Int, Int>
		get() = memScoped {
			val int1 = cValue<IntVar>()
			val int2 = cValue<IntVar>()

			gtk_window_get_size(
				window = windowPointer,
				width = int1,
				height = int2
			)

			int1.ptr.pointed.value to int2.ptr.pointed.value
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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-default">gtk_window_set_default</a>
	 */
	var defaultWidget: Widget?
		get() = gtk_window_get_default_widget(windowPointer).wrap()
		set(value) = gtk_window_set_default(windowPointer, value?.widgetPointer)

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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-has-toplevel-focus">
	 *     gtk_window_has_toplevel_focus</a>
	 */
	val hasTopLevelFocus: Boolean
		get() = gtk_window_has_toplevel_focus(windowPointer).bool

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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-screen">
	 *     gtk_window_get_screen</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-screen">
	 *     gtk_window_set_screen</a>
	 */
	var windowScreen: Screen
		get() = gtk_window_get_screen(windowPointer)!!.wrap()
		set(value) = gtk_window_set_screen(windowPointer, value.screenPointer)

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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-add-accel-group">
	 *     gtk_window_add_accel_group</a>
	 */
	fun addAccelGroup(accelGroup: AccelGroup) {
		gtk_window_add_accel_group(windowPointer, accelGroup.accelGroupPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-remove-accel-group">
	 *     gtk_window_remove_accel_group</a>
	 */
	fun removeAccelGroup(accelGroup: AccelGroup) {
		gtk_window_remove_accel_group(windowPointer, accelGroup.accelGroupPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-activate-focus">
	 *     gtk_window_activate_focus</a>
	 */
	fun activateFocus(): Boolean =
		gtk_window_activate_focus(windowPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-activate-default">
	 *     gtk_window_activate_default</a>
	 */
	fun activateDefault() =
		gtk_window_activate_default(windowPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-geometry-hints">
	 *     gtk_window_set_geometry_hints</a>
	 */
	fun setGeometryHints(geometry: Geometry, geomMask: Hints) {
		gtk_window_set_geometry_hints(windowPointer, null, geometry.geometryPointer, geomMask.gdk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-gravity">
	 *     gtk_window_get_gravity</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-gravity">
	 *     gtk_window_set_gravity</a>
	 */
	var gravity: Gravity
		get() = Gravity.valueOf(gtk_window_get_gravity(windowPointer))!!
		set(value) = gtk_window_set_gravity(windowPointer, value.gdk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-position">
	 *     gtk_window_set_position</a>
	 */
	fun setPosition(position: Position) {
		gtk_window_set_position(windowPointer, position.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-position">
	 *     gtk_window_get_position</a>
	 */
	val position: Pair<Int, Int>
		get() = memScoped {
			val int1 = cValue<IntVar>()
			val int2 = cValue<IntVar>()

			gtk_window_get_position(
				window = windowPointer,
				root_x = int1,
				root_y = int2
			)

			int1.ptr.pointed.value to int2.ptr.pointed.value
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-attached-to">
	 *     gtk_window_get_attached_to</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-attached-to">
	 *     gtk_window_set_attached_to</a>
	 */
	var attachedTo: Widget?
		get() = gtk_window_get_attached_to(windowPointer).wrap()
		set(value) = gtk_window_set_attached_to(windowPointer, value?.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-type-hint">
	 *     gtk_window_get_type_hint</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-type-hint">
	 *     gtk_window_set_type_hint</a>
	 */
	var typeHint: TypeHint
		get() = TypeHint.valueOf(gtk_window_get_type_hint(windowPointer))!!
		set(value) = gtk_window_set_type_hint(windowPointer, value.gdk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-hide-titlebar-when-maximized">
	 *     gtk_window_get_hide_titlebar_when_maximized</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-hide-titlebar-when-maximized">
	 *     gtk_window_set_hide_titlebar_when_maximized</a>
	 */
	var hideTitleBarWhenMaximized: Boolean
		get() = gtk_window_get_hide_titlebar_when_maximized(windowPointer).bool
		set(value) = gtk_window_set_hide_titlebar_when_maximized(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-urgency-hint">
	 *     gtk_window_get_urgency_hint</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-urgency-hint">
	 *     gtk_window_set_urgency_hint</a>
	 */
	var urgencyHint: Boolean
		get() = gtk_window_get_urgency_hint(windowPointer).bool
		set(value) = gtk_window_set_urgency_hint(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-accept-focus">
	 *     gtk_window_get_accept_focus</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-accept-focus">
	 *     gtk_window_set_accept_focus</a>
	 */
	var acceptFocus: Boolean
		get() = gtk_window_get_accept_focus(windowPointer).bool
		set(value) = gtk_window_set_accept_focus(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-focus-on-map">
	 *     gtk_window_get_focus_on_map</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-focus-on-map">
	 *     gtk_window_set_focus_on_map</a>
	 */
	var focusOnMap: Boolean
		get() = gtk_window_get_focus_on_map(windowPointer).bool
		set(value) = gtk_window_set_focus_on_map(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-skip-pager-hint">
	 *     gtk_window_get_skip_pager_hint</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-skip-pager-hint">
	 *     gtk_window_set_skip_pager_hint</a>
	 */
	var skipPagerHint: Boolean
		get() = gtk_window_get_skip_pager_hint(windowPointer).bool
		set(value) = gtk_window_set_skip_pager_hint(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-skip-taskbar-hint">
	 *     gtk_window_get_skip_taskbar_hint</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-skip-taskbar-hint">
	 *     gtk_window_set_skip_taskbar_hint</a>
	 */
	var skipTaskbarHint: Boolean
		get() = gtk_window_get_skip_taskbar_hint(windowPointer).bool
		set(value) = gtk_window_set_skip_taskbar_hint(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-keep-above">
	 *     gtk_window_set_keep_above</a>
	 */
	fun setKeepAbove(setting: Boolean) {
		gtk_window_set_keep_above(windowPointer, setting.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-keep-below">
	 *     gtk_window_set_keep_below</a>
	 */
	fun setKeepBelow(setting: Boolean) {
		gtk_window_set_keep_below(windowPointer, setting.gtk)
	}

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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-iconify">
	 *     gtk_window_iconify</a>
	 */
	fun iconify() {
		gtk_window_iconify(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-deiconify">
	 *     gtk_window_deiconify</a>
	 */
	fun deiconify() {
		gtk_window_deiconify(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-stick">
	 *     gtk_window_stick</a>
	 */
	fun stick() {
		gtk_window_stick(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-unstick">
	 *     gtk_window_unstick</a>
	 */
	fun unstick() {
		gtk_window_unstick(windowPointer)
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
	fun fullScreenOnMonitor(screen: Screen, monitor: Int) {
		gtk_window_fullscreen_on_monitor(windowPointer, screen.screenPointer, monitor)
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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-role">
	 *     gtk_window_get_role</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-role">
	 *     gtk_window_set_role</a>
	 */
	var role: String?
		get() = gtk_window_get_role(windowPointer)?.toKString()
		set(value) = gtk_window_set_role(windowPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-begin-resize-drag">
	 *     gtk_window_begin_resize_drag</a>
	 */
	fun beginResizeDrag(edge: Edge, button: Int, rootX: Int, rootY: Int, timestamp: UInt) {
		gtk_window_begin_resize_drag(windowPointer, edge.gdk, button, rootX, rootY, timestamp)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-begin-move-drag">
	 *     gtk_window_begin_move_drag</a>
	 */
	fun beginMoveDrag(button: Int, rootX: Int, rootY: Int, timestamp: UInt) {
		gtk_window_begin_move_drag(windowPointer, button, rootX, rootY, timestamp)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-move">
	 *     gtk_window_move</a>
	 */
	fun move(x: Int, y: Int) {
		gtk_window_move(windowPointer, x, y)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-has-user-ref-count">
	 *     gtk_window_set_has_user_ref_count</a>
	 */
	fun setHasUserRefCount(setting: Boolean) {
		gtk_window_set_has_user_ref_count(windowPointer, setting.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-icon">
	 *     gtk_window_get_icon</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-icon">
	 *     gtk_window_set_icon</a>
	 */
	var icon: Pixbuf?
		get() = gtk_window_get_icon(windowPointer).wrap()
		set(icon) = gtk_window_set_icon(windowPointer, icon?.pixbufPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-icon-from-file">
	 *     gtk_window_set_icon_from_file</a>
	 */
	@Throws(KGError::class)
	fun setIconFromFile(filename: String): Boolean = memScoped {
		val err = allocPointerTo<GError>().ptr
		val r = gtk_window_set_icon_from_file(windowPointer, filename, err)
		err.unwrap()
		r.bool
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-resize">
	 *     gtk_window_resize</a>
	 */
	fun resize(width: Int, height: Int) {
		gtk_window_resize(windowPointer, width, height)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-window-type">
	 *     gtk_window_get_window_type</a>
	 */
	val type: Type
		get() = Type.valueOf(gtk_window_get_window_type(windowPointer))!!

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-mnemonic-modifier">
	 *     gtk_window_get_mnemonic_modifier</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-mnemonic-modifier">
	 *     gtk_window_set_mnemonic_modifier</a>
	 */
	var mnemonicModifier: ModifierType
		get() = ModifierType.valueOf(gtk_window_get_mnemonic_modifier(windowPointer))!!
		set(modifierType) = gtk_window_set_mnemonic_modifier(windowPointer, modifierType.gdk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-add-mnemonic">
	 *     gtk_window_add_mnemonic</a>
	 */
	fun addMnemonic(key: UInt, target: Widget) {
		gtk_window_add_mnemonic(windowPointer, key, target.widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-remove-mnemonic">
	 *     gtk_window_remove_mnemonic</a>
	 */
	fun removeMnemonic(key: UInt, target: Widget) {
		gtk_window_remove_mnemonic(windowPointer, key, target.widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-mnemonic-activate">
	 *     gtk_window_mnemonic_activate</a>
	 */
	fun mnemonicActivate(key: UInt, modifierType: ModifierType): Boolean =
		gtk_window_mnemonic_activate(windowPointer, key, modifierType.gdk).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-activate-key">
	 *     gtk_window_activate_key</a>
	 */
	fun activateKey(event: Event.Key) =
		gtk_window_activate_key(windowPointer, event.eventKeyPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-propagate-key-event">
	 *     gtk_window_propagate_key_event</a>
	 */
	fun propagateKeyEvent(event: Event.Key) =
		gtk_window_propagate_key_event(windowPointer, event.eventKeyPointer).bool

	/**
	 * @see <a href=""https://developer.gnome.org/gtk3/stable/GtkWindow.html#GtkWindow-activate-default>
	 *     activate-default</a>
	 */
	fun addOnActivateDefaultCallback(action: () -> Unit): SignalManager =
		signalManager(windowPointer, Signals.ACTIVATE_DEFAULT, StableRef.create(action).asCPointer())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#GtkWindow-activate-focus">
	 *     activate-focus</a>
	 */
	fun addOnActivateFocusCallback(action: () -> Unit): SignalManager =
		signalManager(windowPointer, Signals.ACTIVATE_FOCUS, StableRef.create(action).asCPointer())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#GtkWindow-enable-debugging">
	 *     enable-debugging</a>
	 */
	fun addOnEnableDebuggingCallback(action: (Boolean) -> Unit): SignalManager =
		signalManager(
			windowPointer,
			Signals.ENABLE_DEBUGGING,
			StableRef.create(action).asCPointer(),
			staticEnableDebuggingCallback
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#GtkWindow-keys-changed">
	 *     keys-changed</a>
	 */
	fun addOnKeysChangedCallback(action: () -> Unit): SignalManager =
		signalManager(windowPointer, Signals.KEYS_CHANGED, StableRef.create(action).asCPointer())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#GtkWindow-set-focus">
	 *     set-focus</a>
	 */
	fun addOnSetFocusCallback(action: (Widget?) -> Unit): SignalManager =
		signalManager(windowPointer, Signals.SET_FOCUS, StableRef.create(action).asCPointer(), staticSetFocusCallback)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-icon-list">
	 *     gtk_window_get_icon_list</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-icon-list">
	 *     gtk_window_set_icon_list</a>
	 */
	var iconList: WrappedList<Pixbuf>
		get() = gtk_window_get_icon_list(windowPointer)!!.asMutableList(
			{ reinterpret<GdkPixbuf>().wrap() },
			{ pointer }
		)
		set(value) = gtk_window_set_icon_list(windowPointer, value.kWrappedList.klist.listPointer)

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
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-default-icon-list">
		 *     gtk_window_get_default_icon_list</a>
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-default-icon-list">
		 *     gtk_window_set_default_icon_list</a>
		 */
		var defaultIconList: WrappedList<Pixbuf>
			get() = gtk_window_get_default_icon_list()!!.asMutableList(
				{ reinterpret<GdkPixbuf>().wrap() },
				{ pointer }
			)
			set(value) = gtk_window_set_default_icon_list(value.kWrappedList.klist.listPointer)

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
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-default-icon-from-file">
		 *     gtk_window_set_default_icon_from_file</a>
		 */
		fun setDefaultIconName(filename: String) {
			memScoped {
				val err = allocPointerTo<GError>().ptr
				gtk_window_set_default_icon_from_file(filename, err)
				err.unwrap()
				Unit
			}
		}

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-group">
		 *     gtk_window_get_group</a>
		 */
		val Window?.group: WindowGroup
			get() = gtk_window_get_group(this?.windowPointer)!!.wrap()

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-default-icon">
		 *     gtk_window_set_default_icon</a>
		 */
		fun setDefaultIcon(icon: Pixbuf) = gtk_window_set_default_icon(icon.pixbufPointer)

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

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#GtkWindowType">
	 *     GtkWindowType</a>
	 */
	enum class Type(val gtk: GtkWindowType) {
		TOP_LEVEL(GTK_WINDOW_TOPLEVEL),
		POPUP(GTK_WINDOW_POPUP);

		companion object {
			fun valueOf(gtk: GtkWindowType) = values().find { it.gtk == gtk }
		}
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#GtkWindowPosition">
	 *     GtkWindowPosition</a>
	 */
	enum class Position(val gtk: GtkWindowPosition) {
		NONE(GTK_WIN_POS_NONE),
		CENTER(GTK_WIN_POS_CENTER),
		MOUSE(GTK_WIN_POS_MOUSE),
		CENTER_ALWAYS(GTK_WIN_POS_CENTER_ALWAYS),
		CENTER_ON_PARENT(GTK_WIN_POS_CENTER_ON_PARENT);

		companion object {
			fun valueOf(gtk: GtkWindowPosition) =
				values().find { it.gtk == gtk }
		}
	}
}