package nativex.gtk

import gtk.GtkCellArea
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class CellArea internal constructor(
	internal val pointer: CPointer<GtkCellArea>
) {
	companion object{
		internal inline fun CPointer<GtkCellArea>?.wrap() =
			this?.wrap()

		internal inline fun CPointer<GtkCellArea>.wrap() =
			CellArea(this)
	}

}