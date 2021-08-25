package org.gtk.gtk

import gtk.GtkRoot
import gtk.gtk_root_get_display
import gtk.gtk_root_get_focus
import gtk.gtk_root_set_focus
import kotlinx.cinterop.CPointer
import org.gtk.gdk.Display
import org.gtk.gdk.Display.Companion.wrap
import org.gtk.gtk.widgets.Widget
import org.gtk.gtk.widgets.Widget.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 25 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkRoot.html#GtkRoot-struct">GtkRoot</a>
 */
interface Root {
	val rootPointer: CPointer<GtkRoot>

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkRoot.html#gtk-root-get-display">gtk_root_get_display</a>
	 */
	val display: Display
		get() = gtk_root_get_display(rootPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkRoot.html#gtk-root-get-focus">gtk_root_get_focus</a>
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkRoot.html#gtk-root-set-focus">gtk_root_set_focus</a>
	 */
	var focus: Widget?
		get() = gtk_root_get_focus(rootPointer).wrap()
		set(value) = gtk_root_set_focus(rootPointer, value?.widgetPointer)


}

class ImplRoot(override val rootPointer: CPointer<GtkRoot>) : Root {
	companion object {
		inline fun CPointer<GtkRoot>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkRoot>.wrap() =
			ImplRoot(this)
	}
}