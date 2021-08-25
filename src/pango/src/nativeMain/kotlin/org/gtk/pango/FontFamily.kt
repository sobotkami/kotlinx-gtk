package org.gtk.pango

import kotlinx.cinterop.CPointer
import pango.PangoFontFamily

/**
 * kotlinx-gtk
 *
 * 07 / 07 / 2021
 *
 * @see <a href=""></a>
 */
class FontFamily(val fontFamilyPointer: CPointer<PangoFontFamily>) {

	companion object{
		inline fun CPointer<PangoFontFamily>?.wrap() =
			this?.wrap()

		inline fun CPointer<PangoFontFamily>.wrap() =
			FontFamily(this)
	}
}