package org.gtk.gtk
import glib.gpointer
import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.staticCFunction
import org.gtk.glib.asKSequence
import org.gtk.glib.gtk
import org.gtk.gtk.CellArea.Companion.wrap
import org.gtk.gtk.TreeModel.Companion.wrap
import org.gtk.gtk.TreeModel.TreeIter
import org.gtk.gtk.cellrenderer.CellRenderer
import org.gtk.gtk.cellrenderer.CellRenderer.Companion.wrap

interface CellLayout {
	companion object {

		 val staticCellDataFunc =
			staticCFunction { cellLayout: CPointer<GtkCellLayout>?,
			                  cell: CPointer<GtkCellRenderer>?,
			                  treeModel: CPointer<GtkTreeModel>?,
			                  iter: CPointer<GtkTreeIter>?,
			                  data: gpointer? ->
				data?.asStableRef<(CellLayout, CellRenderer, TreeModel, TreeIter) -> Unit>()?.get()?.invoke(
					Impl(cellLayout!!),
					CellRenderer(cell!!),
					treeModel!!.wrap(),
					TreeIter(iter!!)
				)
				Unit
			}
	}

	class Impl(override val cellLayoutHolder: CPointer<GtkCellLayout>) : CellLayout

	val cellLayoutHolder: CPointer<GtkCellLayout>


	fun setCellDataFunc(
		renderer: CellRenderer, function: (
			CellLayout,
			CellRenderer,
			TreeModel,
			TreeIter
		) -> Unit
	) {
		gtk_cell_layout_set_cell_data_func(
			cell_layout = cellLayoutHolder,
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
		gtk_cell_layout_pack_start(cellLayoutHolder, cellRenderer.cellRendererPointer, expand.gtk)
	}

	fun packEnd(cellRenderer: CellRenderer, expand: Boolean) {
		gtk_cell_layout_pack_end(cellLayoutHolder, cellRenderer.cellRendererPointer, expand.gtk)
	}

	val area: CellArea?
		get() = gtk_cell_layout_get_area(cellLayoutHolder).wrap()

	val cells: Sequence<CellRenderer>
		get() = gtk_cell_layout_get_cells(cellLayoutHolder).asKSequence<GtkCellRenderer, CellRenderer> {
			it.wrap()
		}

	fun reorder(cell: CellRenderer, position: Int) {
		gtk_cell_layout_reorder(cellLayoutHolder, cell.cellRendererPointer, position)
	}

	fun clear() {
		gtk_cell_layout_clear(cellLayoutHolder)
	}

	fun addAttribute(cell: CellRenderer, attribute: String, column: Int) {
		gtk_cell_layout_add_attribute(cellLayoutHolder, cell.cellRendererPointer, attribute, column)
	}

	fun clearAttributes(cellRenderer: CellRenderer) {
		gtk_cell_layout_clear_attributes(cellLayoutHolder, cellRenderer.cellRendererPointer)
	}


}