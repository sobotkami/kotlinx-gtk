package nativex.gtk.common.enums

import gtk.GtkToolbarStyle
import gtk.GtkToolbarStyle.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class ToolbarStyle(val key: Int,  val gtk: GtkToolbarStyle) {
	ICONS(0, GTK_TOOLBAR_ICONS),
	TEXT(1, GTK_TOOLBAR_TEXT),
	BOTH(2, GTK_TOOLBAR_BOTH),
	BOTH_HORIZONTAL(3, GTK_TOOLBAR_BOTH_HORIZ);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		 fun valueOf(gtk: GtkToolbarStyle) =
			values().find { it.gtk == gtk }
	}
}