package nativex.gtk

import gtk.GtkTooltip
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gobject.KObject

/**
 * kotlinx-gtk
 * 09 / 06 / 2021
 */
class Tooltip(
	 val tooltipPointer: CPointer<GtkTooltip>
) : KObject(tooltipPointer.reinterpret()) {
	companion object{
		 inline fun CPointer<GtkTooltip>?.wrap() =
			this?.wrap()

		 inline fun CPointer<GtkTooltip>.wrap() =
			Tooltip(this)
	}
}