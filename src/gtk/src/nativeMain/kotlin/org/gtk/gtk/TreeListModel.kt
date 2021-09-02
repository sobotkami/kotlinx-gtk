package org.gtk.gtk

import gio.GListModel
import gobject.GObject
import gtk.*
import kotlinx.cinterop.*
import org.gtk.gio.ListModel
import org.gtk.gio.ListModel.Companion.wrap
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.KGObject
import org.gtk.gobject.staticDestroyStableRefFunction
import org.gtk.gtk.TreeListRow.Companion.wrap

/**
 * gtk-kt
 *
 * 01 / 09 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.TreeListModel.html">
 *     GtkTreeListModel</a>
 */
class TreeListModel(val treeListModelPointer: CPointer<GtkTreeListModel>) :
	KGObject(treeListModelPointer.reinterpret()), ListModel {
	override val listModelPointer: CPointer<GListModel> by lazy { treeListModelPointer.reinterpret() }

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.TreeListModel.new.html">
	 *     gtk_tree_list_model_new</a>
	 */
	constructor(
		root: ListModel,
		passthrough: Boolean,
		autoexpand: Boolean,
		createFunction: TreeListModelCreateModelFunction
	) : this(
		gtk_tree_list_model_new(
			root.listModelPointer,
			passthrough.gtk,
			autoexpand.gtk,
			staticTreeListModelCreateModelFunction,
			StableRef.create(createFunction).asCPointer(),
			staticDestroyStableRefFunction
		)!!
	)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListModel.get_autoexpand.html">
	 *     gtk_tree_list_model_get_autoexpand</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListModel.set_autoexpand.html">
	 *     gtk_tree_list_model_set_autoexpand</a>
	 */
	var autoexpand: Boolean
		get() = gtk_tree_list_model_get_autoexpand(treeListModelPointer).bool
		set(value) = gtk_tree_list_model_set_autoexpand(treeListModelPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListModel.get_child_row.html">
	 *     gtk_tree_list_model_get_child_row</a>
	 */
	fun getChildRow(position: UInt): TreeListRow? =
		gtk_tree_list_model_get_child_row(treeListModelPointer, position).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListModel.get_model.html">
	 *     gtk_tree_list_model_get_model</a>
	 */
	val model: ListModel
		get() = gtk_tree_list_model_get_model(treeListModelPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListModel.get_passthrough.html">
	 *     gtk_tree_list_model_get_passthrough</a>
	 */
	val passthrough: Boolean
		get() = gtk_tree_list_model_get_passthrough(treeListModelPointer).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.TreeListModel.get_row.html">
	 *     gtk_tree_list_model_get_row</a>
	 */
	fun getRow(position: UInt): TreeListRow? =
		gtk_tree_list_model_get_row(treeListModelPointer, position).wrap()

	companion object {
		private val staticTreeListModelCreateModelFunction: GtkTreeListModelCreateModelFunc =
			staticCFunction { item, data ->
				data?.asStableRef<TreeListModelCreateModelFunction>()?.get()
					?.invoke(item!!.reinterpret<GObject>().wrap())?.listModelPointer
			}
	}
}