package kotlin.gtk.container.bin

import gtk.GtkActionBar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class StackSidebar(
	internal val p: CPointer<GtkActionBar>
) : Bin(p.reinterpret()) {

}