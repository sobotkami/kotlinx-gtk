package kotlin.gtk.enums

import gtk.GtkStateType
import gtk.GtkStateType.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class StateType(val key: Int, internal val gtk: GtkStateType) {
	NORMAL(0, GTK_STATE_NORMAL),

	ACTIVE(1, GTK_STATE_ACTIVE),

	PRELIGHT(2, GTK_STATE_PRELIGHT),

	SELECTED(3, GTK_STATE_SELECTED),

	INSENSITIVE(4, GTK_STATE_INSENSITIVE),

	INCONSISTENT(5, GTK_STATE_INCONSISTENT),

	FOCUSED(6, GTK_STATE_FOCUSED);

	;

	companion object {
		fun valueOf(key: Int) = values().find { it.key == key }

		fun valueOf(gtk: GtkStateType) = values().find { it.gtk == gtk }
	}
}