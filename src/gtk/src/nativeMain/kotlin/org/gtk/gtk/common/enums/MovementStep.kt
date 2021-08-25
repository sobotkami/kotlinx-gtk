package org.gtk.gtk.common.enums

import gtk.GtkMovementStep
import gtk.GtkMovementStep.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class MovementStep(val key: Int,  val gtk: GtkMovementStep) {
	LOGICAL_POSITIONS(0, GTK_MOVEMENT_LOGICAL_POSITIONS),
	VISUAL_POSITIONS(1, GTK_MOVEMENT_VISUAL_POSITIONS),
	WORDS(2, GTK_MOVEMENT_WORDS),
	DISPLAY_LINES(3, GTK_MOVEMENT_DISPLAY_LINES),
	DISPLAY_LINE_ENDS(4, GTK_MOVEMENT_DISPLAY_LINE_ENDS),
	PARAGRAPHS(5, GTK_MOVEMENT_PARAGRAPHS),
	PARAGRAPH_ENDS(6, GTK_MOVEMENT_PARAGRAPH_ENDS),
	PAGES(7, GTK_MOVEMENT_PAGES),
	BUFFER_ENDS(8, GTK_MOVEMENT_BUFFER_ENDS),
	HORIZONTAL_PAGES(9, GTK_MOVEMENT_HORIZONTAL_PAGES);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		 fun valueOf(gtk: GtkMovementStep) =
			values().find { it.gtk == gtk }
	}
}