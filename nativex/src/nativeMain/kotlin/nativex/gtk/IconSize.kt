package nativex.gtk

import gtk.GtkIconSize
import gtk.GtkIconSize.*

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
enum class IconSize(val key: Int, internal val gtk: GtkIconSize) {
	INVALID(0, GTK_ICON_SIZE_INVALID),
	MENU(1, GTK_ICON_SIZE_MENU),
	SMALL_TOOLBAR(2, GTK_ICON_SIZE_SMALL_TOOLBAR),
	LARGE_TOOLBAR(3, GTK_ICON_SIZE_LARGE_TOOLBAR),
	BUTTON(4, GTK_ICON_SIZE_BUTTON),
	DND(5, GTK_ICON_SIZE_DND),
	DIALOG(6, GTK_ICON_SIZE_DIALOG);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		internal fun valueOf(gtk: GtkIconSize) =
			values().find { it.gtk == gtk }
	}
}