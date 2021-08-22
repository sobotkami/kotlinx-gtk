package nativex.gtk

import gtk.GtkAccessible
import gtk.GtkAccessibleRole
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 28 / 07 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/iface.Accessible.html">GtkAccessible</a>
 */
interface Accessible {
	val accessiblePointer: CPointer<GtkAccessible>

	/**
	 * TODO
	 */
	enum class Role(val gtk: GtkAccessibleRole) {

		;

		companion object {
			fun valueOf(gtk: GtkAccessibleRole) = values().find { it.gtk == gtk }
		}
	}
}