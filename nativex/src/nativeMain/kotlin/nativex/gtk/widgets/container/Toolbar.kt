package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.IconSize
import nativex.gtk.bool
import nativex.gtk.common.enums.ReliefStyle
import nativex.gtk.common.enums.ToolbarStyle
import nativex.gtk.gtk
import nativex.gtk.widgets.container.bin.toolitem.ToolItem

/**
 * kotlinx-gtk
 * 24 / 03 / 2021
 */
class Toolbar internal constructor(
	internal val toolbarPointer: CPointer<GtkToolbar>
) : Container(toolbarPointer.reinterpret()) {
	constructor() : this(gtk_toolbar_new()!!.reinterpret())

	fun insert(item: ToolItem, position: Int) {
		gtk_toolbar_insert(toolbarPointer, item.toolItemPointer, position);
	}

	fun getItemIndex(item: ToolItem): Int =
		gtk_toolbar_get_item_index(toolbarPointer, item.toolItemPointer)


	val itemCount: Int
		get() = gtk_toolbar_get_n_items(toolbarPointer)

	fun getItem(index: Int): ToolItem? =
		gtk_toolbar_get_nth_item(toolbarPointer, index)?.let { ToolItem(it) }

	fun getDropIndex(x: Int, y: Int): Int =
		gtk_toolbar_get_drop_index(toolbarPointer, x, y)

	fun setDropHighlightItem(item: ToolItem?, index: Int) {
		gtk_toolbar_set_drop_highlight_item(
			toolbarPointer,
			item?.toolItemPointer,
			index
		)
	}

	var showArrow: Boolean
		get() = gtk_toolbar_get_show_arrow(toolbarPointer).bool
		set(value) = gtk_toolbar_set_show_arrow(toolbarPointer, value.gtk)

	fun unsetIconSize() {
		gtk_toolbar_unset_icon_size(toolbarPointer)
	}

	val reliefStyle: ReliefStyle
		get() = ReliefStyle.valueOf(
			gtk_toolbar_get_relief_style(toolbarPointer)
		)!!

	var iconSize: IconSize?
		get() = IconSize.valueOf(
			gtk_toolbar_get_icon_size(toolbarPointer)
		)
		set(value) =
			if (value == null)
				unsetIconSize()
			else
				gtk_toolbar_set_icon_size(
					toolbarPointer,
					value.gtk
				)

	var style: ToolbarStyle?
		get() = ToolbarStyle.valueOf(
			gtk_toolbar_get_style(
				toolbarPointer
			)
		)
		set(value) =
			if (value == null)
				unsetStyle()
			else
				gtk_toolbar_set_style(toolbarPointer, value!!.gtk)

	fun unsetStyle() {
		gtk_toolbar_unset_style(toolbarPointer)
	}
}