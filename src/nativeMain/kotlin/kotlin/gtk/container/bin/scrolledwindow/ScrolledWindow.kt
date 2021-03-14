package kotlin.gtk.container.bin.scrolledwindow

import gtk.GtkActionBar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.container.bin.Bin

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class ScrolledWindow(
	internal val p: CPointer<GtkActionBar>
) : Bin(p.reinterpret()) {

}