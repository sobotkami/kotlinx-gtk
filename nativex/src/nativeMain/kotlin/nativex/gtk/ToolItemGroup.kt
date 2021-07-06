package nativex.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.toKString
import nativex.glib.bool
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.Widget.Companion.wrap
import nativex.gtk.widgets.container.bin.toolitem.ToolItem
import nativex.pango.EllipsizeMode

class ToolItemGroup(
	 val toolItemGroupPointer: CPointer<GtkToolItemGroup>
) {

	val collapsed: Boolean
		get() = gtk_tool_item_group_get_collapsed(toolItemGroupPointer).bool

	fun getDropItem(x: Int, y: Int): ToolItem =
		ToolItem(gtk_tool_item_group_get_drop_item(toolItemGroupPointer, x, y)!!)

	val ellipsize: EllipsizeMode
		get() = EllipsizeMode.Companion.valueOf(gtk_tool_item_group_get_ellipsize(toolItemGroupPointer))!!

	fun getItemPosition(item: ToolItem): Int =
		gtk_tool_item_group_get_item_position(toolItemGroupPointer, item.toolItemPointer)

	val label: String?
		get() = gtk_tool_item_group_get_label(toolItemGroupPointer)?.toKString()

	val labelWidget: Widget
		get() = gtk_tool_item_group_get_label_widget(toolItemGroupPointer)!!.wrap()

	val itemCount: UInt
		get() = gtk_tool_item_group_get_n_items(toolItemGroupPointer)


}