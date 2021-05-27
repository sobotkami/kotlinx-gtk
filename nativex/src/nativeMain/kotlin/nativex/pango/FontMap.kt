package nativex.pango

import gtk.PangoFontMap
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 22 / 05 / 2021
 */
class FontMap internal constructor(internal val pointer: CPointer<PangoFontMap>) {
	companion object {
		internal inline fun CPointer<PangoFontMap>?.wrap() =
			this?.let { FontMap(it) }

		internal inline fun CPointer<PangoFontMap>.wrap() =
			FontMap(this)
	}
}