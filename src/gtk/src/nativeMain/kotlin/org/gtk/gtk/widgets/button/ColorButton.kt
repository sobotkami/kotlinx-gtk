package org.gtk.gtk.widgets.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import org.gtk.gdk.RGBA
import org.gtk.gtk.ColorChooser

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 *
 * <a href="https://developer.gnome.org/gtk3/stable/GtkColorButton.html">GtkColorButton</a>
 */
class ColorButton(
	 val colorButtonPointer: CPointer<GtkColorButton>
) : Button(colorButtonPointer.reinterpret()), ColorChooser {

	override val colorChooserPointer: CPointer<GtkColorChooser> by lazy { colorButtonPointer.reinterpret() }

	/**
	 * <a href="https://developer.gnome.org/gtk3/stable/GtkColorButton.html#gtk-color-button-get-title">
	 *     gtk_color_button_get_title</a>
	 * <a href="https://developer.gnome.org/gtk3/stable/GtkColorButton.html#gtk-color-button-set-title">
	 *     gtk_color_button_set_title</a>
	 */
	var title: String?
		get() = gtk_color_button_get_title(colorButtonPointer)?.toKString()
		set(value) = gtk_color_button_set_title(colorButtonPointer, value)

	/**
	 * <a href="https://developer.gnome.org/gtk3/stable/GtkColorButton.html#gtk-color-button-new">
	 *     gtk_color_button_new</a>
	 */
	constructor() : this(gtk_color_button_new()!!.reinterpret())

	/**
	 * <a href="https://developer.gnome.org/gtk3/stable/GtkColorButton.html#gtk-color-button-new-with-rgba">
	 *     gtk_color_button_new_with_rgba</a>
	 */
	constructor(rgba: RGBA) : this(gtk_color_button_new_with_rgba(rgba.rgbaPointer)!!.reinterpret())
}