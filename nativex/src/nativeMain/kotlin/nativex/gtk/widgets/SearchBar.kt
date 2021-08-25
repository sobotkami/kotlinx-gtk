package nativex.gtk.widgets

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.glib.bool
import nativex.glib.gtk

/**
 * kotlinx-gtk
 *
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSearchBar.html">GtkSearchBar</a>
 */
class SearchBar(
	val searchBarPointer: CPointer<GtkSearchBar>
) : Widget(searchBarPointer.reinterpret()) {
	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSearchBar.html#gtk-search-bar-new">gtk_search_bar_new</a>
	 */
	constructor() : this(gtk_search_bar_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSearchBar.html#gtk-search-bar-connect-entry">gtk_search_bar_connect_entry</a>
	 */
	fun connectEntry(entry: Editable) {
		gtk_search_bar_connect_entry(searchBarPointer, entry.editablePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSearchBar.html#gtk-search-bar-get-search-mode">gtk_search_bar_get_search_mode</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSearchBar.html#gtk-search-bar-set-search-mode">gtk_search_bar_set_search_mode</a>
	 */
	var searchMode: Boolean
		get() = gtk_search_bar_get_search_mode(searchBarPointer).bool
		set(value) = gtk_search_bar_set_search_mode(searchBarPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSearchBar.html#gtk-search-bar-get-show-close-button">
	 *     gtk_search_bar_get_show_close_button</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSearchBar.html#gtk-search-bar-set-show-close-button">
	 *     gtk_search_bar_set_show_close_button</a>
	 */
	var showCloseButton: Boolean
		get() = gtk_search_bar_get_show_close_button(searchBarPointer).bool
		set(value) = gtk_search_bar_set_show_close_button(searchBarPointer, value.gtk)

}