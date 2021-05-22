package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.*
import nativex.PointerHolder
import nativex.gdk.Window
import nativex.gtk.Adjustment
import nativex.gtk.Scrollable
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 24 / 03 / 2021
 */
class Layout internal constructor(
	internal val layoutPointer: CPointer<GtkLayout>
) : Container(layoutPointer.reinterpret()), Scrollable {
	override val scrollablePointer: PointerHolder<GtkScrollable>
		get() = PointerHolder(layoutPointer.reinterpret())

	constructor(
		horizontalAdjustment: Adjustment? = null,
		verticalAdjustment: Adjustment? = null
	) : this(
		gtk_layout_new(
			horizontalAdjustment?.adjustmentPointer,
			verticalAdjustment?.adjustmentPointer
		)!!.reinterpret()
	)

	fun put(widget: Widget, x: Int, y: Int) {
		gtk_layout_put(layoutPointer, widget.widgetPointer, x, y)
	}

	fun move(widget: Widget, x: Int, y: Int) {
		gtk_layout_move(layoutPointer, widget.widgetPointer, x, y)
	}

	
	var size: Pair<UInt, UInt>
		get() = memScoped {
			val w = cValue<UIntVar>()
			val h = cValue<UIntVar>()
			gtk_layout_get_size(layoutPointer, w, h)
			w.ptr.pointed.value to h.ptr.pointed.value
		}
		set(value) {
			gtk_layout_set_size(layoutPointer, value.first, value.second)
		}

	val binWindow: Window
		get() = Window(gtk_layout_get_bin_window(layoutPointer)!!)

}