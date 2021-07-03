package nativex.pango

import gtk.PangoTabArray
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 03 / 07 / 2021
 *
 * @see <a href=""></a>
 */
class TabArray(val tabArrayPointer: CPointer<PangoTabArray>){
	companion object {
		inline fun CPointer<PangoTabArray>?.wrap() =
			this?.wrap()

		inline fun CPointer<PangoTabArray>.wrap() =
			TabArray(this)
	}
}