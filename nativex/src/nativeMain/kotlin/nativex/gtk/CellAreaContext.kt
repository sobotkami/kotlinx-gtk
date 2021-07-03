package nativex.gtk

import gtk.GtkCellAreaContext
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.KObject

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class CellAreaContext(
	 val cellAreaContextPointer: CPointer<GtkCellAreaContext>
) : KObject(cellAreaContextPointer.reinterpret())