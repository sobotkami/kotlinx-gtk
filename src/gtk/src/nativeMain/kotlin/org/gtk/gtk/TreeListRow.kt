package org.gtk.gtk

import gobject.GObject
import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gio.ListModel
import org.gtk.gio.ListModel.Companion.wrap
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.KGObject

/**
 * gtk-kt
 *
 * 01 / 09 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.TreeListRow.html">
 *     GtkTreeListRow</a>
 */
class TreeListRow(val treeListRowPointer: CPointer<GtkTreeListRow>) : KGObject(treeListRowPointer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListRow.get_child_row.html">
	 *     gtk_tree_list_row_get_child_row</a>
	 */
	fun getChildRow(position: UInt): TreeListRow? =
		gtk_tree_list_row_get_child_row(treeListRowPointer, position).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListRow.get_children.html">
	 *     gtk_tree_list_row_get_children</a>
	 */
	fun getChildren(): ListModel? =
		gtk_tree_list_row_get_children(treeListRowPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListRow.get_depth.html">
	 *     gtk_tree_list_row_get_depth</a>
	 */
	val depth: UInt
		get() = gtk_tree_list_row_get_depth(treeListRowPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListRow.get_expanded.html">
	 *     gtk_tree_list_row_get_expanded</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListRow.set_expanded.html">
	 *     gtk_tree_list_row_set_expanded</a>
	 */
	var expanded: Boolean
		get() = gtk_tree_list_row_get_expanded(treeListRowPointer).bool
		set(value) = gtk_tree_list_row_set_expanded(treeListRowPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListRow.get_item.html">
	 *     gtk_tree_list_row_get_item</a>
	 */
	val item: KGObject?
		get() = gtk_tree_list_row_get_item(treeListRowPointer)?.reinterpret<GObject>().wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListRow.get_parent.html">
	 *     gtk_tree_list_row_get_parent</a>
	 */
	val parent: TreeListRow?
		get() = gtk_tree_list_row_get_parent(treeListRowPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListRow.get_position.html">
	 *     gtk_tree_list_row_get_position</a>
	 */
	val position: UInt
		get() = gtk_tree_list_row_get_position(treeListRowPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListRow.is_expandable.html">
	 *     gtk_tree_list_row_is_expandable</a>
	 */
	val isExpandable: Boolean
		get() = gtk_tree_list_row_is_expandable(treeListRowPointer).bool

	companion object {
		inline fun CPointer<GtkTreeListRow>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkTreeListRow>.wrap() =
			TreeListRow(this)
	}

}