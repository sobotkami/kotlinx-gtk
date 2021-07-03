package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.*
import nativex.gdk.Window
import nativex.gtk.Adjustment
import nativex.gtk.Scrollable
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 24 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLayout.html">GtkLayout</a>
 */
class Layout(
	 val layoutPointer: CPointer<GtkLayout>
) : Container(layoutPointer.reinterpret()), Scrollable {
	override val scrollablePointer: CPointer<GtkScrollable>
		get() = layoutPointer.reinterpret()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLayout.html#gtk-layout-new">gtk_layout_new</a>
	 */
	constructor(
		horizontalAdjustment: Adjustment? = null,
		verticalAdjustment: Adjustment? = null
	) : this(
		gtk_layout_new(
			horizontalAdjustment?.adjustmentPointer,
			verticalAdjustment?.adjustmentPointer
		)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLayout.html#gtk-layout-put">gtk_layout_put</a>
	 */
	fun put(widget: Widget, x: Int, y: Int) {
		gtk_layout_put(layoutPointer, widget.widgetPointer, x, y)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLayout.html#gtk-layout-move">gtk_layout_move</a>
	 */
	fun move(widget: Widget, x: Int, y: Int) {
		gtk_layout_move(layoutPointer, widget.widgetPointer, x, y)
	}


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLayout.html#gtk-layout-get-size">gtk_layout_get_size</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLayout.html#gtk-layout-set-size">gtk_layout_set_size</a>
	 */
	var layoutSize: Pair<UInt, UInt>
		get() = memScoped {
			val w = cValue<UIntVar>()
			val h = cValue<UIntVar>()
			gtk_layout_get_size(layoutPointer, w, h)
			w.ptr.pointed.value to h.ptr.pointed.value
		}
		set(value) {
			gtk_layout_set_size(layoutPointer, value.first, value.second)
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkLayout.html#gtk-layout-get-bin-window">
	 *     gtk_layout_get_bin_window</a>
	 */
	val binWindow: Window
		get() = Window(gtk_layout_get_bin_window(layoutPointer)!!)

}