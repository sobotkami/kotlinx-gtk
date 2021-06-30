package nativex.gtk.dragndrop

import gtk.*

/**
 * kotlinx-gtk
 *
 * 30 / 06 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-Drag-and-Drop.html#GtkTargetFlags">GtkTargetFlags</a>
 */
enum class TargetFlags(val gtk: GtkTargetFlags) {
	SAME_APP(GTK_TARGET_SAME_APP),
	SAME_WIDGET(GTK_TARGET_SAME_WIDGET),
	OTHER_APP(GTK_TARGET_OTHER_APP),
	OTHER_WIDGET(GTK_TARGET_OTHER_WIDGET);

	companion object {
		fun valueOf(gtk: GtkTargetFlags) = values().find { it.gtk == gtk }
	}

}