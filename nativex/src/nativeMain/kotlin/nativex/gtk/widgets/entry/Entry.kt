package nativex.gtk.widgets.entry

import gtk.GtkEntry
import gtk.gtk_entry_get_text
import gtk.gtk_entry_new
import gtk.gtk_entry_set_text
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
open class Entry internal constructor(
	internal val entryPointer: CPointer<GtkEntry>
) : Widget(entryPointer.reinterpret()) {

	constructor() : this(gtk_entry_new()!!.reinterpret())

	var text: String?
		get() = gtk_entry_get_text(entryPointer)?.toKString()
		set(value) = gtk_entry_set_text(entryPointer, value)
}