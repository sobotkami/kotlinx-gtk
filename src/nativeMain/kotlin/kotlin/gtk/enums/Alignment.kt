package kotlin.gtk.enums

import gtk.GtkAlign

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class Alignment(val key: Int, internal val gtk: GtkAlign) {
	FILL(0, GtkAlign.GTK_ALIGN_FILL),
	START(1, GtkAlign.GTK_ALIGN_START),
	END(2, GtkAlign.GTK_ALIGN_END),
	CENTER(3, GtkAlign.GTK_ALIGN_CENTER),
	BASELINE(4, GtkAlign.GTK_ALIGN_BASELINE);

	companion object {
		fun valueOf(key: Int) = values().find { it.key == key }
		fun valueOf(gtkAlign: GtkAlign) = values().find { it.gtk == gtkAlign }
	}
}