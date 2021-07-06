package nativex.gtk.widgets
import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.glib.bool
import nativex.glib.gtk
import nativex.pango.EllipsizeMode

/**
 * kotlinx-gtk
 * 25 / 03 / 2021
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html">
 *     GtkProgressBar</a>
 */
class ProgressBar(
	 val progressBarPointer: CPointer<GtkProgressBar>
) : Widget(progressBarPointer.reinterpret()) {
	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-get-fraction">
	 *     gtk_progress_bar_get_fraction</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-set-fraction">
	 *     gtk_progress_bar_set_fraction</a>
	 */
	var fraction: Double
		get() = gtk_progress_bar_get_fraction(progressBarPointer)
		set(value) = gtk_progress_bar_set_fraction(progressBarPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-get-inverted">
	 *     gtk_progress_bar_get_inverted</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-set-inverted">
	 *     gtk_progress_bar_set_inverted</a>
	 */
	var inverted: Boolean
		get() = gtk_progress_bar_get_inverted(progressBarPointer).bool
		set(value) = gtk_progress_bar_set_inverted(
			progressBarPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-get-show-text">
	 *     gtk_progress_bar_get_show_text</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-set-show-text">
	 *     gtk_progress_bar_set_show_text</a>
	 */
	var showText: Boolean
		get() = gtk_progress_bar_get_show_text(progressBarPointer).bool
		set(value) = gtk_progress_bar_set_show_text(
			progressBarPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-get-text">
	 *     gtk_progress_bar_get_text</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-set-text">
	 *     gtk_progress_bar_set_text</a>
	 */
	var text: String?
		get() = gtk_progress_bar_get_text(progressBarPointer)?.toKString()
		set(value) = gtk_progress_bar_set_text(progressBarPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-get-ellipsize">
	 *     gtk_progress_bar_get_pulse_step</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-set-ellipsize">
	 *     gtk_progress_bar_set_pulse_step</a>
	 */
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

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-get-pulse-step">gtk_progress_bar_get_pulse_step</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-set-pulse-step">gtk_progress_bar_set_pulse_step</a>
	 */
	var pulseStep: Double
		get() = gtk_progress_bar_get_pulse_step(progressBarPointer)
		set(value) = gtk_progress_bar_set_pulse_step(progressBarPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-new">
	 *     gtk_progress_bar_new</a>
	 */
	constructor() : this(gtk_progress_bar_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkProgressBar.html#gtk-progress-bar-pulse">
	 *     gtk_progress_bar_pulse</a>
	 */
	fun pulse() {
		gtk_progress_bar_pulse(progressBarPointer)
	}
}