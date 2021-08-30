package org.gtk.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.cValue
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.reinterpret
import org.gtk.glib.bool
import org.gtk.gtk.TreeModel.Companion.wrap
import org.gtk.gtk.TreeModel.TreeIter
import org.gtk.gtk.TreeModel.TreeIter.Companion.wrap
import org.gtk.gtk.TreeModel.TreePath
import org.gtk.gtk.TreeModel.TreePath.Companion.wrap

/**
 * gtk-kt
 *
 * 29 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.TreeModelSort.html">GtkTreeModelSort</a>
 */
class TreeModelSort(val treeModelSortPointer: CPointer<GtkTreeModelSort>) : TreeModel, TreeSortable, TreeDragSource {

	override val treeDragSourcePointer: CPointer<GtkTreeDragSource> by lazy { treeModelSortPointer.reinterpret() }
	override val treeModelPointer: CPointer<GtkTreeModel> by lazy { treeModelSortPointer.reinterpret() }
	override val treeSortablePointer: CPointer<GtkTreeSortable> by lazy { treeModelSortPointer.reinterpret() }

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.TreeModelSort.new_with_model.html">
	 *     gtk_tree_model_sort_new_with_model</a>
	 */
	constructor(model: TreeModel) : this(gtk_tree_model_sort_new_with_model(model.treeModelPointer)!!.reinterpret())

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelSort.clear_cache.html">
	 *     gtk_tree_model_sort_clear_cache</a>
	 */
	fun clearCache() {
		gtk_tree_model_sort_clear_cache(treeModelSortPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelSort.convert_child_iter_to_iter.html">
	 *     gtk_tree_model_sort_convert_child_iter_to_iter</a>
	 */
	fun convertChildIterToIter(childIter: TreeIter): TreeIter? =
		memScoped {
			val sortIter = cValue<GtkTreeIter>()
			if (
				gtk_tree_model_sort_convert_child_iter_to_iter(
					treeModelSortPointer, sortIter, childIter.treeIterPointer
				).bool
			)
				sortIter.ptr.wrap()
			else
				null
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelSort.convert_child_path_to_path.html">
	 *     gtk_tree_model_sort_convert_child_path_to_path</a>
	 */
	fun convertChildPathToPath(childPath: TreePath): TreePath? =
		gtk_tree_model_sort_convert_child_path_to_path(treeModelSortPointer, childPath.treePathPointer).wrap()


	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelSort.convert_iter_to_child_iter.html">
	 *     gtk_tree_model_sort_convert_iter_to_child_iter</a>
	 */
	fun convertIterToChildIter(sortedIter: TreeIter): TreeIter =
		memScoped {
			val childIter = cValue<GtkTreeIter>()
			gtk_tree_model_sort_convert_iter_to_child_iter(treeModelSortPointer, childIter, sortedIter.treeIterPointer)
			childIter.ptr.wrap()
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelSort.convert_path_to_child_path.html">
	 *     gtk_tree_model_sort_convert_path_to_child_path</a>
	 */
	fun convertPathToChildPath(sortedPath: TreePath) =
		gtk_tree_model_sort_convert_path_to_child_path(treeModelSortPointer, sortedPath.treePathPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelSort.get_model.html">
	 *     gtk_tree_model_sort_get_model</a>
	 */
	val model: TreeModel
		get() = gtk_tree_model_sort_get_model(treeModelSortPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelSort.iter_is_valid.html">
	 *     gtk_tree_model_sort_iter_is_valid</a>
	 */
	fun iterIsValid(iter: TreeIter): Boolean =
		gtk_tree_model_sort_iter_is_valid(treeModelSortPointer, iter.treeIterPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelSort.reset_default_sort_func.html">
	 *     gtk_tree_model_sort_reset_default_sort_func</a>
	 */
	fun resetDefaultSortFunction() =
		gtk_tree_model_sort_reset_default_sort_func(treeModelSortPointer)
}