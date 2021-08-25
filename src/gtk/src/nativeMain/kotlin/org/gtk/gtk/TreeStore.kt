package org.gtk.gtk

import gtk.GtkTreeStore
import gtk.gtk_tree_store_append
import gtk.gtk_tree_store_newv
import gtk.gtk_tree_store_set_value
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.reinterpret
import org.gtk.gobject.KGObject
import org.gtk.gobject.KGType
import org.gtk.gobject.KGType.Companion.toCArray
import org.gtk.gobject.KGValue
import org.gtk.gtk.TreeModel.TreeIter

class TreeStore(
	val treeStorePointer: CPointer<GtkTreeStore>
) : KGObject(treeStorePointer.reinterpret()) {
	constructor(vararg types: KGType) : this(
		memScoped {
			gtk_tree_store_newv(
				types.size,
				types.toCArray(this)
			)!!
		}
	)

	fun append(iter: TreeIter?, iter2: TreeIter?) {
		gtk_tree_store_append(treeStorePointer, iter?.treeIterPointer, iter2?.treeIterPointer)
	}

	fun setValue(iter: TreeIter, column: Int, value: KGValue?) {
		gtk_tree_store_set_value(treeStorePointer, iter.treeIterPointer, column, value?.pointer)
	}

	fun asTreeModel(): TreeModel =
		TreeModel(treeStorePointer.reinterpret())

}