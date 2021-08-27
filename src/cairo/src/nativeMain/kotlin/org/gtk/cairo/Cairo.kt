package org.gtk.cairo

import cairo.*
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 16 / 04 / 2021
 * TODO
 *
 * @see <a href="https://www.cairographics.org/manual/cairo-cairo-t.html">cario_t</a>
 */
class Cairo(
	val pointer: CPointer<cairo_t>
) {
	fun setSourceRGB(red: Double, green: Double, blue: Double) {
		cairo_set_source_rgb(pointer, red, green, blue)
	}

	fun paint() {
		cairo_paint(pointer)
	}

	fun destroy() {
		cairo_destroy(pointer)
	}

	fun setSourceSurface(surface: Surface, x: Double, y: Double) {
		cairo_set_source_surface(pointer, surface.cPointer, x, y)
	}

	fun rectangle(x: Double, y: Double, width: Double, height: Double) {
		cairo_rectangle(pointer, x, y, width, height)
	}

	fun fill() {
		cairo_fill(pointer)
	}

	/**
	 * @see <a href="https://www.cairographics.org/manual/cairo-cairo-t.html#cairo-create">cairo_create</a>
	 */
	constructor(surface: Surface) : this(cairo_create(surface.cPointer)!!)
}