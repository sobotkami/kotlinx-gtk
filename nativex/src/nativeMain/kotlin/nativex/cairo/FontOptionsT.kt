package nativex.cairo

import gtk.cairo_font_options_t
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 22 / 05 / 2021
 */
class FontOptionsT( val pointer: CPointer<cairo_font_options_t>) {
	companion object {
		 inline fun CPointer<cairo_font_options_t>?.wrap() =
			this?.let { FontOptionsT(it) }

		 inline fun CPointer<cairo_font_options_t>.wrap() =
			FontOptionsT(this)
	}
}
