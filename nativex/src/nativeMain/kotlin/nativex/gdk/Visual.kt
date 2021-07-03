package nativex.gdk

import gtk.GdkVisual
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
class Visual(
	 val pointer: CPointer<GdkVisual>
) {

	companion object {
		 inline fun CPointer<GdkVisual>?.wrap() =
			this?.let { Visual(it) }

		 inline fun CPointer<GdkVisual>.wrap() =
			Visual(this)

	}
}