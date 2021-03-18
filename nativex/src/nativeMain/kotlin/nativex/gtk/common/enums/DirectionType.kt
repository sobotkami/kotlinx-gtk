package nativex.gtk.common.enums

import gtk.GtkDirectionType
import gtk.GtkDirectionType.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class DirectionType(val key: Int, internal val gtk: GtkDirectionType) {
	FORWARD(0, GTK_DIR_TAB_FORWARD),
	BACKWARD(1, GTK_DIR_TAB_BACKWARD),
	UP(2, GTK_DIR_UP),
	DOWN(3, GTK_DIR_DOWN),
	LEFT(4, GTK_DIR_LEFT),
	RIGHT(5, GTK_DIR_RIGHT);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		internal fun valueOf(gtk: GtkDirectionType) =
			values().find { it.gtk == gtk }
	}
}