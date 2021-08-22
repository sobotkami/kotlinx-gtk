package nativex.cairo

import cairo.cairo_font_options_t
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 22 / 05 / 2021
 */
class FontOptions(val pointer: CPointer<cairo_font_options_t>) {
	companion object {
		 inline fun CPointer<cairo_font_options_t>?.wrap() =
			this?.let { FontOptions(it) }

		 inline fun CPointer<cairo_font_options_t>.wrap() =
			FontOptions(this)
	}
}
