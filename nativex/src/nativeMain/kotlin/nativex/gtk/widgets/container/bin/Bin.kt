package nativex.gtk.widgets.container.bin

import gtk.GtkBin
import gtk.gtk_bin_get_child
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.Container

/**
 * kotlinx-gtk
 * 07 / 03 / 2021
 */
open class Bin internal constructor(
	internal val binPointer: CPointer<GtkBin>
) : Container(binPointer.reinterpret()) {

	val child: Widget?
		get() = gtk_bin_get_child(binPointer)?.let { Widget(it) }

}