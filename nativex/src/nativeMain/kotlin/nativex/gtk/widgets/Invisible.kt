package nativex.gtk.widgets

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gdk.Screen

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class Invisible(
	 val invisiblePointer: CPointer<GtkInvisible>
) : Widget(invisiblePointer.reinterpret()) {
	constructor() : this(gtk_invisible_new()!!.reinterpret())
	constructor(screen: Screen) : this(gtk_invisible_new_for_screen(screen.screenPointer)!!.reinterpret())

	var invScreen: Screen
		get() = Screen(gtk_invisible_get_screen(invisiblePointer)!!)
		set(value) = gtk_invisible_set_screen(
			invisiblePointer,
			value.screenPointer
		)
}