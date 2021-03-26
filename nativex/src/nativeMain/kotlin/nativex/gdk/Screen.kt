package nativex.gdk

import gtk.GdkScreen
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.KotlinGObject

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class Screen internal constructor(
	internal val screenPointer: CPointer<GdkScreen>
) : KotlinGObject(screenPointer.reinterpret()) {

}