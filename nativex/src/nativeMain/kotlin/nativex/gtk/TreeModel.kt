package nativex.gtk

import gtk.*
import kotlinx.cinterop.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
open class TreeModel internal constructor(
	internal val pointer: CPointer<GtkTreeModel>
) {

	@ExperimentalCoroutinesApi
	val rowChangedSignal: Flow<RowChanged> by lazy {
		pointer.reinterpret<GObject>().callbackSignalFlow(
			Signals.ROW_CHANGED,
			RowChanged.staticCallback
		)
	}

	@ExperimentalCoroutinesApi
	val rowDeletedSignal: Flow<RowDeleted> by lazy {
		pointer.reinterpret<GObject>().callbackSignalFlow(
			Signals.ROW_DELETED,
			RowDeleted.staticCallback
		)
	}

	@ExperimentalCoroutinesApi
	val rowHasChildToggled: Flow<RowHasChildToggled> by lazy {
		pointer.reinterpret<GObject>().callbackSignalFlow(
			Signals.ROW_HAS_CHILD_TOGGLED,
			RowHasChildToggled.staticCallback
		)
	}

	@ExperimentalCoroutinesApi
	val rowInsertedSignal: Flow<RowInserted> by lazy {
		pointer.reinterpret<GObject>().callbackSignalFlow(
			Signals.ROW_INSERTED,
			RowInserted.staticCallback
		)
	}

	@ExperimentalCoroutinesApi
	val rowsReordered: Flow<RowsReordered> by lazy {
		pointer.reinterpret<GObject>().callbackSignalFlow(
			Signals.ROWS_REORDERED,
			RowsReordered.staticCallback
		)
	}

	data class RowChanged(
		val path: TreePath,
		val iter: TreeIter,
	) {
		companion object {
			internal val staticCallback: GCallback =
				staticCFunction { _: gpointer?, path: CPointer<GtkTreePath>, iter: CPointer<GtkTreeIter>, data: gpointer? ->
					data?.asStableRef<(RowChanged) -> Unit>()?.get()
						?.invoke(
							RowChanged(
								TreePath(path),
								TreeIter(iter)
							)
						)
					Unit
				}.reinterpret()
		}
	}

	data class RowDeleted(
		val path: TreePath
	) {
		companion object {
			internal val staticCallback: GCallback =
				staticCFunction { _: gpointer?, path: CPointer<GtkTreePath>, data: gpointer? ->
					data?.asStableRef<(RowDeleted) -> Unit>()?.get()
						?.invoke(
							RowDeleted(TreePath(path))
						)
					Unit
				}.reinterpret()

		}
	}

	data class RowHasChildToggled(
		val path: TreePath,
		val iter: TreeIter
	) {
		companion object {
			internal val staticCallback: GCallback =
				staticCFunction { _: gpointer?, path: CPointer<GtkTreePath>, iter: CPointer<GtkTreeIter>, data: gpointer? ->
					data?.asStableRef<(RowHasChildToggled) -> Unit>()
						?.get()
						?.invoke(
							RowHasChildToggled(
								TreePath(path),
								TreeIter(iter)
							)
						)
					Unit
				}.reinterpret()
		}
	}

	data class RowInserted(
		val path: TreePath,
		val iter: TreeIter
	) {
		companion object {
			internal val staticCallback: GCallback =
				staticCFunction { _: gpointer?, path: CPointer<GtkTreePath>, iter: CPointer<GtkTreeIter>, data: gpointer? ->
					data?.asStableRef<(RowInserted) -> Unit>()?.get()
						?.invoke(
							RowInserted(
								TreePath(path),
								TreeIter(iter)
							)
						)
					Unit
				}.reinterpret()
		}
	}

	data class RowsReordered(
		val path: TreePath,
		val iter: TreeIter?,
		val newOrder: Sequence<Pair<Int, Int>>
	) {
		companion object {
			internal val staticCallback: GCallback =
				staticCFunction { _: gpointer?, path: CPointer<GtkTreePath>, iter: CPointer<GtkTreeIter>, newOrder: gpointer, data: gpointer? ->
					data?.asStableRef<(RowsReordered) -> Unit>()
						?.get()
						?.invoke(
							RowsReordered(
								TreePath(path),
								TreeIter(iter),
								newOrder.reinterpret<CPointerVar<IntVar>>()
									.asSequence()
									.mapIndexed { index, cPointer ->
										index to cPointer.pointed.value
									}
							)
						)
					Unit
				}.reinterpret()

		}
	}

	enum class Flags(val key: Int, internal val gtk: GtkTreeModelFlags) {
		ITERS_PERSIST(0, GTK_TREE_MODEL_ITERS_PERSIST),
		LIST_ONLY(1, GTK_TREE_MODEL_LIST_ONLY);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }


			internal fun valueOf(gtk: GtkTreeModelFlags) =
				values().find { it.gtk == gtk }
		}
	}

	data class TreeIter internal constructor(
		internal val treeIterPointer: CPointer<GtkTreeIter>
	) {
		constructor() : this(memScoped { alloc<GtkTreeIter>().ptr })

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