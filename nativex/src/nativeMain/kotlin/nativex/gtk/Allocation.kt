package nativex.gtk

import gtk.GtkAllocation
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 24 / 07 / 2021
 *
 * @see <a href=""></a>
 */
class Allocation(
	val allocationPointer: CPointer<GtkAllocation>
) {
}