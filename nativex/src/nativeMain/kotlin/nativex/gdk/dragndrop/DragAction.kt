package nativex.gdk.dragndrop

import gtk.*

/**
 * kotlinx-gtk
 *
 * 30 / 06 / 2021
 *
 * @see <a href="https://developer.gnome.org/gdk3/stable/gdk3-Drag-and-Drop.html#GdkDragAction">GdkDragAction</a>
 */
enum class DragAction(internal val gdk: GdkDragAction) {
	DEFAULT(GDK_ACTION_DEFAULT),
	COPY(GDK_ACTION_COPY),
	MOVE(GDK_ACTION_MOVE),
	LINK(GDK_ACTION_LINK),
	PRIVATE(GDK_ACTION_PRIVATE),
	ASK(GDK_ACTION_ASK);

	companion object {
		fun valueOf(gdk: GdkDragAction) = values().find { it.gdk == gdk }
	}
}