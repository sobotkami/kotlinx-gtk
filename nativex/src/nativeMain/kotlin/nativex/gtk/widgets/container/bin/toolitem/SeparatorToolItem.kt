package nativex.gtk.widgets.container.bin.toolitem

import gtk.GtkSeparatorToolItem
import gtk.gtk_separator_tool_item_get_draw
import gtk.gtk_separator_tool_item_new
import gtk.gtk_separator_tool_item_set_draw
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.glib.bool
import nativex.glib.gtk

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
class SeparatorToolItem(
	@Suppress("MemberVisibilityCanBePrivate")
	 val separatorToolItem: CPointer<GtkSeparatorToolItem>
) : ToolItem(separatorToolItem.reinterpret()) {
	constructor() : this(gtk_separator_tool_item_new()!!.reinterpret())

	var draw: Boolean
		get() = gtk_separator_tool_item_get_draw(
			separatorToolItem
		).bool
		set(value) = gtk_separator_tool_item_set_draw(
			separatorToolItem,
			value.gtk
		)
}