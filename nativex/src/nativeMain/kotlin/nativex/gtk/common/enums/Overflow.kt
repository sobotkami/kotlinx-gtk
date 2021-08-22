package nativex.gtk.common.enums

import gtk.GtkOverflow

/**
 * kotlinx-gtk
 *
 * 01 / 08 / 2021
 *
 * @see <a href=""></a>
 */
enum class Overflow(val gtk: GtkOverflow) {
	VISIBLE(GtkOverflow.GTK_OVERFLOW_VISIBLE),
	HIDDEN(GtkOverflow.GTK_OVERFLOW_HIDDEN);

	companion object {
		inline fun valueOf(gtk: GtkOverflow) = values().find { it.gtk == gtk }
	}
}