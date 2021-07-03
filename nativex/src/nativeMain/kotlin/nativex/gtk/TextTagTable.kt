package nativex.gtk

import gtk.GtkTextTagTable
import gtk.gtk_text_tag_table_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 19 / 03 / 2021
 */
class TextTagTable(
	 val textTagTablePointer: CPointer<GtkTextTagTable>
) {
	constructor() : this(gtk_text_tag_table_new()!!.reinterpret())
}