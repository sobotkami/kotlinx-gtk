package nativex.gtk.widgets.range

import gtk.GtkOrientable
import gtk.GtkScrollbar
import gtk.gtk_scrollbar_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.Adjustment
import nativex.gtk.Orientable
import nativex.gtk.common.enums.Orientation

/**
 * kotlinx-gtk
 *
 * 14 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrollbar.html">GtkScrollbar</a>
 */
open class Scrollbar(
	val scrollBar: CPointer<GtkScrollbar>
) : Range(scrollBar.reinterpret()), Orientable {

	override val orientablePointer: CPointer<GtkOrientable> by lazy { scrollBar.reinterpret() }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScrollbar.html#gtk-scrollbar-new">gtk_scrollbar_new</a>
	 */
	constructor(
		orientation: Orientation,
		adjustment: Adjustment? = null
	) : this(
		gtk_scrollbar_new(orientation.gtk, adjustment?.adjustmentPointer)!!.reinterpret()
	)

	class HorizontalScrollbar(adjustment: Adjustment? = null) : Scrollbar(Orientation.HORIZONTAL, adjustment)

	class VerticalScrollbar(adjustment: Adjustment? = null) : Scrollbar(Orientation.VERTICAL, adjustment)
}