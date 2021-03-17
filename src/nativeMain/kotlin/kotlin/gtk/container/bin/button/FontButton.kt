package kotlin.gtk.container.bin.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import kotlin.gtk.from
import kotlin.gtk.gtkValue

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
		get() = Boolean.from(gtk_font_button_get_show_style(fontButtonPointer))
		set(value) = gtk_font_button_set_show_style(
			fontButtonPointer,
			value.gtkValue
		)

	var showSize: Boolean
		get() = Boolean.from(gtk_font_button_get_show_size(fontButtonPointer))
		set(value) = gtk_font_button_set_show_size(
			fontButtonPointer,
			value.gtkValue
		)

	var useFont: Boolean
		get() = Boolean.from(gtk_font_button_get_use_font(fontButtonPointer))
		set(value) = gtk_font_button_set_use_font(
			fontButtonPointer,
			value.gtkValue
		)

	var useSize: Boolean
		get() = Boolean.from(gtk_font_button_get_use_size(fontButtonPointer))
		set(value) = gtk_font_button_set_use_size(
			fontButtonPointer,
			value.gtkValue
		)

	var title: String?
		get() = gtk_font_button_get_title(fontButtonPointer)?.toKString()
		set(value) = gtk_font_button_set_title(fontButtonPointer, value)
}