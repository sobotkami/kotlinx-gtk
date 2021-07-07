package nativex.pango

import kotlinx.cinterop.CPointer
import pango.PangoFontFace

/**
 * kotlinx-gtk
 *
 * 07 / 07 / 2021
 *
 * @see <a href=""></a>
 */
class FontFace(val fontFacePointer: CPointer<PangoFontFace>) {
	companion object {
		inline fun CPointer<PangoFontFace>?.wrap() =
			this?.wrap()

		inline fun CPointer<PangoFontFace>.wrap() =
			FontFace(this)
	}
}