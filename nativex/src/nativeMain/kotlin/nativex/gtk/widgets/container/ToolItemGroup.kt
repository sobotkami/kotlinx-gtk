package nativex.gtk.widgets.container

import gtk.GtkToolItemGroup
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 24 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToolItemGroup.html">GtkToolItemGroup</a>
 */
class ToolItemGroup(
	val toolItemGroupPointer: CPointer<GtkToolItemGroup>
) : Container(toolItemGroupPointer.reinterpret()) {
	companion object {
		 inline fun CPointer<GtkToolItemGroup>?.wrap() =
			this?.wrap()

		 inline fun CPointer<GtkToolItemGroup>.wrap() =
			ToolItemGroup(this)
	}
}