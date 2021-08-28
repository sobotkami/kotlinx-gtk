package org.gtk.gtk

import gtk.GtkWidgetPaintable
import gtk.gtk_widget_paintable_get_widget
import gtk.gtk_widget_paintable_new
import gtk.gtk_widget_paintable_set_widget
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gdk.Paintable
import org.gtk.gtk.widgets.Widget
import org.gtk.gtk.widgets.Widget.Companion.wrap

/**
 * gtk-kt
 *
 * 27 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.WidgetPaintable.html">GtkWidgetPaintable</a>
 */
class WidgetPaintable(val widgetPaintablePointer: CPointer<GtkWidgetPaintable>) :
	Paintable(widgetPaintablePointer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/ctor.WidgetPaintable.new.html">gtk_widget_paintable_new</a>
	 */
	constructor(widget: Widget?) : this(gtk_widget_paintable_new(widget?.widgetPointer)!!.reinterpret())

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.WidgetPaintable.get_widget.html">
	 *     gtk_widget_paintable_get_widget</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.WidgetPaintable.set_widget.html">
	 *     gtk_widget_paintable_set_widget</a>
	 */
	var widget: Widget?
		get() = gtk_widget_paintable_get_widget(widgetPaintablePointer).wrap()
		set(value) = gtk_widget_paintable_set_widget(widgetPaintablePointer, value?.widgetPointer)

}