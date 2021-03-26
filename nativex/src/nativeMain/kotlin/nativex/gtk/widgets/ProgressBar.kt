package nativex.gtk.widgets

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.gtk.bool
import nativex.gtk.gtk
import nativex.pango.EllipsizeMode

/**
 * kotlinx-gtk
 * 25 / 03 / 2021
 */
class ProgressBar internal constructor(
	internal val progressBarPointer: CPointer<GtkProgressBar>
) : Widget(progressBarPointer.reinterpret()) {
	var fraction: Double
		get() = gtk_progress_bar_get_fraction(progressBarPointer)
		set(value) = gtk_progress_bar_set_fraction(progressBarPointer, value)

	var inverted: Boolean
		get() = gtk_progress_bar_get_inverted(progressBarPointer).bool
		set(value) = gtk_progress_bar_set_inverted(
			progressBarPointer,
			value.gtk
		)

	var showText: Boolean
		get() = gtk_progress_bar_get_show_text(progressBarPointer).bool
		set(value) = gtk_progress_bar_set_show_text(
			progressBarPointer,
			value.gtk
		)

	var text: String?
		get() = gtk_progress_bar_get_text(progressBarPointer)?.toKString()
		set(value) = gtk_progress_bar_set_text(progressBarPointer, value)

	var ellipsize: EllipsizeMode
		get() = EllipsizeMode.valueOf(
			gtk_progress_bar_get_ellipsize(
				progressBarPointer
			)
		)!!
		set(value) = gtk_progress_bar_set_ellipsize(
			progressBarPointer,
			value.pango
		)

	var pulseStep: Double
		get() = gtk_progress_bar_get_pulse_step(progressBarPointer)
		set(value) = gtk_progress_bar_set_pulse_step(progressBarPointer, value)

	constructor() : this(gtk_progress_bar_new()!!.reinterpret())

	fun pulse() {
		gtk_progress_bar_pulse(progressBarPointer)
	}
}