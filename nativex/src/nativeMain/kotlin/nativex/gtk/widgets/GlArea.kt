package nativex.gtk.widgets

import gtk.GtkGLArea
import gtk.gtk_gl_area_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class GlArea internal constructor(
	internal val glAreaPointer: CPointer<GtkGLArea>
) : Widget(glAreaPointer.reinterpret()) {
	constructor() : this(gtk_gl_area_new()!!.reinterpret())
}