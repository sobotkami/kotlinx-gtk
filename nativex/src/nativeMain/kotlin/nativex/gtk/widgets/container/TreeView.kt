package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import nativex.gtk.TreeModel
import nativex.gtk.TreeModel.TreeIter
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
class TreeView(
	 val treeViewPointer: CPointer<GtkTreeView>
) : Container(treeViewPointer.reinterpret()) {
	companion object {
		 val staticTreeViewRowSeparatorFunc: GtkTreeViewRowSeparatorFunc =
			staticCFunction { model: CPointer<GtkTreeModel>?,
			                  iter: CPointer<GtkTreeIter>?,
			                  data: gpointer? ->
				data?.asStableRef<TreeViewRowSeparatorFunc>()?.get()?.invoke(
					TreeModel(model!!),
					TreeIter(iter!!)
				)?.gtk ?: 0
			}
	}
}

typealias TreeViewRowSeparatorFunc = (TreeModel, TreeIter) -> Boolean