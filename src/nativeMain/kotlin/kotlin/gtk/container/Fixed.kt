package kotlin.gtk.container

import gtk.GtkFixed
import gtk.gtk_fixed_move
import gtk.gtk_fixed_new
import gtk.gtk_fixed_put
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class Fixed internal constructor(
	internal val fixedPointer: CPointer<GtkFixed>
) : Container(
	fixedPointer.reinterpret()
) {
	constructor() : this(
		gtk_fixed_new()!!.reinterpret()
	)

	fun put(widget: Widget, x: Int, y: Int): FixedWidget {
		gtk_fixed_put(fixedPointer, widget.widgetPointer, x, y)
		return FixedWidget(widget)
	}

	fun move(widget: Widget, x: Int, y: Int) {
		gtk_fixed_move(fixedPointer, widget.widgetPointer, x, y)
	}

	/**
	 * Wraps [Widget] to make it easy to move a widget inside [Fixed]
	 */
	inner class FixedWidget(private val widget: Widget) {
		fun move(x: Int, y: Int) {
			move(widget, x, y)
		}
	}
}