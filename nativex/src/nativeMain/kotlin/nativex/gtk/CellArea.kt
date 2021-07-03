package nativex.gtk

import gtk.GtkCellArea
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class CellArea(
	 val pointer: CPointer<GtkCellArea>
) {
	companion object{
		 inline fun CPointer<GtkCellArea>?.wrap() =
			this?.wrap()

		 inline fun CPointer<GtkCellArea>.wrap() =
			CellArea(this)
	}

}