package nativex.gtk

import gtk.GtkOrientable
import gtk.gtk_orientable_get_orientation
import gtk.gtk_orientable_set_orientation
import kotlinx.cinterop.CPointer
import nativex.gtk.common.enums.Orientation

/**
 * kotlinx-gtk
 *
 * 23 / 06 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-Orientable.html">GtkOrientable</a>
 */
interface Orientable {
	val orientablePointer: CPointer<GtkOrientable>

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-Orientable.html#gtk-orientable-get-orientation">
	 *     gtk_orientable_get_orientation</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-Orientable.html#gtk-orientable-set-orientation">
	 *     gtk_orientable_set_orientation</a>
	 */
	var orientation: Orientation
		get() = Orientation.valueOf(gtk_orientable_get_orientation(orientablePointer))!!
		set(value) {
			gtk_orientable_set_orientation(orientablePointer, value.gtk)
		}
}