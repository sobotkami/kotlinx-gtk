package nativex.gtk.widgets.container.bin.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.gtk.bool
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class FontButton internal constructor(
	internal val fontButtonPointer: CPointer<GtkFontButton>
) : Button(fontButtonPointer.reinterpret()) {
	constructor() : this(gtk_font_button_new()!!.reinterpret())
	constructor(fontName: String) : this(gtk_font_button_new_with_font(fontName)!!.reinterpret())

	var showStyle: Boolean
		get() = gtk_font_button_get_show_style(fontButtonPointer).bool
		set(value) = gtk_font_button_set_show_style(
			fontButtonPointer,
			value.gtk
		)

	var showSize: Boolean
		get() = gtk_font_button_get_show_size(fontButtonPointer).bool
		set(value) = gtk_font_button_set_show_size(
			fontButtonPointer,
			value.gtk
		)

	var useFont: Boolean
		get() = gtk_font_button_get_use_font(fontButtonPointer).bool
		set(value) = gtk_font_button_set_use_font(
			fontButtonPointer,
			value.gtk
		)

	var useSize: Boolean
		get() = gtk_font_button_get_use_size(fontButtonPointer).bool
		set(value) = gtk_font_button_set_use_size(
			fontButtonPointer,
			value.gtk
		)

	var title: String?
		get() = gtk_font_button_get_title(fontButtonPointer)?.toKString()
		set(value) = gtk_font_button_set_title(fontButtonPointer, value)
}