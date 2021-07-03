package nativex.cairo

import gtk.cairo_t
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 16 / 04 / 2021
 * TODO
 *
 * @see <a href="https://developer.gnome.org/cairo/stable/cairo-cairo-t.html">cario_t</a>
 */
class CairoT(
	 val pointer: CPointer<cairo_t>
)