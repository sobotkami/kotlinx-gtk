package nativex.gtk.widgets.container

import gtk.GtkToolItemGroup
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 24 / 03 / 2021
 */
class ToolItemGroup internal constructor(
	internal val toolItemGroupPointer: CPointer<GtkToolItemGroup>
) : Container(toolItemGroupPointer.reinterpret()) {

}