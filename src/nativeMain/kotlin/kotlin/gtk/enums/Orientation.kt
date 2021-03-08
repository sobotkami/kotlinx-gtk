package kotlin.gtk.enums

import gtk.GtkOrientation

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class Orientation(val key: Int, internal val gtk: GtkOrientation) {
	HORIZONTAL(0, GtkOrientation.GTK_ORIENTATION_HORIZONTAL),
	VERTICAL(1, GtkOrientation.GTK_ORIENTATION_VERTICAL);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		fun valueOf(orientation: GtkOrientation) =
			values().find { it.gtk == orientation }
	}
}