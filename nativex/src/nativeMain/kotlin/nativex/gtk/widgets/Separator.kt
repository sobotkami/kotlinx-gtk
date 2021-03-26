package nativex.gtk.widgets

import gtk.GtkSeparator
import gtk.gtk_separator_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.Orientation.HORIZONTAL
import nativex.gtk.common.enums.Orientation.VERTICAL

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
open class Separator internal constructor(
	separatorPointer: CPointer<GtkSeparator>
) : Widget(separatorPointer.reinterpret()) {
	constructor(orientation: Orientation) : this(gtk_separator_new(orientation.gtk)!!.reinterpret())

	class HorizontalSeparator() : Separator(HORIZONTAL)
	class VerticalSeparator() : Separator(VERTICAL)
}