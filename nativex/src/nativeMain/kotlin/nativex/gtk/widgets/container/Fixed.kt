package nativex.gtk.widgets.container

import gtk.GtkFixed
import gtk.gtk_fixed_move
import gtk.gtk_fixed_new
import gtk.gtk_fixed_put
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFixed.html">GtkFixed</a>
 */
class Fixed internal constructor(
	internal val fixedPointer: CPointer<GtkFixed>
) : Container(
	fixedPointer.reinterpret()
) {
	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFixed.html#gtk-fixed-new">gtk_fixed_new</a>
	 */
	constructor() : this(
		gtk_fixed_new()!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFixed.html#gtk-fixed-put">gtk_fixed_put</a>
	 *
	 * @return [FixedWidget] with direct management functions
	 */
	fun put(widget: Widget, x: Int, y: Int): FixedWidget {
		gtk_fixed_put(fixedPointer, widget.widgetPointer, x, y)
		return FixedWidget(widget)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFixed.html#gtk-fixed-move">gtk_fixed_move</a>
	 */
	fun move(widget: Widget, x: Int, y: Int) {
		gtk_fixed_move(fixedPointer, widget.widgetPointer, x, y)
	}

	/**
	 * Wraps a [Widget] to make it easy to manage a widget that is inside a [Fixed]
	 */
	inner class FixedWidget(val widget: Widget) {
		fun move(x: Int, y: Int) {
			move(widget, x, y)
		}

		fun remove() {
			this@Fixed.remove(widget)
		}
	}
}