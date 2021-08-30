package org.gtk.gtk

import gtk.GtkTreeSortable
import kotlinx.cinterop.CPointer

/**
 * gtk-kt
 *
 * 29 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/iface.TreeSortable.html">GtkTreeSortable</a>
 */
interface TreeSortable {
	val treeSortablePointer: CPointer<GtkTreeSortable>

}