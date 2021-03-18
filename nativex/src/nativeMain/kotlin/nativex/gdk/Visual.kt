package nativex.gdk

import gtk.GdkVisual
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
class Visual internal constructor(
	internal val pointer: CPointer<GdkVisual>
)