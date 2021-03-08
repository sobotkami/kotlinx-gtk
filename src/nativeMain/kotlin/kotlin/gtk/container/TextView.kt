package kotlin.gtk.container

import gtk.GtkTextView
import gtk.gtk_text_view_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class TextView internal constructor(
	internal val textViewPointer: CPointer<GtkTextView>
) : Container(
	textViewPointer.reinterpret()
) {
	constructor() : this(gtk_text_view_new()!!.reinterpret())

}