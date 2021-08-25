package nativex.gtk

import glib.gpointer
import gobject.GCallback
import gobject.GObject
import gtk.*
import kotlinx.cinterop.*
import nativex.Closeable
import nativex.ClosedException
import nativex.glib.asSequence
import nativex.glib.bool
import nativex.glib.reinterpretOrNull
import nativex.gobject.KGObject
import nativex.gobject.Signals
import nativex.gobject.addSignalCallback

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
open class TreeModel(
	val treeModelPointer: CPointer<GtkTreeModel>
) : KGObject(treeModelPointer.reinterpret()) {
	fun getPath(iter: TreeIter): TreePath =
		TreePath(gtk_tree_model_get_path(treeModelPointer, iter.treeIterPointer)!!)

	fun iterHasChild(iter: TreeIter): Boolean =
		gtk_tree_model_iter_has_child(treeModelPointer, iter.treeIterPointer).bool

	fun addOnRowChangedCallback(action: (RowChanged) -> Unit) =
		addSignalCallback(Signals.ROW_CHANGED, action, RowChanged.staticCallback)

	fun addOnRowDeletedCallback(action: (RowDeleted) -> Unit) =
		addSignalCallback(Signals.ROW_DELETED, action, RowDeleted.staticCallback)

	fun addOnRowHasChildToggledCallback(action: (RowHasChildToggled) -> Unit) =
		addSignalCallback(Signals.ROW_HAS_CHILD_TOGGLED, action, RowHasChildToggled.staticCallback)

	fun addOnRowInsertedCallback(action:(RowInserted)->Unit) =
		addSignalCallback(Signals.ROW_INSERTED,action,RowInserted.staticCallback)

	fun addOnReorderedCallback(action:(RowsReordered)->Unit) =
		addSignalCallback(Signals.ROWS_REORDERED,action,RowsReordered.staticCallback)

	fun getIter(path: TreePath): TreeIter = TreeIter(memScoped {
		val iter = cValue<GtkTreeIter>()
		gtk_tree_model_get_iter(treeModelPointer, iter.ptr, path.treePathPointer)
		iter.ptr
	})

	data class RowChanged(
		val path: TreePath,
		val iter: TreeIter,
	) {
		companion object {
			val staticCallback: GCallback =
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
			val staticCallback: GCallback =
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
			val staticCallback: GCallback =
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
			val staticCallback: GCallback =
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
			val staticCallback: GCallback =
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

	enum class Flags(val key: Int, val gtk: GtkTreeModelFlags) {
		ITERS_PERSIST(0, GTK_TREE_MODEL_ITERS_PERSIST),
		LIST_ONLY(1, GTK_TREE_MODEL_LIST_ONLY);

		companion object {
			fun valueOf(key: Int) =
				values().find { it.key == key }


			fun valueOf(gtk: GtkTreeModelFlags) =
				values().find { it.gtk == gtk }
		}
	}

	data class TreeIter(
		val treeIterPointer: CPointer<GtkTreeIter>
	) {
		constructor() : this(memScoped { alloc<GtkTreeIter>().ptr })

		val stamp: Int
			get() = treeIterPointer.pointed.stamp

		var userData: Any? = null
		var userData1: Any? = null
		var userData3: Any? = null
	}

	class TreePath(
		val treePathPointer: CPointer<GtkTreePath>
	) : Closeable {
		private var isClosed = false
		private inline fun getCloseException() =
			ClosedException("TreePath has been freed")

		fun free() {
			if (isClosed) throw getCloseException()
			gtk_tree_path_free(treePathPointer)
			isClosed = true
		}


		val indices: Sequence<Int>
			get() {
				if (isClosed) throw getCloseException()
				return gtk_tree_path_get_indices(treePathPointer).asSequence(depth)
			}

		val depth: Int
			get() {
				if (isClosed) throw getCloseException()
				return gtk_tree_path_get_depth(treePathPointer)
			}

		constructor() : this(gtk_tree_path_new()!!)

		constructor(vararg indices: Int) : this(
			gtk_tree_path_new_from_indicesv(
				indices.toCValues(),
				indices.size.toULong()
			)!!
		)

		override fun close() {
			free()
		}
	}

	companion object {
		fun TreeModel.asKObject(): KGObject? =
			this.treeModelPointer.reinterpretOrNull<GObject>().wrap()
	}
}