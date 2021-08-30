package org.gtk.gtk

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