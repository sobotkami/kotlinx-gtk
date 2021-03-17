package kotlin.gtk.container.bin.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import kotlin.gdk.RGBA
import kotlin.gtk.ColorChooser

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class ColorButton internal constructor(
	internal val colorButtonPointer: CPointer<GtkColorButton>
) : Button(colorButtonPointer.reinterpret()), ColorChooser {
	override val colorChooserPointer: PointerHolder<GtkColorChooser> by lazy {
		PointerHolder(colorButtonPointer.reinterpret())
	}

	constructor() : this(gtk_color_button_new()!!.reinterpret())
	constructor(rgba: RGBA) : this(gtk_color_button_new_with_rgba(rgba.rgbaPointer)!!.reinterpret())

	var title: String?
		get() = gtk_color_button_get_title(colorButtonPointer)?.toKString()
		set(value) = gtk_color_button_set_title(colorButtonPointer, value)
}