package nativex.gtk.widgets.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gdk.Event
import nativex.gtk.bool
import nativex.gtk.gtk
import nativex.gtk.widgets.entry.Entry

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class SearchBar(
	internal val searchBarPointer: CPointer<GtkSearchBar>
) : Bin(searchBarPointer.reinterpret()) {
	constructor() : this(gtk_search_bar_new()!!.reinterpret())

	fun connectEntry(entry: Entry) {
		gtk_search_bar_connect_entry(searchBarPointer, entry.entryPointer)
	}

	var searchMode: Boolean
		get() = gtk_search_bar_get_search_mode(searchBarPointer).bool
		set(value) = gtk_search_bar_set_search_mode(
			searchBarPointer,
			value.gtk
		)

	var showCloseButton: Boolean
		get() = gtk_search_bar_get_show_close_button(
			searchBarPointer
		)
			.bool
		set(value) = gtk_search_bar_set_show_close_button(
			searchBarPointer,
			value.gtk
		)

	fun handleEvent(event: Event) {
		TODO("gtk_search_bar_handle_even")
	}

}