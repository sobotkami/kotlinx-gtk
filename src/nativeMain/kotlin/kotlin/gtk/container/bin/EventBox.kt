package kotlin.gtk.container.bin

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.from
import kotlin.gtk.gtkValue

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class EventBox(
	internal val eventBoxPointer: CPointer<GtkEventBox>
) : Bin(eventBoxPointer.reinterpret()) {

	constructor() : this(gtk_event_box_new()!!.reinterpret())

	var aboveChild: Boolean
		get() = Boolean.from(gtk_event_box_get_above_child(eventBoxPointer))
		set(value) = gtk_event_box_set_above_child(
			eventBoxPointer,
			value.gtkValue
		)

	var visibleWindow: Boolean
		get() = Boolean.from(gtk_event_box_get_visible_window(eventBoxPointer))
		set(value) = gtk_event_box_set_visible_window(
			eventBoxPointer,
			value.gtkValue
		)
}