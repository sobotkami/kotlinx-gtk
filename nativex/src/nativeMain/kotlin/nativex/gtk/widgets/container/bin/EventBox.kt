package nativex.gtk.widgets.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.bool
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 *
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEventBox.html">GtkEventBox</a>
 */
class EventBox(
	internal val eventBoxPointer: CPointer<GtkEventBox>
) : Bin(eventBoxPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEventBox.html#gtk-event-box-new">gtk_event_box_new</a>
	 */
	constructor() : this(gtk_event_box_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEventBox.html#gtk-event-box-get-above-child">gtk_event_box_get_above_child</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEventBox.html#gtk-event-box-set-above-child">gtk_event_box_set_above_child</a>
	 */
	var aboveChild: Boolean
		get() = gtk_event_box_get_above_child(eventBoxPointer).bool
		set(value) = gtk_event_box_set_above_child(
			eventBoxPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEventBox.html#gtk-event-box-get-visible-window">gtk_event_box_get_visible_window</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkEventBox.html#gtk-event-box-set-visible-window">gtk_event_box_set_visible_window</a>
	 */
	var visibleWindow: Boolean
		get() = gtk_event_box_get_visible_window(eventBoxPointer).bool
		set(value) = gtk_event_box_set_visible_window(
			eventBoxPointer,
			value.gtk
		)
}