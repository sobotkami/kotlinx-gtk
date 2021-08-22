package nativex.gtk.common.enums

import gtk.GtkOrientation

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class Orientation( val gtk: GtkOrientation) {
	HORIZONTAL( GtkOrientation.GTK_ORIENTATION_HORIZONTAL),
	VERTICAL( GtkOrientation.GTK_ORIENTATION_VERTICAL);

	companion object {
		 fun valueOf(gtk: GtkOrientation) =
			values().find { it.gtk == gtk }
	}
}