package nativex.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.staticCFunction
import nativex.PointerHolder
import nativex.gtk.CellArea.Companion.wrap
import nativex.gtk.TreeModel.TreeIter
import nativex.gtk.cellrenderer.CellRenderer
import nativex.gtk.cellrenderer.CellRenderer.Companion.wrap


interface CellLayout {
	companion object {

		internal val staticCellDataFunc =
			staticCFunction { cellLayout: CPointer<GtkCellLayout>?,
			                  cell: CPointer<GtkCellRenderer>?,
			                  treeModel: CPointer<GtkTreeModel>?,
			                  iter: CPointer<GtkTreeIter>?,
			                  data: gpointer? ->
				data?.asStableRef<(CellLayout, CellRenderer, TreeModel, TreeIter) -> Unit>()?.get()?.invoke(
					Impl(PointerHolder(cellLayout!!)),
					CellRenderer(cell!!),
					TreeModel(treeModel!!),
					TreeIter(iter!!)
				)
				Unit
			}
	}

	class Impl(override val cellLayoutHolder: PointerHolder<GtkCellLayout>) : CellLayout

	val cellLayoutHolder: PointerHolder<GtkCellLayout>


	fun setCellDataFunc(
		renderer: CellRenderer, function: (
			CellLayout,
			CellRenderer,
			TreeModel,
			TreeIter
		) -> Unit
	) {
		gtk_cell_layout_set_cell_data_func(
			cell_layout = cellLayoutHolder.ptr,
			cell = renderer.cellRendererPointer,
			func = staticCellDataFunc,
			func_data = StableRef.create { layout: CellLayout, cell: CellRenderer, treeModel: TreeModel, iter: TreeIter ->
				function(layout, cell, treeModel, iter)
				Unit
			}.asCPointer(),
			destroy = staticCFunction { void: gpointer? ->
				void?.asStableRef<Any>()?.dispose()
				Unit
			}
		)
	}

	fun packStart(cellRenderer: CellRenderer, expand: Boolean) {
		gtk_cell_layout_pack_start(cellLayoutHolder.ptr, cellRenderer.cellRendererPointer, expand.gtk)
	}

	fun packEnd(cellRenderer: CellRenderer, expand: Boolean) {
		gtk_cell_layout_pack_end(cellLayoutHolder.ptr, cellRenderer.cellRendererPointer, expand.gtk)
	}

	val area: CellArea?
		get() = gtk_cell_layout_get_area(cellLayoutHolder.ptr).wrap()

	val cells: Sequence<CellRenderer>
		get() = gtk_cell_layout_get_cells(cellLayoutHolder.ptr).asKSequence<GtkCellRenderer, CellRenderer> {
			it.wrap()
		}

	fun reorder(cell: CellRenderer, position: Int) {
		gtk_cell_layout_reorder(cellLayoutHolder.ptr, cell.cellRendererPointer, position)
	}

	fun clear() {
		gtk_cell_layout_clear(cellLayoutHolder.ptr)
	}

	fun addAttribute(cell: CellRenderer, attribute: String, column: Int) {
		gtk_cell_layout_add_attribute(cellLayoutHolder.ptr, cell.cellRendererPointer, attribute, column)
	}

	fun clearAttributes(cellRenderer: CellRenderer) {
		gtk_cell_layout_clear_attributes(cellLayoutHolder.ptr, cellRenderer.cellRendererPointer)
	}


}