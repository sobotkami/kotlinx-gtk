package org.gtk.gtk

import gtk.*
import kotlinx.cinterop.*
import org.gtk.glib.bool
import org.gtk.glib.toCArray
import org.gtk.gobject.KGType
import org.gtk.gobject.KGType.Companion.toCArray
import org.gtk.gobject.KGValue
import org.gtk.gobject.KGValue.Companion.wrap
import org.gtk.gtk.TreeModel.TreeIter

/**
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.TreeStore.html">
 *     GtkTreeStore</a>
 */
class TreeStore(
	val treeStorePointer: CPointer<GtkTreeStore>
) : TreeModel {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.TreeStore.newv.html">
	 *     gtk_tree_store_newv</a>
	 */
	constructor(vararg types: KGType) : this(
		memScoped {
			gtk_tree_store_newv(
				types.size,
				types.toCArray(this)
			)!!
		}
	)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.append.html">
	 *     gtk_tree_store_append</a>
	 */
	fun append(iter: TreeIter?, iter2: TreeIter?) {
		gtk_tree_store_append(treeStorePointer, iter?.treeIterPointer, iter2?.treeIterPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.clear.html">
	 *     gtk_tree_store_clear</a>
	 */
	fun clear() {
		gtk_tree_store_clear(treeStorePointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.insert.html">
	 *     gtk_tree_store_insert</a>
	 */
	fun insert(iter: TreeIter, parent: TreeIter?, position: Int) {
		gtk_tree_store_insert(treeStorePointer, iter.treeIterPointer, parent?.treeIterPointer, position)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.insert_after.html">
	 *     gtk_tree_store_insert_after</a>
	 */
	fun insertAfter(iter: TreeIter, parent: TreeIter?, sibling: TreeIter?) {
		gtk_tree_store_insert_after(
			treeStorePointer,
			iter.treeIterPointer,
			parent?.treeIterPointer,
			sibling?.treeIterPointer
		)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.insert_before.html">
	 *     gtk_tree_store_insert_before</a>
	 */
	fun insertBefore(iter: TreeIter, parent: TreeIter?, sibling: TreeIter?) {
		gtk_tree_store_insert_before(
			treeStorePointer,
			iter.treeIterPointer,
			parent?.treeIterPointer,
			sibling?.treeIterPointer
		)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.insert_with_valuesv.html">
	 *     gtk_tree_store_insert_with_valuesv</a>
	 */
	fun insertWithValues(iter: TreeIter?, parent: TreeIter?, position: Int, pairs: Array<Pair<Int, KGValue>>) {
		memScoped {
			val size = pairs.size

			gtk_tree_store_insert_with_valuesv(
				treeStorePointer,
				iter?.treeIterPointer,
				parent?.treeIterPointer,
				position,
				allocArray(size) { index ->
					value = pairs[index].first
				},
				allocArray(size) { index ->
					pairs[index].second.copy(this.ptr.wrap())
				},
				size
			)
		}
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.is_ancestor.html">
	 *     gtk_tree_store_is_ancestor</a>
	 */
	fun isAncestor(iter: TreeIter, descendant: TreeIter): Boolean =
		gtk_tree_store_is_ancestor(treeStorePointer, iter.treeIterPointer, descendant.treeIterPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.iter_depth.html">
	 *     gtk_tree_store_iter_depth</a>
	 */
	fun iterDepth(iter: TreeIter): Int =
		gtk_tree_store_iter_depth(treeStorePointer, iter.treeIterPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.iter_is_valid.html">
	 *     gtk_tree_store_iter_is_valid</a>
	 */
	fun iterIsValid(iter: TreeIter): Boolean =
		gtk_tree_store_iter_is_valid(treeStorePointer, iter.treeIterPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.move_after.html">
	 *     gtk_tree_store_move_after</a>
	 */
	fun moveAfter(iter: TreeIter, position: TreeIter?) =
		gtk_tree_store_move_after(treeStorePointer, iter.treeIterPointer, position?.treeIterPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.move_before.html">
	 *     gtk_tree_store_move_before</a>
	 */
	fun moveBefore(iter: TreeIter, position: TreeIter?) =
		gtk_tree_store_move_before(treeStorePointer, iter.treeIterPointer, position?.treeIterPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.prepend.html">
	 *     gtk_tree_store_prepend</a>
	 */
	fun prepend(iter: TreeIter, parent: TreeIter?) =
		gtk_tree_store_prepend(treeStorePointer, iter.treeIterPointer, parent?.treeIterPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.remove.html">
	 *     gtk_tree_store_remove</a>
	 */
	fun remove(iter: TreeIter) =
		gtk_tree_store_remove(treeStorePointer, iter.treeIterPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.reorder.html">
	 *     gtk_tree_store_reorder</a>
	 */
	fun reorder(parent: TreeIter?, newOrder: Array<Int>) =
		memScoped {
			gtk_tree_store_reorder(
				treeStorePointer,
				parent?.treeIterPointer,
				newOrder.toCArray(this)
			)
		}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.set_column_types.html">
	 *     gtk_tree_store_set_column_types</a>
	 */
	fun setColumnTypes(types: Array<KGType>) {
		memScoped {
			gtk_tree_store_set_column_types(treeStorePointer, types.size, types.toCArray(memScope))
		}
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.set_value.html">
	 *     gtk_tree_store_set_value</a>
	 */
	fun setValue(iter: TreeIter, column: Int, value: KGValue?) {
		gtk_tree_store_set_value(treeStorePointer, iter.treeIterPointer, column, value?.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.set_valuesv.html">
	 *     gtk_tree_store_set_valuesv</a>
	 */
	fun setValues(iter: TreeIter, pairs: Array<Pair<Int, KGValue>>) {
		memScoped {
			val size = pairs.size
			gtk_tree_store_set_valuesv(
				treeStorePointer,
				iter.treeIterPointer,
				allocArray(size) { index ->
					value = pairs[index].first
				},
				allocArray(size) { index ->
					pairs[index].second.copy(this.ptr.wrap())
				},
				size
			)

		}
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeStore.swap.html">
	 *     gtk_tree_store_swap</a>
	 */
	fun swap(a: TreeIter, b: TreeIter) {
		gtk_tree_store_swap(treeStorePointer, a.treeIterPointer, b.treeIterPointer)
	}

	override val treeModelPointer: CPointer<GtkTreeModel> by lazy { treeStorePointer.reinterpret() }
}