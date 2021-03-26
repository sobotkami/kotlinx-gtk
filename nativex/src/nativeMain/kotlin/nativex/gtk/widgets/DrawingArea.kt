package nativex.gtk.widgets

import gtk.GtkDrawingArea
import gtk.gtk_drawing_area_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class DrawingArea internal constructor(
	internal val drawingAreaPointer: CPointer<GtkDrawingArea>
) : Widget(drawingAreaPointer.reinterpret()) {
	constructor() : this(gtk_drawing_area_new()!!.reinterpret())
}