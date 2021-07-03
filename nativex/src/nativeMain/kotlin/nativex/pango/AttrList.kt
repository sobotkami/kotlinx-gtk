package nativex.pango

import gtk.PangoAttrList
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 27 / 03 / 2021
 */
class AttrList(val attrListPointer: CPointer<PangoAttrList>) {
	companion object {
		inline fun CPointer<PangoAttrList>?.wrap() =
			this?.wrap()

		inline fun CPointer<PangoAttrList>.wrap() =
			AttrList(this)
	}
}