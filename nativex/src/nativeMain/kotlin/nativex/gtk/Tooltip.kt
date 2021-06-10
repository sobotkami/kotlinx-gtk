package nativex.gtk

import gtk.GtkTooltip
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gio.KObject

/**
 * kotlinx-gtk
 * 09 / 06 / 2021
 */
class Tooltip internal constructor(
	internal val tooltipPointer: CPointer<GtkTooltip>
) : KObject(tooltipPointer.reinterpret()) {
	companion object{
		internal inline fun CPointer<GtkTooltip>?.wrap() =
			this?.wrap()

		internal inline fun CPointer<GtkTooltip>.wrap() =
			Tooltip(this)
	}
}