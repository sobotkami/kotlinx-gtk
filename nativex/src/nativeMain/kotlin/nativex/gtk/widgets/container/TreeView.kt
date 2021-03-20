package nativex.gtk.widgets.container

import gtk.GtkTreeView
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.TreeModel

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
class TreeView internal constructor(
	internal val treeViewPointer: CPointer<GtkTreeView>
) : Container(treeViewPointer.reinterpret())

typealias TreeViewRowSeparatorFunc = (TreeModel, TreeModel.TreeIter, Any) -> Unit