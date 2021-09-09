package org.gtk.gtk

import org.gtk.gdk.Clipboard
import org.gtk.gdk.Paintable
import org.gtk.gio.ListModel
import org.gtk.gobject.KGObject
import org.gtk.gobject.KGValue
import org.gtk.gtk.widgets.TextView

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

/**
 * @see <a href="https://docs.gtk.org/gtk4/callback.TextTagTableForeach.html">TextTagTableForeach</a>
 */
typealias TextTagTableForeachFunction = (TextTag) -> Unit

typealias TextTagTableTagChangedFunction = (tag: TextTag, sizeChanged: Boolean) -> Unit

typealias TextBufferApplyTagFunction = (tag: TextTag, start: TextIter, end: TextIter) -> Unit

typealias TextBufferDeleteRangeFunction = (start: TextIter, end: TextIter) -> Unit

typealias TextBufferInsertChildAnchorFunction = (location: TextIter, anchor: TextChildAnchor) -> Unit

typealias TextBufferInsertPaintableFunction = (location: TextIter, paintable: Paintable) -> Unit

typealias TextBufferInsertTextFunction = (location: TextIter, text: String, length: Int) -> Unit

typealias TextBufferMarkDeletedFunction = (mark: TextMark) -> Unit

typealias TextBufferMarkSetFunction = (location: TextIter, mark: TextMark) -> Unit

typealias TextBufferPasteDoneFunction = (clipboard: Clipboard) -> Unit

typealias TextBufferRemoveTagFunction = (tag: TextTag, start: TextIter, end: TextIter) -> Unit