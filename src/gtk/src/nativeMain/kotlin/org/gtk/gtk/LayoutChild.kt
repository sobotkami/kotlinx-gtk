package org.gtk.gtk

import gtk.GtkLayoutChild
import gtk.gtk_layout_child_get_child_widget
import gtk.gtk_layout_child_get_layout_manager
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gobject.KGObject
import org.gtk.gtk.LayoutManager.Companion.wrap
import org.gtk.gtk.widgets.Widget
import org.gtk.gtk.widgets.Widget.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 01 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.LayoutChild.html">GtkLayoutChild</a>
 */
class LayoutChild(val layoutChildPointer: CPointer<GtkLayoutChild>) : KGObject(layoutChildPointer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.LayoutChild.get_child_widget.html">
	 *     gtk_layout_child_get_child_widget</a>
	 */
	val child: Widget
		get() = gtk_layout_child_get_child_widget(layoutChildPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.LayoutChild.get_layout_manager.html">
	 *     gtk_layout_child_get_layout_manager</a>
	 */
	val layoutManager: LayoutManager
		get() = gtk_layout_child_get_layout_manager(layoutChildPointer)!!.wrap()

	companion object{
		inline fun CPointer<GtkLayoutChild>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkLayoutChild>.wrap() =
			LayoutChild(this)
	}
}