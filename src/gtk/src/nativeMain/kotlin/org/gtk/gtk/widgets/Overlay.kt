package org.gtk.gtk.widgets

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 *
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkOverlay.html"></a>
 */
class Overlay(
	 val overlayPointer: CPointer<GtkOverlay>
) : Widget(overlayPointer.reinterpret()) {

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

}