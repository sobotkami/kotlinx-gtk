package nativex.gdk

import gtk.GdkPixbufAnimation
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class PixbufAnimation internal constructor(
	internal val pixBufAnimationPointer: CPointer<GdkPixbufAnimation>
) {
}