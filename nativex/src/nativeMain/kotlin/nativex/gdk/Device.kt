package nativex.gdk

import gtk.GdkDevice
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
class Device(
	 val pointer: CPointer<GdkDevice>
){
	companion object{
		 inline fun CPointer<GdkDevice>?.wrap() =
			this?.let { Device(it) }

		 inline fun CPointer<GdkDevice>.wrap() =
			Device(this)

	}
}