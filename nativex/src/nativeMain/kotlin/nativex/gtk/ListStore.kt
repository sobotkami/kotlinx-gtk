package nativex.gtk

import gtk.*
import kotlinx.cinterop.*
import nativex.gobject.KObject
import nativex.glib.bool
import nativex.glib.toCArray
import nativex.gobject.KGType
import nativex.gobject.KGType.Companion.toCArray
import nativex.gobject.KGValue
import nativex.gtk.TreeModel.TreeIter

class ListStore constructor(
	val listStorePointer: CPointer<GtkListStore>
) : KObject(listStorePointer.reinterpret()) {
	constructor(vararg types: KGType) : this(
		memScoped {
			gtk_list_store_newv(
				types.size,
				types.toCArray(this)
			)!!
		}
	)

	fun insertBefore(iter: TreeIter, sibling: TreeIter) {
		gtk_list_store_insert_before(listStorePointer, iter.treeIterPointer, sibling.treeIterPointer)
	}

	fun insertAfter(iter: TreeIter, sibling: TreeIter) {
		gtk_list_store_insert_after(listStorePointer, iter.treeIterPointer, sibling.treeIterPointer)
	}

	fun clear() {
		gtk_list_store_clear(listStorePointer)
	}

	fun isValid(iter: TreeIter): Boolean =
		gtk_list_store_iter_is_valid(listStorePointer, iter.treeIterPointer).bool

	fun reorder(newOrder: Array<Int>) {
		memScoped {
			gtk_list_store_reorder(listStorePointer, newOrder.toCArray(this).pointed.value)
		}
	}

	fun swap(a: TreeIter, b: TreeIter) {
		gtk_list_store_swap(listStorePointer, a.treeIterPointer, b.treeIterPointer)
	}

	fun moveBefore(iter: TreeIter, position: TreeIter) {
		gtk_list_store_move_before(listStorePointer, iter.treeIterPointer, position.treeIterPointer)
	}

	fun moveAfter(iter: TreeIter, position: TreeIter) {
		gtk_list_store_move_after(listStorePointer, iter.treeIterPointer, position.treeIterPointer)
	}

	fun insert(iter: TreeIter, position: Int) {
		gtk_list_store_insert(listStorePointer, iter.treeIterPointer, position)
	}

	fun setColumnTypes(vararg types: KGType) {
		memScoped {
			gtk_list_store_set_column_types(
				listStorePointer, types.size, types.toCArray(this)
			)
		}
	}

	fun prepend(iter: TreeIter) {
		gtk_list_store_prepend(listStorePointer, iter.treeIterPointer)
	}

	fun append(iter: TreeIter) {
		gtk_list_store_append(listStorePointer, iter.treeIterPointer)
	}

	fun setValue(iter: TreeIter, column: Int, value: KGValue?) {
		gtk_list_store_set_value(listStorePointer, iter.treeIterPointer, column, value?.pointer)
	}

	fun remove(iter: TreeIter) {
		gtk_list_store_remove(listStorePointer, iter.treeIterPointer)
	}

	fun asTreeModel(): TreeModel =
		TreeModel(listStorePointer.reinterpret())
}