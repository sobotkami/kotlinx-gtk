package kotlin.gtk.container.bin.popover

import gtk.GtkActionBar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.container.bin.Bin

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class Popover(
	internal val p: CPointer<GtkActionBar>
) : Bin(p.reinterpret()) {

}