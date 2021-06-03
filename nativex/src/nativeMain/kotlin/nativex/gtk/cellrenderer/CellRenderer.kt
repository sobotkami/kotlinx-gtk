package nativex.gtk.cellrenderer

import gtk.GtkCellRenderer
import gtk.gtk_cell_renderer_pixbuf_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.KObject

class CellRenderer internal constructor(
	internal val cellRendererPointer: CPointer<GtkCellRenderer>
) : KObject(cellRendererPointer.reinterpret()) {

	constructor() : this(gtk_cell_renderer_pixbuf_new()!!)

	companion object {
		internal inline fun CPointer<GtkCellRenderer>?.wrap() =
			this?.wrap()

		internal inline fun CPointer<GtkCellRenderer>.wrap() =
			CellRenderer(this)
	}
}