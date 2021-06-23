package nativex.gtk.widgets.container.bin.frame

import gtk.GtkAspectFrame
import gtk.gtk_aspect_frame_new
import gtk.gtk_aspect_frame_set
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 *
 * 20 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAspectFrame.html">GtkAspectFrame</a>
 */
class AspectFrame internal constructor(
	internal val aspectFramePointer: CPointer<GtkAspectFrame>
) : Frame(aspectFramePointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAspectFrame.html#gtk-aspect-frame-new">
	 *     gtk_aspect_frame_new</a>
	 */
	constructor(
		label: String,
		xAlign: Float,
		yAlign: Float,
		ratio: Float,
		obeyChild: Boolean
	) : this(
		gtk_aspect_frame_new(
			label,
			xAlign,
			yAlign,
			ratio,
			obeyChild.gtk
		)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAspectFrame.html#gtk-aspect-frame-set">
	 *     gtk_aspect_frame_set</a>
	 */
	fun set(
		label: String,
		xAlign: Float,
		yAlign: Float,
		ratio: Float,
		obeyChild: Boolean
	) {
		gtk_aspect_frame_set(
			aspectFramePointer,
			xAlign,
			yAlign,
			ratio,
			obeyChild.gtk
		)
	}
}