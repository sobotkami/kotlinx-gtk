package kotlin.gtk.container

import gtk.GtkBin
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
open class Bin internal constructor(
	internal val binPointer: CPointer<GtkBin>
) : Container(binPointer.reinterpret()) {

}