package nativex.gdk

import gtk.GdkVisual
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
class Visual internal constructor(
	internal val pointer: CPointer<GdkVisual>
) {

	companion object {
		internal inline fun CPointer<GdkVisual>?.wrap() =
			this?.let { Visual(it) }

		internal inline fun CPointer<GdkVisual>.wrap() =
			Visual(this)

	}
}