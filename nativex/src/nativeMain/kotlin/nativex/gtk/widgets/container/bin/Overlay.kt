package nativex.gtk.widgets.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.bool
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class Overlay(
	internal val overlayPointer: CPointer<GtkOverlay>
) : Bin(overlayPointer.reinterpret()) {

	constructor() : this(gtk_overlay_new()!!.reinterpret())

	fun addOverlay(widget: Widget) {
		gtk_overlay_add_overlay(overlayPointer, widget.widgetPointer)
	}

	fun reorderOverlay(widget: Widget, index: Int) {
		gtk_overlay_reorder_overlay(overlayPointer, widget.widgetPointer, index)
	}

	fun overlayPassThrough(widget: Widget): Boolean =
		gtk_overlay_get_overlay_pass_through(
			overlayPointer,
			widget.widgetPointer
		).bool

	fun overlayPassThrough(widget: Widget, passThrough: Boolean) {
		gtk_overlay_set_overlay_pass_through(
			overlayPointer,
			widget.widgetPointer,
			passThrough.gtk
		)
	}

}