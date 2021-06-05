package nativex.gtk

import gtk.GtkSelectionData
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 05 / 06 / 2021
 */
class SelectionData internal constructor(
	internal val selectionDataPointer: CPointer<GtkSelectionData>
) {

}