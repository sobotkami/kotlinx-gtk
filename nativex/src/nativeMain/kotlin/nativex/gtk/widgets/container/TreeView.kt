package nativex.gtk.widgets.container

import gtk.GtkTreeIter
import gtk.GtkTreeModel
import gtk.GtkTreeView
import gtk.gpointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import nativex.gtk.TreeModel
import nativex.gtk.TreeModel.TreeIter

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
class TreeView internal constructor(
	internal val treeViewPointer: CPointer<GtkTreeView>
) : Container(treeViewPointer.reinterpret()) {
	companion object {
		internal val staticTreeViewRowSeparatorFunc =
			staticCFunction { model: CPointer<GtkTreeModel>?,
			                  iter: CPointer<GtkTreeIter>?,
			                  data: gpointer? ->
				data?.asStableRef<TreeViewRowSeparatorFunc>()?.get()?.invoke(
					TreeModel(model!!),
					TreeIter(iter!!)
				)
				Unit
			}
	}
}

typealias TreeViewRowSeparatorFunc = (TreeModel, TreeIter) -> Unit