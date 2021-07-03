package nativex.gtk.dragndrop

import gtk.*

/**
 * kotlinx-gtk
 *
 * 30 / 06 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-Drag-and-Drop.html#GtkDestDefaults">GtkDestDefaults</a>
 */
enum class DestDefaults( val gtk: GtkDestDefaults) {
	MOTION(GTK_DEST_DEFAULT_MOTION),
	HIGHLIGHT(GTK_DEST_DEFAULT_HIGHLIGHT),
	DROP(GTK_DEST_DEFAULT_DROP),
	ALL(GTK_DEST_DEFAULT_ALL);

	companion object{
		 fun valueOf(gtk: GtkDestDefaults) = values().find { it.gtk == gtk }
	}
}