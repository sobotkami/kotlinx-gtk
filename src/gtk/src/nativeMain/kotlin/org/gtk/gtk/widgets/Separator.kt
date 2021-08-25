package org.gtk.gtk.widgets

import gtk.GtkOrientable
import gtk.GtkSeparator
import gtk.gtk_separator_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gtk.Orientable
import org.gtk.gtk.common.enums.Orientation
import org.gtk.gtk.common.enums.Orientation.HORIZONTAL
import org.gtk.gtk.common.enums.Orientation.VERTICAL

/**
 * kotlinx-gtk
 *
 * 26 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSeparator.html">GtkSeparator</a>
 */
open class Separator(
	val separatorPointer: CPointer<GtkSeparator>
) : Widget(separatorPointer.reinterpret()), Orientable {
	override val orientablePointer: CPointer<GtkOrientable> by lazy { separatorPointer.reinterpret() }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSeparator.html#gtk-separator-new">gtk_separator_new</a>
	 */
	constructor(orientation: Orientation) : this(gtk_separator_new(orientation.gtk)!!.reinterpret())

	class HorizontalSeparator : Separator(HORIZONTAL)
	class VerticalSeparator : Separator(VERTICAL)
}