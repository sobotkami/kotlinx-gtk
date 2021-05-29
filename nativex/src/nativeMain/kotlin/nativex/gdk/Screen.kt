package nativex.gdk

import gtk.GdkScreen
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.KObject

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class Screen internal constructor(
	internal val screenPointer: CPointer<GdkScreen>
) : KObject(screenPointer.reinterpret()) {

	companion object{
		internal inline fun CPointer<GdkScreen>?.wrap() =
			this?.wrap()

		internal inline fun CPointer<GdkScreen>.wrap() =
			Screen(this)
	}
}