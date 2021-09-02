package org.gtk.gtk

import org.gtk.gio.ListModel
import org.gtk.gobject.KGObject
import org.gtk.gobject.KGValue

/*
 * gtk-kt
 *
 * 29 / 08 / 2021
 */

/**
 * @see <a href="https://docs.gtk.org/gtk4/callback.TreeSelectionFunc.html">GtkTreeSelectionFunction</a>
 */
typealias TreeSelectionFunction = (
	model: TreeModel,
	path: TreeModel.TreePath,
	pathCurrentlySelected: Boolean
) -> Boolean

/**
 * @see <a href="https://docs.gtk.org/gtk4/callback.TreeSelectionForeachFunc.html">GtkTreeSelectionForEachFunction</a>
 */
typealias TreeSelectionForEachFunction = (
	path: TreeModel.TreePath,
	iter: TreeModel.TreeIter
) -> Unit

/**
 * @see <a href="https://docs.gtk.org/gtk4/callback.TreeModelFilterModifyFunc.html">TreeModelFilterModifyFunc</a>
 */
typealias TreeModelFilterModifyFunction = (
	iter: TreeModel.TreeIter,
	value: KGValue,
	column: Int
) -> Unit

/**
 * @see <a href="https://docs.gtk.org/gtk4/callback.TreeModelFilterVisibleFunc.html">TreeModelFilterVisibleFunc</a>
 */
typealias TreeModelFilterVisibleFunction = (
	iter: TreeModel.TreeIter,
) -> Boolean

/**
 * @see <a href="https://docs.gtk.org/gtk4/callback.TreeListModelCreateModelFunc.html">TreeListModelCreateModelFunc</a>
 */
typealias TreeListModelCreateModelFunction = (item: KGObject) -> ListModel?