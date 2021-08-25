package org.gtk.gtk.widgets

import glib.gpointer
import gtk.GtkTreeIter
import gtk.GtkTreeModel
import gtk.GtkTreeView
import gtk.GtkTreeViewRowSeparatorFunc
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import org.gtk.glib.gtk
import org.gtk.gtk.TreeModel
import org.gtk.gtk.TreeModel.TreeIter

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
class TreeView(
	 val treeViewPointer: CPointer<GtkTreeView>
) : Widget(treeViewPointer.reinterpret()) {
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