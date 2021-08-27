package org.gtk.cairo

import cairo.cairo_surface_destroy
import cairo.cairo_surface_t
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 17 / 04 / 2021
 * TODO
 * @see <a href="https://developer.gnome.org/cairo/stable/cairo-cairo-surface-t.html">cairo_surface_t</a>
 */
class Surface(
	val cPointer: CPointer<cairo_surface_t>
) {
	fun destroy() {
		cairo_surface_destroy(cPointer)
	}

	companion object {
		inline fun CPointer<cairo_surface_t>.wrap() = Surface(this)
		inline fun CPointer<cairo_surface_t>?.wrap() = this?.wrap()
	}
}