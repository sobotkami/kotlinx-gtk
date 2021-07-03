package nativex.gtk.common.enums

import gtk.GtkScrollStep
import gtk.GtkScrollStep.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class ScrollStep(val key: Int,  val gtk: GtkScrollStep) {
	STEPS(0, GTK_SCROLL_STEPS),
	PAGES(1, GTK_SCROLL_PAGES),
	ENDS(2, GTK_SCROLL_ENDS),
	HORIZONTAL_STEPS(3, GTK_SCROLL_HORIZONTAL_STEPS),
	HORIZONTAL_PAGES(4, GTK_SCROLL_HORIZONTAL_PAGES),
	HORIZONTAL_ENDS(5, GTK_SCROLL_HORIZONTAL_ENDS);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		 fun valueOf(gtk: GtkScrollStep) =
			values().find { it.gtk == gtk }
	}
}