package nativex.gtk

import gtk.GtkTextBuffer
import gtk.gtk_text_buffer_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 19 / 03 / 2021
 */
class TextBuffer(
	 val textBufferPointer: CPointer<GtkTextBuffer>
) {
	constructor(textTagTable: TextTagTable) : this(
		gtk_text_buffer_new(
			textTagTable.textTagTablePointer
		)!!.reinterpret()
	)

	companion object{
		inline fun CPointer<GtkTextBuffer>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkTextBuffer>.wrap() =
			TextBuffer(this)
	}
}