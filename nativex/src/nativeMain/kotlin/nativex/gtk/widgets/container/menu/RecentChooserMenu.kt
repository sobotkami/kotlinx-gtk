package nativex.gtk.widgets.container.menu

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gtk.RecentManager

/**
 * kotlinx-gtk
 * 27 / 03 / 2021
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRecentChooserMenu.html">
 *     GtkRecentChooserMenu</a>
 */
class RecentChooserMenu(
	 val recentChooserMenuPointer: CPointer<GtkRecentChooserMenu>
) : Menu(recentChooserMenuPointer.reinterpret()) {
	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRecentChooserMenu.html#gtk-recent-chooser-menu-new">
	 *     gtk_recent_chooser_menu_new</a>
	 */
	constructor() : this(gtk_recent_chooser_menu_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRecentChooserMenu.html#gtk-recent-chooser-menu-new-for-manager">
	 *     gtk_recent_chooser_menu_new_for_manager</a>
	 */
	constructor(manager: RecentManager) : this(
		gtk_recent_chooser_menu_new_for_manager(manager.managerPointer)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRecentChooserMenu.html#gtk-recent-chooser-menu-get-show-numbers">
	 *     gtk_recent_chooser_menu_get_show_numbers</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRecentChooserMenu.html#gtk-recent-chooser-menu-set-show-numbers">
	 *     gtk_recent_chooser_menu_set_show_numbers</a>
	 */
	var showNumbers: Boolean
		get() = gtk_recent_chooser_menu_get_show_numbers(
			recentChooserMenuPointer
		).bool
		set(value) = gtk_recent_chooser_menu_set_show_numbers(
			recentChooserMenuPointer,
			value.gtk
		)
}