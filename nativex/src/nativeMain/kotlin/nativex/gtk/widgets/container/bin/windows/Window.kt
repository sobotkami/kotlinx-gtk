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
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.signalFlow
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
import nativex.gmodule.KGList
import nativex.gmodule.KGList.Companion.wrapNotNull
import nativex.gobject.Signals
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
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html"></a>
 */
open class Window(val windowPointer: CPointer<GtkWindow>) : Bin(windowPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-new"></a>
	 */
	constructor(type: Type) : this(gtk_window_new(type.gtk)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-title"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-title"></a>
	 */
	var title: String?
		get() = gtk_window_get_title(windowPointer)?.toKString()
		set(value) {
			gtk_window_set_title(windowPointer, value)
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-resizable"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-resizable"></a>
	 */
	var isResizable: Boolean
		get() = gtk_window_get_resizable(windowPointer).bool
		set(value) = gtk_window_set_resizable(
			windowPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-modal"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-modal"></a>
	 */
	var isModal: Boolean
		get() = gtk_window_get_modal(windowPointer).bool
		set(value) = gtk_window_set_modal(
			windowPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-default-size"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-default-size"></a>
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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-size"></a>
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
	 * @see <a href=""></a>
	 * @see <a href=""></a>
	 */
	var hideOnClose: Boolean
		get() = TODO()
		set(_) = TODO()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-destroy-with-parent"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-destroy-with-parent"></a>
	 */
	var destroyWithParent: Boolean
		get() = gtk_window_get_destroy_with_parent(windowPointer).bool
		set(value) = gtk_window_set_destroy_with_parent(
			windowPointer,
			value.gtk
		)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-application"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-application"></a>
	 */
	var application: Application?
		get() = gtk_window_get_application(windowPointer).wrap()
		set(value) = gtk_window_set_application(windowPointer, value?.applicationPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-titlebar"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-titlebar"></a>
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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-focus-visible"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-focus-visible"></a>
	 */
	var isFocusVisible: Boolean
		get() = gtk_window_get_focus_visible(windowPointer).bool
		set(value) = gtk_window_set_focus_visible(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-focus"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-focus"></a>
	 */
	var focus: Widget?
		get() = gtk_window_get_focus(windowPointer).wrap()
		set(value) = gtk_window_set_focus(windowPointer, value?.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-default-widget"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-default"></a>
	 */
	var defaultWidget: Widget?
		get() = gtk_window_get_default_widget(windowPointer).wrap()
		set(value) = gtk_window_set_default(windowPointer, value?.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-decorated"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-decorated"></a>
	 */
	var decorated: Boolean
		get() = gtk_window_get_decorated(windowPointer).bool
		set(value) = gtk_window_set_decorated(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-deletable"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-deletable"></a>
	 */
	var deletable: Boolean
		get() = gtk_window_get_deletable(windowPointer).bool
		set(value) = gtk_window_set_deletable(
			windowPointer,
			value.gtk
		)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-transient-for"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-transient-for"></a>
	 */
	var transientFor: Window?
		get() = gtk_window_get_transient_for(windowPointer).wrap()
		set(value) = gtk_window_set_transient_for(windowPointer, value?.windowPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-has-group"></a>
	 */
	val hasGroup: Boolean
		get() = gtk_window_has_group(windowPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-has-toplevel-focus"></a>
	 */
	val hasTopLevelFocus: Boolean
		get() = gtk_window_has_toplevel_focus(windowPointer).bool


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-icon-name"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-icon-name"></a>
	 */
	var iconName: String?
		get() = gtk_window_get_icon_name(windowPointer)?.toKString()
		set(value) = gtk_window_set_icon_name(windowPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-mnemonics-visible"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-mnemonics-visible"></a>
	 */
	var areMnemonicsVisible: Boolean
		get() = gtk_window_get_mnemonics_visible(windowPointer).bool
		set(value) = gtk_window_set_mnemonics_visible(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-screen"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-screen"></a>
	 */
	var windowScreen: Screen
		get() = gtk_window_get_screen(windowPointer)!!.wrap()
		set(value) = gtk_window_set_screen(windowPointer, value.screenPointer)

	/**
	 * @see <a href=""></a>
	 */
	fun setTransientFor(window: Window) {
		gtk_window_set_transient_for(windowPointer, window.windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-is-active"></a>
	 */
	val isActive: Boolean
		get() = gtk_window_is_active(windowPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-is-maximized"></a>
	 */
	val isMaximized: Boolean
		get() = gtk_window_is_maximized(windowPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-add-accel-group"></a>
	 */
	fun addAccelGroup(accelGroup: AccelGroup) {
		gtk_window_add_accel_group(windowPointer, accelGroup.accelGroupPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-remove-accel-group"></a>
	 */
	fun removeAccelGroup(accelGroup: AccelGroup) {
		gtk_window_remove_accel_group(windowPointer, accelGroup.accelGroupPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-activate-focus"></a>
	 */
	fun activateFocus(): Boolean =
		gtk_window_activate_focus(windowPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-activate-default"></a>
	 */
	fun activateDefault() =
		gtk_window_activate_default(windowPointer).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-geometry-hints"></a>
	 */
	fun setGeometryHints(geometry: Geometry, geomMask: nativex.gdk.Window.Hints) {
		gtk_window_set_geometry_hints(windowPointer, null, geometry.geometryPointer, geomMask.gdk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-gravity"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-gravity"></a>
	 */
	var gravity: nativex.gdk.Window.Gravity
		get() = nativex.gdk.Window.Gravity.valueOf(gtk_window_get_gravity(windowPointer))!!
		set(value) = gtk_window_set_gravity(windowPointer, value.gdk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-position"></a>
	 */
	fun setPosition(position: Position) {
		gtk_window_set_position(windowPointer, position.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-position"></a>
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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-attached-to"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-attached-to"></a>
	 */
	var attachedTo: Widget?
		get() = gtk_window_get_attached_to(windowPointer).wrap()
		set(value) = gtk_window_set_attached_to(windowPointer, value?.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-type-hint"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-type-hint"></a>
	 */
	var typeHint: TypeHint
		get() = TypeHint.valueOf(gtk_window_get_type_hint(windowPointer))!!
		set(value) = gtk_window_set_type_hint(windowPointer, value.gdk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-hide-titlebar-when-maximized"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-hide-titlebar-when-maximized"></a>
	 */
	var hideTitleBarWhenMaximized: Boolean
		get() = gtk_window_get_hide_titlebar_when_maximized(windowPointer).bool
		set(value) = gtk_window_set_hide_titlebar_when_maximized(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-urgency-hint"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-urgency-hint"></a>
	 */
	var urgencyHint: Boolean
		get() = gtk_window_get_urgency_hint(windowPointer).bool
		set(value) = gtk_window_set_urgency_hint(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-accept-focus"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-accept-focus"></a>
	 */
	var acceptFocus: Boolean
		get() = gtk_window_get_accept_focus(windowPointer).bool
		set(value) = gtk_window_set_accept_focus(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-focus-on-map"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-focus-on-map"></a>
	 */
	var focusOnMap: Boolean
		get() = gtk_window_get_focus_on_map(windowPointer).bool
		set(value) = gtk_window_set_focus_on_map(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-skip-pager-hint"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-skip-pager-hint"></a>
	 */
	var skipPagerHint: Boolean
		get() = gtk_window_get_skip_pager_hint(windowPointer).bool
		set(value) = gtk_window_set_skip_pager_hint(windowPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-skip-taskbar-hint"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-skip-taskbar-hint"></a>
	 */
	var skipTaskbarHint: Boolean
		get() = gtk_window_get_skip_taskbar_hint(windowPointer).bool
		set(value) = gtk_window_set_skip_taskbar_hint(windowPointer, value.gtk)

	/**
	 * @see <a href=""></a>
	 */
	fun getTopLevels() {
		TODO("")
	}

	/**
	 * @see <a href=""></a>
	 */
	fun listTopLevels() {
		TODO("")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-keep-above"></a>
	 */
	fun setKeepAbove(setting: Boolean) {
		gtk_window_set_keep_above(windowPointer, setting.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-keep-below"></a>
	 */
	fun setKeepBelow(setting: Boolean) {
		gtk_window_set_keep_below(windowPointer, setting.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-present"></a>
	 */
	fun present() {
		gtk_window_present(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-present-with-time"></a>
	 */
	fun presentWithTime(timestamp: UInt) {
		gtk_window_present_with_time(windowPointer, timestamp)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-close"></a>
	 */
	fun close() {
		gtk_window_close(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-iconify"></a>
	 */
	fun iconify() {
		gtk_window_iconify(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-deiconify"></a>
	 */
	fun deiconify() {
		gtk_window_deiconify(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-stick"></a>
	 */
	fun stick() {
		gtk_window_stick(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-unstick"></a>
	 */
	fun unstick() {
		gtk_window_unstick(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-maximize"></a>
	 */
	fun maximize() {
		gtk_window_maximize(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-unmaximize"></a>
	 */
	fun unMaximize() {
		gtk_window_unmaximize(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-fullscreen"></a>
	 */
	fun fullScreen() {
		gtk_window_fullscreen(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-fullscreen-on-monitor"></a>
	 */
	fun fullScreenOnMonitor(screen: Screen, monitor: Int) {
		gtk_window_fullscreen_on_monitor(windowPointer, screen.screenPointer, monitor)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-unfullscreen"></a>
	 */
	fun unFullScreen() {
		gtk_window_unfullscreen(windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-startup-id"></a>
	 */
	fun setStartupId(startupId: String) {
		gtk_window_set_startup_id(windowPointer, startupId)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-role"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-role"></a>
	 */
	var role: String?
		get() = gtk_window_get_role(windowPointer)?.toKString()
		set(value) = gtk_window_set_role(windowPointer, value)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-begin-resize-drag"></a>
	 */
	fun beginResizeDrag(edge: Edge, button: Int, rootX: Int, rootY: Int, timestamp: UInt) {
		gtk_window_begin_resize_drag(windowPointer, edge.gdk, button, rootX, rootY, timestamp)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-begin-move-drag"></a>
	 */
	fun beginMoveDrag(button: Int, rootX: Int, rootY: Int, timestamp: UInt) {
		gtk_window_begin_move_drag(windowPointer, button, rootX, rootY, timestamp)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-move">gtk_window_move</a>
	 */
	fun move(x: Int, y: Int) {
		gtk_window_move(windowPointer, x, y)
	}

	/**
	 * @see <a href=""></a>
	 */
	fun getModal(): Boolean {
		TODO("")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-has-user-ref-count"></a>
	 */
	fun setHasUserRefCount(setting: Boolean) {
		gtk_window_set_has_user_ref_count(windowPointer, setting.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-icon"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-icon"></a>
	 */
	var icon: Pixbuf?
		get() = gtk_window_get_icon(windowPointer).wrap()
		set(icon) = gtk_window_set_icon(windowPointer, icon?.pixbufPointer)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-icon-list"></a>
	 */
	fun setIconList(list: List<Pixbuf>) {
		TODO("gtk_window_set_icon_list")
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-icon-from-file"></a>
	 */
	@Throws(KGError::class)
	fun setIconFromFile(filename: String): Boolean = memScoped {
		val err = allocPointerTo<GError>().ptr
		val r = gtk_window_set_icon_from_file(windowPointer, filename, err)
		err.unwrap()
		r.bool
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-window-type">
	 *     gtk_window_get_window_type</a>
	 */
	val type: Type
		get() = Type.valueOf(gtk_window_get_window_type(windowPointer))!!

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-mnemonic-modifier"></a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-mnemonic-modifier"></a>
	 */
	var mnemonicModifier: ModifierType
		get() = ModifierType.valueOf(gtk_window_get_mnemonic_modifier(windowPointer))!!
		set(modifierType) = gtk_window_set_mnemonic_modifier(windowPointer, modifierType.gdk)

	/**
	 * @see <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val activeDefaultSignal: Flow<Unit> by signalFlow(Signals.ACTIVATE_DEFAULT)


	/**
	 * @see <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val activeFocusSignal: Flow<Unit> by signalFlow(Signals.ACTIVATE_FOCUS)


	/**
	 * @see <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val enableDebuggingSignal: Flow<Boolean> by signalFlow(
		Signals.ENABLE_DEBUGGING,
		staticEnableDebuggingCallback
	)


	/**
	 * @see <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val keysChangedSignal: Flow<Unit> by
	signalFlow(Signals.KEYS_CHANGED)


	/**
	 * @see <a href=""></a>
	 */
	@ExperimentalCoroutinesApi
	val setFocusSignal: Flow<Unit> by
	signalFlow(
		Signals.SET_FOCUS,
		staticSetFocusCallback
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-icon-list"></a>
	 */
	val iconList: KGList<Pixbuf>
		get() = gtk_window_get_icon_list(windowPointer).wrapNotNull()

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
					?.invoke(widget?.let { Widget(it) })
				Unit
			}.reinterpret()

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-default-icon-list"></a>
		 */
		val defaultIconList: KGList<Pixbuf>
			get() = gtk_window_get_default_icon_list().wrapNotNull()

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-auto-startup-notification"></a>
		 */
		fun setAutoStartupNotification(setting: Boolean) {
			gtk_window_set_auto_startup_notification(setting.gtk)
		}

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-interactive-debugging"></a>
		 */
		fun setInteractiveDebugging(enable: Boolean) {
			gtk_window_set_interactive_debugging(enable.gtk)
		}

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-default-icon-name"></a>
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-set-default-icon-name"></a>
		 */
		var defaultIconName: String
			get() = gtk_window_get_default_icon_name()!!.toKString()
			set(value) = gtk_window_set_default_icon_name(value)

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindow.html#gtk-window-get-group"></a>
		 */
		val Window?.group: WindowGroup
			get() = gtk_window_get_group(this?.windowPointer)!!.wrap()


		inline fun CPointer<GtkWindow>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkWindow>.wrap() =
			Window(this)
	}

	/**
	 * @see <a href=""></a>
	 */
	enum class Type(val key: Int, val gtk: GtkWindowType) {
		TOP_LEVEL(0, GTK_WINDOW_TOPLEVEL),
		POPUP(1, GTK_WINDOW_POPUP);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			fun valueOf(gtk: GtkWindowType) = values().find { it.gtk == gtk }
		}
	}

	/**
	 * @see <a href=""></a>
	 */
	enum class Position(val key: Int, val gtk: GtkWindowPosition) {
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