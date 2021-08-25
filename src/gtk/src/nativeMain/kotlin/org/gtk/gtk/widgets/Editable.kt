package org.gtk.gtk.widgets

import gtk.GtkEditable
import gtk.gtk_editable_get_text
import gtk.gtk_editable_set_text
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.toKString

/**
 * kotlinx-gtk
 *
 * 24 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/iface.Editable.html"></a>
 */
interface Editable {
	val editablePointer: CPointer<GtkEditable>


	var text: String
		get() = gtk_editable_get_text(editablePointer)!!.toKString()
		set(value) = gtk_editable_set_text(editablePointer, value)
}