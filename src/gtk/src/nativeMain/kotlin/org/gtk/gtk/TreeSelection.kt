package org.gtk.gtk

import gtk.*
import kotlinx.cinterop.*
import org.gtk.glib.WrappedKList
import org.gtk.glib.WrappedKList.Companion.asWrappedKList
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.KGObject
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback
import org.gtk.gobject.staticDestroyStableRefFunction
import org.gtk.gtk.TreeModel.Companion.wrap
import org.gtk.gtk.TreeModel.TreeIter.Companion.wrap
import org.gtk.gtk.TreeModel.TreePath.Companion.wrap
import org.gtk.gtk.common.enums.SelectionMode
import org.gtk.gtk.widgets.TreeView

/**
 * gtk-kt
 *
 * 28 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.TreeSelection.html">
 *     GtkTreeSelection</a>
 */
class TreeSelection(val treeSelectionPointer: CPointer<GtkTreeSelection>) :
	KGObject(treeSelectionPointer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.count_selected_rows.html">
	 *     gtk_tree_selection_count_selected_rows</a>
	 */
	val selectedRowCount: Int
		get() = gtk_tree_selection_count_selected_rows(treeSelectionPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.get_mode.html">
	 *     gtk_tree_selection_get_mode</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.set_mode.html">
	 *     gtk_tree_selection_set_mode</a>
	 */
	var mode: SelectionMode
		get() = SelectionMode.valueOf(gtk_tree_selection_get_mode(treeSelectionPointer))!!
		set(value) = gtk_tree_selection_set_mode(treeSelectionPointer, value.gtk)


	data class Selected(
		val model: TreeModel,
		val iter: TreeModel.TreeIter
	)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.get_selected.html">
	 *     gtk_tree_selection_get_selected</a>
	 */
	val selected: Selected?
		get() = memScoped {
			val model = cValue<CPointerVar<GtkTreeModel>>()
			val iter = cValue<GtkTreeIter>()
			if (gtk_tree_selection_get_selected(treeSelectionPointer, model, iter).bool)
				Selected(
					model.ptr.pointed.pointed!!.ptr.wrap(),
					TreeModel.TreeIter(iter.ptr)
				)
			else null
		}

	data class SelectedRows(
		val model: TreeModel,
		val rows: WrappedKList<TreeModel.TreePath>
	)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.get_selected_rows.html">
	 *     gtk_tree_selection_get_selected_rows</a>
	 */
	val selectedRows: SelectedRows
		get() = memScoped {
			val model = cValue<CPointerVar<GtkTreeModel>>()
			val gList = gtk_tree_selection_get_selected_rows(treeSelectionPointer, model)
			SelectedRows(
				model.ptr.pointed.pointed!!.ptr.wrap(),
				gList!!.asWrappedKList({ TreeModel.TreePath(reinterpret()) }, { treePathPointer })
			)
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.get_tree_view.html">
	 *     gtk_tree_selection_get_tree_view</a>
	 */
	val treeView: TreeView
		get() = TreeView(gtk_tree_selection_get_tree_view(treeSelectionPointer)!!)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.get_user_data.html">
	 *     gtk_tree_selection_get_user_data</a>
	 */
	val selectFunction: TreeSelectionFunction?
		get() = gtk_tree_selection_get_user_data(treeSelectionPointer)?.asStableRef<TreeSelectionFunction>()?.get()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.iter_is_selected.html">
	 *     gtk_tree_selection_iter_is_selected</a>
	 */
	fun iterIsSelected(iter: TreeModel.TreeIter): Boolean =
		gtk_tree_selection_iter_is_selected(treeSelectionPointer, iter.treeIterPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.path_is_selected.html">
	 *     gtk_tree_selection_path_is_selected</a>
	 */
	fun pathIsSelected(path: TreeModel.TreePath): Boolean =
		gtk_tree_selection_path_is_selected(treeSelectionPointer, path.treePathPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.select_all.html">
	 *     gtk_tree_selection_select_all</a>
	 */
	fun selectAll() {
		gtk_tree_selection_select_all(treeSelectionPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.select_iter.html">
	 *     gtk_tree_selection_select_iter</a>
	 */
	fun selectIter(iter: TreeModel.TreeIter) {
		gtk_tree_selection_select_iter(treeSelectionPointer, iter.treeIterPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.select_path.html">
	 *     gtk_tree_selection_select_path</a>
	 */
	fun selectPath(path: TreeModel.TreePath) {
		gtk_tree_selection_select_path(treeSelectionPointer, path.treePathPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.select_range.html">
	 *     gtk_tree_selection_select_range</a>
	 */
	fun selectRange(startPath: TreeModel.TreePath, endPath: TreeModel.TreePath) {
		gtk_tree_selection_select_range(treeSelectionPointer, startPath.treePathPointer, endPath.treePathPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.selected_foreach.html">
	 *     gtk_tree_selection_selected_foreach</a>
	 */
	fun forEachSelected(treeSelectionForEachFunction: TreeSelectionForEachFunction) {
		memScoped {
			val ref = StableRef.create(treeSelectionForEachFunction)
			gtk_tree_selection_selected_foreach(
				treeSelectionPointer,
				staticTreeSelectionForEachFunction,
				ref.asCPointer()
			)
			ref.dispose()
		}
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.set_select_function.html">
	 *     gtk_tree_selection_set_select_function</a>
	 */
	fun setSelectFunction(func: TreeSelectionFunction) {
		gtk_tree_selection_set_select_function(
			treeSelectionPointer,
			staticTreeSelectionFunction,
			StableRef.create(func).asCPointer(),
			staticDestroyStableRefFunction
		)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.unselect_all.html">
	 *     gtk_tree_selection_unselect_all</a>
	 */
	fun unselectAll() {
		gtk_tree_selection_unselect_all(treeSelectionPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.unselect_iter.html">
	 *     gtk_tree_selection_unselect_iter</a>
	 */
	fun unselectIter(iter: TreeModel.TreeIter) {
		gtk_tree_selection_unselect_iter(treeSelectionPointer, iter.treeIterPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.unselect_path.html">
	 *     gtk_tree_selection_unselect_path</a>
	 */
	fun unselectPath(path: TreeModel.TreePath) {
		gtk_tree_selection_unselect_path(treeSelectionPointer, path.treePathPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeSelection.unselect_range.html">
	 *     gtk_tree_selection_unselect_range</a>
	 */
	fun unselectRange(startPath: TreeModel.TreePath, endPath: TreeModel.TreePath) {
		gtk_tree_selection_unselect_range(treeSelectionPointer, startPath.treePathPointer, endPath.treePathPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.TreeSelection.changed.html">changed</a>
	 */
	fun addOnChangedCallback(action: () -> Unit) =
		addSignalCallback(Signals.CHANGED, action)

	companion object {
		private val staticTreeSelectionForEachFunction: GtkTreeSelectionForeachFunc =
			staticCFunction { _, path, iter, data ->
				data?.asStableRef<TreeSelectionForEachFunction>()?.get()?.invoke(path!!.wrap(), iter!!.wrap())
				Unit
			}

		private val staticTreeSelectionFunction: GtkTreeSelectionFunc =
			staticCFunction { _, model, path, pathCurrentlySelected, data ->
				data?.asStableRef<TreeSelectionFunction>()?.get()
					?.invoke(
						model!!.wrap(),
						path!!.wrap(),
						pathCurrentlySelected.bool
					).gtk
			}
	}
}