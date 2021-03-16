package kotlin.gtk.widgets.entry

import gtk.GtkEntry
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
open class Entry internal constructor(
	internal val entryPointer: CPointer<GtkEntry>
) : Widget(entryPointer.reinterpret()) {
}