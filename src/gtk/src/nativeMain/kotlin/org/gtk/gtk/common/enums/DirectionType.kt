package org.gtk.gtk.common.enums
import glib.gpointer
import gobject.GCallback
import gtk.GtkDirectionType
import gtk.GtkDirectionType.*
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-Standard-Enumerations.html#GtkDirectionType">
 *     GtkDirectionType</a>
 */
enum class DirectionType(val key: Int,  val gtk: GtkDirectionType) {
	FORWARD(0, GTK_DIR_TAB_FORWARD),
	BACKWARD(1, GTK_DIR_TAB_BACKWARD),
	UP(2, GTK_DIR_UP),
	DOWN(3, GTK_DIR_DOWN),
	LEFT(4, GTK_DIR_LEFT),
	RIGHT(5, GTK_DIR_RIGHT);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		 fun valueOf(gtk: GtkDirectionType) =
			values().find { it.gtk == gtk }

		/** Generic callback for signals */
		 val staticDirectionTypeCallback: GCallback =
			staticCFunction { _: gpointer, direction: GtkDirectionType, data: gpointer ->
				data.asStableRef<(DirectionType) -> Unit>().get().invoke(valueOf(direction)!!)
				Unit
			}.reinterpret()
	}
}