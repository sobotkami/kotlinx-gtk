package nativex.cairo

import cairo.cairo_surface_t
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 17 / 04 / 2021
 * TODO
 * @see <a href="https://developer.gnome.org/cairo/stable/cairo-cairo-surface-t.html">cairo_surface_t</a>
 */
class CairoSurfaceT(
	 val cPointer: CPointer<cairo_surface_t>
)