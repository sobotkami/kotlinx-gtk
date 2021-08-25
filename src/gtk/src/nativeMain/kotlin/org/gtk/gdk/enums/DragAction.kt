package org.gtk.gdk.enums

import gtk.*

/**
 * kotlinx-gtk
 *
 * 30 / 06 / 2021
 *
 * @see <a href="https://docs.gtk.org/gdk3/flags.DragAction.html">GdkDragAction</a>
 */
enum class DragAction(val gdk: GdkDragAction) {
	//DEFAULT(GDK_ACTION_DEFAULT),
	COPY(GDK_ACTION_COPY),
	MOVE(GDK_ACTION_MOVE),
	LINK(GDK_ACTION_LINK),

	//PRIVATE(GDK_ACTION_PRIVATE),
	ASK(GDK_ACTION_ASK);

	companion object {
		fun valueOf(gdk: GdkDragAction) = values().find { it.gdk == gdk }
	}
}