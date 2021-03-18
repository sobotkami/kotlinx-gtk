package nativex.gtk.widgets.entry

import gtk.GtkSearchEntry
import gtk.gtk_search_entry_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gdk.Event

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class SearchEntry internal constructor(
	internal val searchEntryPointer: CPointer<GtkSearchEntry>
) : Entry(searchEntryPointer.reinterpret()) {
	constructor() : this(gtk_search_entry_new()!!.reinterpret())

	fun handleEvent(event: Event): Boolean {
		TODO("gtk_search_entry_handle_event")
	}
}