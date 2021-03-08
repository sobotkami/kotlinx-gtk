package kotlin.gtk.common.enums

import gtk.GtkOrientation
import gtk.GtkToolbarStyle
import gtk.GtkToolbarStyle.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class ToolbarStyle(val key: Int, internal val gtk: GtkToolbarStyle) {
	ICONS(0, GTK_TOOLBAR_ICONS),
	TEXT(1, GTK_TOOLBAR_TEXT),
	BOTH(2, GTK_TOOLBAR_BOTH),
	BOTH_HORIZONTAL(3, GTK_TOOLBAR_BOTH_HORIZ);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		internal fun valueOf(gtk: GtkToolbarStyle) =
			values().find { it.gtk == gtk }
	}
}