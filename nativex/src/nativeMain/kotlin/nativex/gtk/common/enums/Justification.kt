package nativex.gtk.common.enums

import gtk.GtkJustification
import gtk.GtkJustification.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class Justification(val key: Int, internal val gtk: GtkJustification) {
	LEFT(0, GTK_JUSTIFY_LEFT),
	RIGHT(1, GTK_JUSTIFY_RIGHT),
	CENTER(2, GTK_JUSTIFY_CENTER),
	FILL(3, GTK_JUSTIFY_FILL);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		internal fun valueOf(gtk: GtkJustification) =
			values().find { it.gtk == gtk }
	}
}