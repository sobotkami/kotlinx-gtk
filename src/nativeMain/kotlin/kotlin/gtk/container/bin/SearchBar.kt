package kotlin.gtk.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gdk.Event
import kotlin.gtk.from
import kotlin.gtk.gtkValue
import kotlin.gtk.widgets.entry.Entry

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
		get() = Boolean.from(gtk_search_bar_get_search_mode(searchBarPointer))
		set(value) = gtk_search_bar_set_search_mode(
			searchBarPointer,
			value.gtkValue
		)

	var showCloseButton: Boolean
		get() = Boolean.from(
			gtk_search_bar_get_show_close_button(
				searchBarPointer
			)
		)
		set(value) = gtk_search_bar_set_show_close_button(
			searchBarPointer,
			value.gtkValue
		)

	fun handleEvent(event: Event) {
		TODO("gtk_search_bar_handle_even")
	}

}