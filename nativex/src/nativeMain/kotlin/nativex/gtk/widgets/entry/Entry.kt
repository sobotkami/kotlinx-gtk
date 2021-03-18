package nativex.gtk.widgets.entry

import gtk.GtkEntry
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
open class Entry internal constructor(
	internal val entryPointer: CPointer<GtkEntry>
) : Widget(entryPointer.reinterpret())