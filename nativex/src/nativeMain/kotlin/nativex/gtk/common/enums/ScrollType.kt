package nativex.gtk.common.enums

import gtk.GtkScrollType
import gtk.GtkScrollType.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class ScrollType(val key: Int,  val gtk: GtkScrollType) {
	NONE(0, GTK_SCROLL_NONE),
	JUMP(1, GTK_SCROLL_JUMP),
	STEP_BACKWARD(2, GTK_SCROLL_STEP_BACKWARD),
	STEP_FORWARD(3, GTK_SCROLL_STEP_FORWARD),
	PAGE_BACKWARD(4, GTK_SCROLL_PAGE_BACKWARD),
	PAGE_FORWARD(5, GTK_SCROLL_PAGE_FORWARD),
	STEP_UP(6, GTK_SCROLL_STEP_UP),
	STEP_DOWN(7, GTK_SCROLL_STEP_DOWN),
	PAGE_UP(8, GTK_SCROLL_PAGE_UP),
	PAGE_DOWN(9, GTK_SCROLL_PAGE_DOWN),
	STEP_LEFT(10, GTK_SCROLL_STEP_LEFT),
	STEP_RIGHT(11, GTK_SCROLL_STEP_RIGHT),
	PAGE_LEFT(12, GTK_SCROLL_PAGE_LEFT),
	PAGE_RIGHT(13, GTK_SCROLL_PAGE_RIGHT),
	START(14, GTK_SCROLL_START),
	END(15, GTK_SCROLL_END);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		 fun valueOf(gtk: GtkScrollType) =
			values().find { it.gtk == gtk }
	}
}