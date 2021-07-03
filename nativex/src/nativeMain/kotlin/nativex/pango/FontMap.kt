package nativex.pango

import gtk.PangoFontMap
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 22 / 05 / 2021
 */
class FontMap( val pointer: CPointer<PangoFontMap>) {
	companion object {
		 inline fun CPointer<PangoFontMap>?.wrap() =
			this?.let { FontMap(it) }

		 inline fun CPointer<PangoFontMap>.wrap() =
			FontMap(this)
	}
}