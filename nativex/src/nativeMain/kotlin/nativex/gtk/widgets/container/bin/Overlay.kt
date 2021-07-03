package nativex.gtk.widgets.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.bool
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkOverlay.html"></a>
 */
class Overlay(
	 val overlayPointer: CPointer<GtkOverlay>
) : Bin(overlayPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkOverlay.html#gtk-overlay-new">
	 *     gtk_overlay_new</a>
	 */
	constructor() : this(gtk_overlay_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkOverlay.html#gtk-overlay-add-overlay">
	 *     gtk_overlay_add_overlay</a>
	 */
	fun addOverlay(widget: Widget) {
		gtk_overlay_add_overlay(overlayPointer, widget.widgetPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkOverlay.html#gtk-overlay-reorder-overlay">
	 *     gtk_overlay_reorder_overlay</a>
	 */
	fun reorderOverlay(widget: Widget, index: Int) {
		gtk_overlay_reorder_overlay(overlayPointer, widget.widgetPointer, index)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkOverlay.html#gtk-overlay-get-overlay-pass-through">
	 *     gtk_overlay_get_overlay_pass_through</a>
	 */
	fun getOverlayPassThrough(widget: Widget): Boolean =
		gtk_overlay_get_overlay_pass_through(
			overlayPointer,
			widget.widgetPointer
		).bool

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkOverlay.html#gtk-overlay-set-overlay-pass-through">
	 *     gtk_overlay_set_overlay_pass_through</a>
	 */
	fun setOverlayPassThrough(widget: Widget, passThrough: Boolean) {
		gtk_overlay_set_overlay_pass_through(
			overlayPointer,
			widget.widgetPointer,
			passThrough.gtk
		)
	}

}