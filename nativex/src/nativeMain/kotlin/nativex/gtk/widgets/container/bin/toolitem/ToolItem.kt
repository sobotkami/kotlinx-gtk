package nativex.gtk.widgets.container.bin.toolitem

import gtk.GtkActionBar
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.container.bin.Bin

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class ToolItem(
	internal val p: CPointer<GtkActionBar>
) : Bin(p.reinterpret())