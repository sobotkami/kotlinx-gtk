package nativex.gtk.widgets.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.bool
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class EventBox(
	internal val eventBoxPointer: CPointer<GtkEventBox>
) : Bin(eventBoxPointer.reinterpret()) {

	constructor() : this(gtk_event_box_new()!!.reinterpret())

	var aboveChild: Boolean
		get() = gtk_event_box_get_above_child(eventBoxPointer).bool
		set(value) = gtk_event_box_set_above_child(
			eventBoxPointer,
			value.gtk
		)

	var visibleWindow: Boolean
		get() = gtk_event_box_get_visible_window(eventBoxPointer).bool
		set(value) = gtk_event_box_set_visible_window(
			eventBoxPointer,
			value.gtk
		)
}