package nativex.gtk.widgets

import gtk.GtkSpinner
import gtk.gtk_spinner_new
import gtk.gtk_spinner_start
import gtk.gtk_spinner_stop
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 *
 * 26 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/unstable/GtkSpinner.html"></a>
 */
class Spinner(
	 val spinnerPointer: CPointer<GtkSpinner>
) : Widget(spinnerPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/unstable/GtkSpinner.html#gtk-spinner-new"></a>
	 */
	constructor() : this(gtk_spinner_new()!!.reinterpret())

	/**
	 * Easy way to start / stop the Spinner
	 * @see <a href="https://developer.gnome.org/gtk3/unstable/GtkSpinner.html#gtk-spinner-start">gtk_spinner_start</a>
	 * @see <a href="https://developer.gnome.org/gtk3/unstable/GtkSpinner.html#gtk-spinner-stop">gtk_spinner_stop</a>
	 */
	var isRunning: Boolean = false
		set(value) {
			if (value)
				gtk_spinner_start(spinnerPointer)
			else gtk_spinner_stop(spinnerPointer)
			field = value
		}

	/**
	 * @see isRunning
	 * @see <a href="https://developer.gnome.org/gtk3/unstable/GtkSpinner.html#gtk-spinner-start">gtk_spinner_start</a>
	 */
	fun start() {
		isRunning = true
	}

	/**
	 * @see isRunning
	 * @see <a href="https://developer.gnome.org/gtk3/unstable/GtkSpinner.html#gtk-spinner-stop">gtk_spinner_stop</a>
	 */
	fun stop() {
		isRunning = false
	}
}