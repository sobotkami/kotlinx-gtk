package nativex.gtk.widgets.container

import gtk.GtkToolItemGroup
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.selections.TargetEntry

/**
 * kotlinx-gtk
 * 24 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolItemGroup.html">GtkToolItemGroup</a>
 */
class ToolItemGroup internal constructor(
	val toolItemGroupPointer: CPointer<GtkToolItemGroup>
) : Container(toolItemGroupPointer.reinterpret()) {
	companion object {
		internal inline fun CPointer<GtkToolItemGroup>?.wrap() =
			this?.wrap()

		internal inline fun CPointer<GtkToolItemGroup>.wrap() =
			ToolItemGroup(this)
	}
}