package kotlin.gtk.common.enums

import gtk.GtkPositionType
import gtk.GtkPositionType.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class PositionType(val key: Int, internal val gtk: GtkPositionType) {
	LEFT(0, GTK_POS_LEFT),
	RIGHT(1, GTK_POS_RIGHT),
	TOP(2, GTK_POS_TOP),
	BOTTOM(3, GTK_POS_BOTTOM);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		internal fun valueOf(gtk: GtkPositionType) =
			values().find { it.gtk == gtk }
	}
}