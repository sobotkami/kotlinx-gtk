package org.gtk.gtk.widgets.frame

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import org.gtk.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html">GtkFrame</a>
 */
open class Frame(
	val framePointer: CPointer<GtkFrame>
) : Widget(framePointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-new">gtk_frame_new</a>
	 */
	constructor(
		label: String? = null
	) : this(gtk_frame_new(label)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-get-label">gtk_frame_get_label</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-set-label">gtk_frame_set_label</a>
	 */
	var label: String?
		get() = gtk_frame_get_label(framePointer)?.toKString()
		set(value) {
			gtk_frame_set_label(framePointer, value)
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-get-label-align">gtk_frame_get_label_align</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-set-label-align">gtk_frame_set_label_align</a>
	 */
	var labelAlign: Float
		get() = gtk_frame_get_label_align(framePointer)
		set(value) = gtk_frame_set_label_align(framePointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-get-label-widget">gtk_frame_get_label_widget</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-set-label-widget">gtk_frame_set_label_widget</a>
	 */
	var labelWidget: Widget?
		get() = gtk_frame_get_label_widget(framePointer)?.let { Widget(it) }
		set(value) = gtk_frame_set_label_widget(
			framePointer,
			value?.widgetPointer
		)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Frame.get_child.html">gtk_frame_get_child</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.Frame.set_child.html">gtk_frame_set_child</a>
	 */
	var child: Widget?
		get() = gtk_frame_get_child(framePointer)?.wrap()
		set(value) = gtk_frame_set_child(framePointer, value?.widgetPointer)
}