package kotlin.gtk.widgets.range

import gtk.GtkScrollbar
import gtk.gtk_scrollbar_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.Adjustment
import kotlin.gtk.common.enums.Orientation

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
class Scrollbar internal constructor(
	internal val scrollBar: CPointer<GtkScrollbar>
) : Range(scrollBar.reinterpret()) {

	constructor(
		orientation: Orientation,
		adjustment: Adjustment? = null
	) : this(
		gtk_scrollbar_new(orientation.gtk, adjustment?.pointer)!!.reinterpret()
	)
}