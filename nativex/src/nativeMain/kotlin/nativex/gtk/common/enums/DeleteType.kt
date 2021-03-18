package nativex.gtk.common.enums

import gtk.GtkDeleteType
import gtk.GtkDeleteType.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class DeleteType(val key: Int, internal val gtk: GtkDeleteType) {
	CHARS(0, GTK_DELETE_CHARS),
	ENDS(1, GTK_DELETE_WORD_ENDS),
	WORDS(2, GTK_DELETE_WORDS),
	DISPLAY_LINES(3, GTK_DELETE_DISPLAY_LINES),
	DISPLAY_LINES_ENDS(4, GTK_DELETE_DISPLAY_LINE_ENDS),
	PARAGRAPH_ENDS(5, GTK_DELETE_PARAGRAPH_ENDS),
	PARAGRAPHS(6, GTK_DELETE_PARAGRAPHS),
	WHITESPACE(7, GTK_DELETE_WHITESPACE);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		internal fun valueOf(gtk: GtkDeleteType) =
			values().find { it.gtk == gtk }
	}
}