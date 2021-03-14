package kotlin.gtk.container

import gtk.GtkTreeIter
import gtk.GtkTreeView
import gtk.GtkTreeViewRowSeparatorFunc
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.TreeModel

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
class TreeView internal constructor(
	internal val treeViewPointer: CPointer<GtkTreeView>
) : Container(treeViewPointer.reinterpret()) {
}

typealias TreeViewRowSeparatorFunc = (TreeModel, TreeModel.Iter, Any) -> Unit