package org.gtk

import gtk.*
import kotlinx.cinterop.*
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.KGObject
import org.gtk.gobject.KGType
import org.gtk.gobject.KGType.Companion.toCArray
import org.gtk.gobject.KGValue.Companion.wrap
import org.gtk.gobject.staticDestroyStableRefFunction
import org.gtk.gtk.TreeDragSource
import org.gtk.gtk.TreeModel
import org.gtk.gtk.TreeModel.Companion.wrap
import org.gtk.gtk.TreeModel.TreeIter.Companion.wrap
import org.gtk.gtk.TreeModel.TreePath.Companion.wrap
import org.gtk.gtk.TreeModelFilterModifyFunction
import org.gtk.gtk.TreeModelFilterVisibleFunction

/**
 * gtk-kt
 *
 * 01 / 09 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.TreeModelFilter.html">
 *     GtkTreeModelFilter</a>
 */
class TreeModelFilter(val treeModelFilterPointer: CPointer<GtkTreeModelFilter>) :
	KGObject(treeModelFilterPointer.reinterpret()), TreeDragSource,
	TreeModel {
	override val treeDragSourcePointer: CPointer<GtkTreeDragSource> by lazy { treeModelFilterPointer.reinterpret() }
	override val treeModelPointer: CPointer<GtkTreeModel> by lazy { treeModelFilterPointer.reinterpret() }

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelFilter.clear_cache.html">
	 *     gtk_tree_model_filter_clear_cache</a>
	 */
	fun clearCache() {
		gtk_tree_model_filter_clear_cache(treeModelFilterPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelFilter.convert_child_iter_to_iter.html">
	 *     gtk_tree_model_filter_convert_child_iter_to_iter</a>
	 */
	fun convertChildIterToIter(childIter: TreeModel.TreeIter): TreeModel.TreeIter? =
		memScoped {
			val sortIter = cValue<GtkTreeIter>()
			if (
				gtk_tree_model_filter_convert_child_iter_to_iter(
					treeModelFilterPointer, sortIter, childIter.treeIterPointer
				).bool
			)
				sortIter.ptr.wrap()
			else
				null
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelFilter.convert_child_path_to_path.html">
	 *     gtk_tree_model_filter_convert_child_path_to_path</a>
	 */
	fun convertChildPathToPath(childPath: TreeModel.TreePath): TreeModel.TreePath? =
		gtk_tree_model_filter_convert_child_path_to_path(treeModelFilterPointer, childPath.treePathPointer).wrap()


	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelFilter.convert_iter_to_child_iter.html">
	 *     gtk_tree_model_filter_convert_iter_to_child_iter</a>
	 */
	fun convertIterToChildIter(sortedIter: TreeModel.TreeIter): TreeModel.TreeIter =
		memScoped {
			val childIter = cValue<GtkTreeIter>()
			gtk_tree_model_filter_convert_iter_to_child_iter(
				treeModelFilterPointer,
				childIter,
				sortedIter.treeIterPointer
			)
			childIter.ptr.wrap()
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelFilter.convert_path_to_child_path.html">
	 *     gtk_tree_model_filter_convert_path_to_child_path</a>
	 */
	fun convertPathToChildPath(sortedPath: TreeModel.TreePath) =
		gtk_tree_model_filter_convert_path_to_child_path(treeModelFilterPointer, sortedPath.treePathPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelFilter.get_model.html">
	 *     gtk_tree_model_filter_get_model</a>
	 */
	val model: TreeModel
		get() = gtk_tree_model_filter_get_model(treeModelFilterPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelFilter.refilter.html">
	 *     gtk_tree_model_filter_refilter</a>
	 */
	fun refilter() {
		gtk_tree_model_filter_refilter(treeModelFilterPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelFilter.set_modify_func.html">
	 *     gtk_tree_model_filter_set_modify_func</a>
	 */
	fun setModifyFunction(types: Array<KGType>, function: TreeModelFilterModifyFunction) {
		memScoped {
			gtk_tree_model_filter_set_modify_func(
				treeModelFilterPointer,
				types.size,
				types.toCArray(this),
				staticTreeModelFilterModifyFunction,
				StableRef.create(function).asCPointer(),
				staticDestroyStableRefFunction
			)
		}
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelFilter.set_visible_column.html">
	 *     gtk_tree_model_filter_set_visible_column</a>
	 */
	fun setVisibleColumn(column: Int) {
		gtk_tree_model_filter_set_visible_column(treeModelFilterPointer, column)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeModelFilter.set_visible_func.html">
	 *     gtk_tree_model_filter_set_visible_func</a>
	 */
	fun setVisibleFunction(function: TreeModelFilterVisibleFunction) {
		gtk_tree_model_filter_set_visible_func(
			treeModelFilterPointer,
			staticTreeModelFilterVisibleFunction,
			StableRef.create(function).asCPointer(),
			staticDestroyStableRefFunction
		)
	}

	companion object {
		private val staticTreeModelFilterModifyFunction: GtkTreeModelFilterModifyFunc =
			staticCFunction { _, iter, value, column, data ->
				data?.asStableRef<TreeModelFilterModifyFunction>()?.get()?.invoke(
					iter!!.wrap(),
					value!!.wrap(),
					column
				)
				Unit
			}

		private val staticTreeModelFilterVisibleFunction: GtkTreeModelFilterVisibleFunc =
			staticCFunction { _, iter, data ->
				data?.asStableRef<TreeModelFilterVisibleFunction>()?.get()?.invoke(
					iter!!.wrap(),
				).gtk
			}

	}
}