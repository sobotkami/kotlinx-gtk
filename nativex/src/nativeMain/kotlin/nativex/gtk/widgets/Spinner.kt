package nativex.gtk.widgets

import gtk.GtkSpinner
import gtk.gtk_spinner_new
import gtk.gtk_spinner_start
import gtk.gtk_spinner_stop
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class Spinner internal constructor(
	internal val spinnerPointer: CPointer<GtkSpinner>
) : Widget(spinnerPointer.reinterpret()) {
	constructor() : this(gtk_spinner_new()!!.reinterpret())

	var isRunning: Boolean = false
		set(value) {
			if (value)
				gtk_spinner_start(spinnerPointer)
			else gtk_spinner_stop(spinnerPointer)
			field = value
		}

	fun start() {
		isRunning = true
	}

	fun stop() {
		isRunning = false
	}
}