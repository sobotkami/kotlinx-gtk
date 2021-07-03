package nativex.gtk

import gtk.GtkTargetList
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 03 / 07 / 2021
 *
 * @see <a href=""></a>
 */
class TargetList(val targetListPointer: CPointer<GtkTargetList>) {
	companion object {
		inline fun CPointer<GtkTargetList>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkTargetList>.wrap() =
			TargetList(this)
	}
}