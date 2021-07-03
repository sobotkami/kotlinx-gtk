package nativex.gtk.common.enums

import gtk.GtkDragResult
import gtk.GtkDragResult.*

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class DragResult(
	val key: Int,
	 val gtk: GtkDragResult
) {
	SUCCESS(0, GTK_DRAG_RESULT_SUCCESS),
	NO_TARGET(2, GTK_DRAG_RESULT_NO_TARGET),
	USER_CANCELLED(3, GTK_DRAG_RESULT_USER_CANCELLED),

	TIMEOUT_EXPIRED(4, GTK_DRAG_RESULT_TIMEOUT_EXPIRED),
	BROKEN(5, GTK_DRAG_RESULT_GRAB_BROKEN),
	ERROR(6, GTK_DRAG_RESULT_ERROR);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		 fun valueOf(gtk: GtkDragResult) =
			values().find { it.gtk == gtk }
	}
}