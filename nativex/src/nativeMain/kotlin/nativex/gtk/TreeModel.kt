package nativex.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.pointed
import kotlinx.coroutines.flow.Flow

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
open class TreeModel internal constructor(
	internal val pointer: CPointer<GtkTreeModel>
) {

	val rowChangedSignal: Flow<RowChanged>
		get() {
			TODO()
		}

	val rowDeletedSignal: Flow<RowDeleted>
		get() {
			TODO()
		}

	val rowHasChildToggled: Flow<RowHasChildToggled>
		get() {
			TODO()
		}

	val rowInsertedSignal: Flow<RowInserted>
		get() {
			TODO()
		}

	val rowsReordered: Flow<RowsReordered>
		get() {
			TODO()
		}

	data class RowChanged(
		val path: GtkTreePath,
		val iter: TreeIter,
	)

	data class RowDeleted(
		val path: GtkTreePath
	)

	data class RowHasChildToggled(
		val path: GtkTreePath,
		val iter: TreeIter
	)

	data class RowInserted(
		val path: GtkTreePath,
		val iter: TreeIter
	)

	data class RowsReordered(
		val path: GtkTreePath,
		val iter: TreeIter?,
		val newOrder: Sequence<Pair<Int, Int>>
	)

	enum class Flags(val key: Int, internal val gtk: GtkTreeModelFlags) {
		ITERS_PERSIST(0, GTK_TREE_MODEL_ITERS_PERSIST),
		LIST_ONLY(1, GTK_TREE_MODEL_LIST_ONLY);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }

			@ExperimentalUnsignedTypes
			internal fun valueOf(gtk: GtkTreeModelFlags) =
				values().find { it.gtk == gtk }
		}
	}

	data class TreeIter internal constructor(
		internal val treeIterPointer: CPointer<GtkTreeIter>
	) {
		val stamp: Int
			get() = treeIterPointer.pointed.stamp

		var userData: Any? = null
		var userData1: Any? = null
		var userData3: Any? = null
	}

	data class TreePath internal constructor(
		internal val treePathPointer: CPointer<GtkTreePath>
	) {

	}

}