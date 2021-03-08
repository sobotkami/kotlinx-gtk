package kotlin.gtk.container

import gtk.GtkBox
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
open class Box internal constructor(
	internal val boxPointer: CPointer<GtkBox>
) : Container(boxPointer.reinterpret()) {


}