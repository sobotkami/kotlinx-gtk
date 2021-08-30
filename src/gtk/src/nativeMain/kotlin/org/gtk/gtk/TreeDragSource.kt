package org.gtk.gtk

import gtk.GtkTreeDragSource
import gtk.gtk_tree_drag_source_drag_data_delete
import gtk.gtk_tree_drag_source_drag_data_get
import gtk.gtk_tree_drag_source_row_draggable
import kotlinx.cinterop.CPointer
import org.gtk.gdk.ContentProvider
import org.gtk.gdk.ContentProvider.Companion.wrap
import org.gtk.glib.bool

/**
 * gtk-kt
 *
 * 29 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/iface.TreeDragSource.html">
 *     GtkTreeDragSource</a>
 */
interface TreeDragSource {

	val treeDragSourcePointer: CPointer<GtkTreeDragSource>

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeDragSource.drag_data_delete.html">
	 *     gtk_tree_drag_source_drag_data_delete</a>
	 */
	fun dragDataDelete(path: TreeModel.TreePath): Boolean =
		gtk_tree_drag_source_drag_data_delete(treeDragSourcePointer, path.treePathPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeDragSource.drag_data_get.html">
	 *     gtk_tree_drag_source_drag_data_get</a>
	 */
	fun dataGet(path: TreeModel.TreePath): ContentProvider? =
		gtk_tree_drag_source_drag_data_get(treeDragSourcePointer, path.treePathPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeDragSource.row_draggable.html">
	 *     gtk_tree_drag_source_row_draggable</a>
	 */
	fun isRowDraggable(path: TreeModel.TreePath): Boolean =
		gtk_tree_drag_source_row_draggable(treeDragSourcePointer, path.treePathPointer).bool
}