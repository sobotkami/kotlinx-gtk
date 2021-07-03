package nativex.gtk.widgets.misc

import gtk.GtkMisc
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
open class Misc(
	 val miscPointer: CPointer<GtkMisc>
) : Widget(miscPointer.reinterpret())