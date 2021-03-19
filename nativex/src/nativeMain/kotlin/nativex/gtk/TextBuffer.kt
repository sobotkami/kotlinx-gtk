package nativex.gtk

import gtk.GtkTextBuffer
import gtk.gtk_text_buffer_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 19 / 03 / 2021
 */
class TextBuffer internal constructor(
	internal val textBufferPointer: CPointer<GtkTextBuffer>
) {
	constructor(textTagTable: TextTagTable) : this(
		gtk_text_buffer_new(
			textTagTable.textTagTablePointer
		)!!.reinterpret()
	)
}