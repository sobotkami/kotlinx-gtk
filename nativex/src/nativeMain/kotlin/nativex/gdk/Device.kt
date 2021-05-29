package nativex.gdk

import gtk.GdkDevice
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
class Device internal constructor(
	internal val pointer: CPointer<GdkDevice>
){
	companion object{
		internal inline fun CPointer<GdkDevice>?.wrap() =
			this?.let { Device(it) }

		internal inline fun CPointer<GdkDevice>.wrap() =
			Device(this)

	}
}