package nativex.gdk

import gtk.GdkDragContext
import kotlinx.cinterop.CPointer
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 09 / 06 / 2021
 */
class DragContext internal constructor(
	internal val dragContext: CPointer<GdkDragContext>
) {

	companion object{
		internal inline fun CPointer<GdkDragContext>?.wrap() =
			this?.wrap()

		internal inline fun CPointer<GdkDragContext>.wrap() =
			DragContext(this)
	}
}