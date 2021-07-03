package nativex.gtk.widgets

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gdk.Pixbuf
import nativex.gdk.RGBA
import nativex.gtk.*

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class CellView(
	 val cellViewPointer: CPointer<GtkCellView>
) : Widget(cellViewPointer.reinterpret()) {
	constructor() : this(gtk_cell_view_new()!!.reinterpret())
	constructor(cellView: CellArea, cellAreaContext: CellAreaContext) : this(
		gtk_cell_view_new_with_context(
			cellView.pointer,
			cellAreaContext.cellAreaContextPointer
		)!!.reinterpret()
	)

	constructor(text: String, markup: Boolean = false) : this(
		(if (markup) gtk_cell_view_new_with_text(text) else gtk_cell_view_new_with_markup(
			text
		))!!.reinterpret()
	)

	constructor(pixBuf: Pixbuf) : this(gtk_cell_view_new_with_pixbuf(pixBuf.pixbufPointer)!!.reinterpret())

	var model: TreeModel?
		get() = gtk_cell_view_get_model(cellViewPointer)?.let { TreeModel(it) }
		set(value) = gtk_cell_view_set_model(cellViewPointer, value?.treeModelPointer)

	var displayedRow: TreeModel.TreePath?
		get() = gtk_cell_view_get_displayed_row(cellViewPointer)?.let {
			TreeModel.TreePath(
				it
			)
		}
		set(value) = gtk_cell_view_set_displayed_row(
			cellViewPointer,
			value?.treePathPointer
		)

	fun setBackgroundRgba(rgba: RGBA) {
		gtk_cell_view_set_background_rgba(cellViewPointer, rgba.rgbaPointer)
	}

	var drawSensitive: Boolean
		get() = gtk_cell_view_get_draw_sensitive(cellViewPointer).bool
		set(value) = gtk_cell_view_set_draw_sensitive(
			cellViewPointer,
			value.gtk
		)

	var fitModel: Boolean
		get() = gtk_cell_view_get_fit_model(cellViewPointer).bool
		set(value) = gtk_cell_view_set_fit_model(
			cellViewPointer,
			value.gtk
		)
}