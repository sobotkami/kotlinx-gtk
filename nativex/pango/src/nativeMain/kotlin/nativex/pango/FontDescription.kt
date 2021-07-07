package nativex.pango

import kotlinx.cinterop.CPointer
import pango.PangoFontDescription

/**
 * kotlinx-gtk
 *
 * 07 / 07 / 2021
 *
 * @see <a href=""></a>
 */
class FontDescription(val fontDescriptionPointer: CPointer<PangoFontDescription>) {
	companion object {
		inline fun CPointer<PangoFontDescription>?.wrap() =
			this?.wrap()

		inline fun CPointer<PangoFontDescription>.wrap() =
			FontDescription(this)
	}
}