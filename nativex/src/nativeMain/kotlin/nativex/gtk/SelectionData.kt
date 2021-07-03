package nativex.gtk

import gtk.GtkSelectionData
import kotlinx.cinterop.CPointer
import nativex.gdk.DragContext

/**
 * kotlinx-gtk
 * 05 / 06 / 2021
 */
class SelectionData(
	 val selectionDataPointer: CPointer<GtkSelectionData>
) {
	companion object{
		 inline fun CPointer<GtkSelectionData>?.wrap() =
			this?.wrap()

		 inline fun CPointer<GtkSelectionData>.wrap() =
			SelectionData(this)
	}
}