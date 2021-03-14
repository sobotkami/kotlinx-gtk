package kotlin.gtk.container.bin

import gtk.GtkBin
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.container.Container

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
open class Bin internal constructor(
	internal val binPointer: CPointer<GtkBin>
) : Container(binPointer.reinterpret()) {

}