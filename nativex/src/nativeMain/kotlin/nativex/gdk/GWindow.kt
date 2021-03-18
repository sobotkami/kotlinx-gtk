package nativex.gdk

import gtk.GdkWindow
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 22 / 02 / 2021
 */
class GWindow internal constructor(
	internal val pointer: CPointer<GdkWindow>
)