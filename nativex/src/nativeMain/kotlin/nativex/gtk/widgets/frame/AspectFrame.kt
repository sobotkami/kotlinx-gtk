package nativex.gtk.widgets.frame

import gtk.GtkAspectFrame
import gtk.gtk_aspect_frame_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.glib.gtk

/**
 * kotlinx-gtk
 *
 * 20 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAspectFrame.html">GtkAspectFrame</a>
 */
class AspectFrame(
	val aspectFramePointer: CPointer<GtkAspectFrame>
) : Frame(aspectFramePointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAspectFrame.html#gtk-aspect-frame-new">
	 *     gtk_aspect_frame_new</a>
	 */
	constructor(xAlign: Float, yAlign: Float, ratio: Float, obeyChild: Boolean) :
			this(gtk_aspect_frame_new(xAlign, yAlign, ratio, obeyChild.gtk)!!.reinterpret())
}