package nativex.gdk

import gtk.GdkDragContext
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 09 / 06 / 2021
 */
class DragContext(
	 val dragContext: CPointer<GdkDragContext>
) {

	companion object{
		 inline fun CPointer<GdkDragContext>?.wrap() =
			this?.wrap()

		 inline fun CPointer<GdkDragContext>.wrap() =
			DragContext(this)
	}
}