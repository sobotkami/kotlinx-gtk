package nativex.gtk.common.enums

import gtk.GtkSelectionMode
import gtk.GtkSelectionMode.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class SelectionMode(val key: Int,  val gtk: GtkSelectionMode) {
	NONE(0, GTK_SELECTION_NONE),
	SINGLE(1, GTK_SELECTION_SINGLE),
	BROWSE(2, GTK_SELECTION_BROWSE),
	MULTIPLE(3, GTK_SELECTION_MULTIPLE);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		 fun valueOf(gtk: GtkSelectionMode) =
			values().find { it.gtk == gtk }
	}
}