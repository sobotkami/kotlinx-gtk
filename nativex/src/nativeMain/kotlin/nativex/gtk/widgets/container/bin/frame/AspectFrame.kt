package nativex.gtk.widgets.container.bin.frame

import gtk.GtkAspectFrame
import gtk.gtk_aspect_frame_new
import gtk.gtk_aspect_frame_set
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
class AspectFrame internal constructor(
	internal val aspectFramePointer: CPointer<GtkAspectFrame>
) : Frame(aspectFramePointer.reinterpret()) {
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