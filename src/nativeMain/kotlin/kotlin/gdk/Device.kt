package kotlin.gdk

import gtk.GdkDevice
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 14 / 03 / 2021
 */
class Device internal constructor(
	internal val pointer: CPointer<GdkDevice>
) {
}