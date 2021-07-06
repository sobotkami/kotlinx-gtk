package nativex.pango

import kotlinx.cinterop.CPointer
import pango.PangoFontMap

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