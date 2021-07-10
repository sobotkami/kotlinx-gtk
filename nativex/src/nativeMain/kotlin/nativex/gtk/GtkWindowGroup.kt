package nativex.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gdk.Device
import nativex.gmodule.KGList
import nativex.gmodule.KGList.Companion.wrapNotNull
import nativex.gobject.KObject
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.Widget.Companion.wrap
import nativex.gtk.widgets.container.bin.windows.Window

/**
 * kotlinx-gtk
 *
 * 08 / 02 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindowGroup.html">GtkWindowGroup</a>
 */
class WindowGroup(val windowGroupPointer: CPointer<GtkWindowGroup>) : KObject(windowGroupPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindowGroup.html#gtk-window-group-new">
	 *     gtk_window_group_new</a>
	 */
	constructor() : this(gtk_window_group_new()!!)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindowGroup.html#gtk-window-group-add-window">
	 *     gtk_window_group_add_window</a>
	 */
	fun addWindow(window: Window) {
		gtk_window_group_add_window(windowGroupPointer, window.windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindowGroup.html#gtk-window-group-remove-window">
	 *     gtk_window_group_remove_window</a>
	 */
	fun removeWindow(window: Window) {
		gtk_window_group_remove_window(windowGroupPointer, window.windowPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindowGroup.html#gtk-window-group-list-windows">
	 *     gtk_window_group_list_windows</a>
	 */
	val windowList: KGList<Window>
		get() = gtk_window_group_list_windows(windowGroupPointer).wrapNotNull()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindowGroup.html#gtk-window-group-get-current-grab">
	 *     gtk_window_group_get_current_grab</a>
	 */
	val currentGrab: Widget
		get() = gtk_window_group_get_current_grab(windowGroupPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWindowGroup.html#gtk-window-group-get-current-device-grab">
	 *     gtk_window_group_get_current_device_grab</a>
	 */
	fun getCurrentDeviceGrab(device: Device): Widget? =
		gtk_window_group_get_current_device_grab(windowGroupPointer, device.pointer).wrap()

	companion object{
		inline fun CPointer<GtkWindowGroup>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkWindowGroup>.wrap() =
			WindowGroup(this)
	}
}