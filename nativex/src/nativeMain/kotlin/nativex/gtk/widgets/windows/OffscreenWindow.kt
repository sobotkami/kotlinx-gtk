package nativex.gtk.widgets.windows

import gtk.GtkOffscreenWindow
import gtk.gtk_offscreen_window_get_pixbuf
import gtk.gtk_offscreen_window_get_surface
import gtk.gtk_offscreen_window_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.cairo.Surface
import nativex.cairo.Surface.Companion.wrap
import nativex.gdk.Pixbuf
import nativex.gdk.Pixbuf.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 08 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkOffscreenWindow.html">GtkOffscreenWindow</a>
 */
class OffscreenWindow(val offScreenWindowPointer: CPointer<GtkOffscreenWindow>) :
	Window(offScreenWindowPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkOffscreenWindow.html#gtk-offscreen-window-new">
	 *     gtk_offscreen_window_new</a>
	 */
	constructor() : this(gtk_offscreen_window_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkOffscreenWindow.html#gtk-offscreen-window-get-surface">
	 *     gtk_offscreen_window_get_surface</a>
	 */
	val surface: Surface?
		get() = gtk_offscreen_window_get_surface(offScreenWindowPointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkOffscreenWindow.html#gtk-offscreen-window-get-pixbuf">
	 *     gtk_offscreen_window_get_pixbuf</a>
	 */
	val pixbuf: Pixbuf?
		get() = gtk_offscreen_window_get_pixbuf(offScreenWindowPointer).wrap()
}