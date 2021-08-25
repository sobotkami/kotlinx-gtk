package org.gtk.gtk

import gtk.GtkCellAreaContext
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gobject.KGObject

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class CellAreaContext(
	 val cellAreaContextPointer: CPointer<GtkCellAreaContext>
) : KGObject(cellAreaContextPointer.reinterpret())