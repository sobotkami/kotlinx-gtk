package kotlin.gtk

import gtk.GtkCellArea
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
abstract class CellArea
internal constructor(
	internal val pointer: CPointer<GtkCellArea>
) {
}