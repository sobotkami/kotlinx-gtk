package nativex.gtk.widgets.container.bin.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.glib.bool
import nativex.glib.gtk

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class LinkButton(
	 val linkButtonPointer: CPointer<GtkLinkButton>
) : Button(linkButtonPointer.reinterpret()) {

	constructor(uri: String) : this(gtk_link_button_new(uri)!!.reinterpret())
	constructor(uri: String, label: String) : this(
		gtk_link_button_new_with_label(uri, label)!!.reinterpret()
	)

	var uri: String
		get() = gtk_link_button_get_uri(linkButtonPointer)!!.toKString()
		set(value) = gtk_link_button_set_uri(linkButtonPointer, uri)

	var visited: Boolean
		get() = gtk_link_button_get_visited(linkButtonPointer).bool
		set(value) = gtk_link_button_set_visited(
			linkButtonPointer,
			value.gtk
		)

}