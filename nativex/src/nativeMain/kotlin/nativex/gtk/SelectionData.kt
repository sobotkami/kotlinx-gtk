package nativex.gtk

import gtk.GtkSelectionData
import kotlinx.cinterop.CPointer
import nativex.gdk.DragContext

/**
 * kotlinx-gtk
 * 05 / 06 / 2021
 */
class SelectionData internal constructor(
	internal val selectionDataPointer: CPointer<GtkSelectionData>
) {
	companion object{
		internal inline fun CPointer<GtkSelectionData>?.wrap() =
			this?.wrap()

		internal inline fun CPointer<GtkSelectionData>.wrap() =
			SelectionData(this)
	}
}