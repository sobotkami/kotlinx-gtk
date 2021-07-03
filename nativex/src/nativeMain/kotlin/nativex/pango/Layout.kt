package nativex.pango

import gtk.PangoLayout
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class Layout(val pointer: CPointer<PangoLayout>) {
	companion object {
		inline fun CPointer<PangoLayout>?.wrap() =
			this?.wrap()

		inline fun CPointer<PangoLayout>.wrap() =
			Layout(this)
	}
}