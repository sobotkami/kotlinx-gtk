package nativex.gdk

import gtk.GdkVisual
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
@Deprecated("Removed in GTK4", level = DeprecationLevel.WARNING)
class Visual internal constructor(
	internal val pointer: CPointer<GdkVisual>
)