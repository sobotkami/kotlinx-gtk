package nativex.gtk

import gtk.GtkTooltip
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gobject.KGObject

/**
 * kotlinx-gtk
 * 09 / 06 / 2021
 */
class Tooltip(
	 val tooltipPointer: CPointer<GtkTooltip>
) : KGObject(tooltipPointer.reinterpret()) {
	companion object{
		 inline fun CPointer<GtkTooltip>?.wrap() =
			this?.wrap()

		 inline fun CPointer<GtkTooltip>.wrap() =
			Tooltip(this)
	}
}