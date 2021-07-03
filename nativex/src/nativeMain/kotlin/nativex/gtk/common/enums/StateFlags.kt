package nativex.gtk.common.enums

import gtk.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class StateFlags(val key: Int,  val gtk: GtkStateFlags) {
	NORMAL(0, GTK_STATE_FLAG_NORMAL),
	ACTIVE(1, GTK_STATE_FLAG_ACTIVE),
	PRELIGHT(2, GTK_STATE_FLAG_PRELIGHT),
	SELECTED(3, GTK_STATE_FLAG_SELECTED),
	INSENSITIVE(4, GTK_STATE_FLAG_INSENSITIVE),
	INCONSISTENT(5, GTK_STATE_FLAG_INCONSISTENT),
	FOCUSED(6, GTK_STATE_FLAG_FOCUSED),
	BACKDROP(7, GTK_STATE_FLAG_BACKDROP),
	DIR_LTR(8, GTK_STATE_FLAG_DIR_LTR),
	DIR_RTL(9, GTK_STATE_FLAG_DIR_RTL),
	LINK(10, GTK_STATE_FLAG_LINK),
	VISITED(11, GTK_STATE_FLAG_VISITED),
	CHECKED(12, GTK_STATE_FLAG_CHECKED),
	DROP_ACTIVE(13, GTK_STATE_FLAG_DROP_ACTIVE);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		
		 fun valueOf(gtk: GtkStateFlags) =
			values().find { it.gtk == gtk }
	}
}