package nativex.gtk

import gtk.GtkCellAreaContext
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.KotlinGObject

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class CellAreaContext internal constructor(
	internal val cellAreaContextPointer: CPointer<GtkCellAreaContext>
) : KotlinGObject(cellAreaContextPointer.reinterpret()) {


}