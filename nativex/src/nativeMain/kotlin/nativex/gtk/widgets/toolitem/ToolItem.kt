package nativex.gtk.widgets.toolitem

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.glib.bool
import nativex.glib.gtk
import nativex.gtk.IconSize
import nativex.gtk.SizeGroup
import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.ToolbarStyle
import nativex.gtk.widgets.Widget
import nativex.pango.EllipsizeMode

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
open class ToolItem(
	@Suppress("MemberVisibilityCanBePrivate")
	 val toolItemPointer: CPointer<GtkToolItem>
) : Widget(toolItemPointer.reinterpret()) {

	var homogeneous: Boolean
		get() = gtk_tool_item_get_homogeneous(
			toolItemPointer
		).bool
		set(value) = gtk_tool_item_set_homogeneous(
			toolItemPointer,
			value.gtk
		)

	var toolExpand: Boolean
		get() = gtk_tool_item_get_expand(
			toolItemPointer
		).bool
		set(value) = gtk_tool_item_set_expand(
			toolItemPointer,
			value.gtk
		)

	var useDragWindow: Boolean
		get() = gtk_tool_item_get_use_drag_window(
			toolItemPointer
		).bool
		set(value) = gtk_tool_item_set_use_drag_window(
			toolItemPointer,
			value.gtk
		)

	var visibleHorizontal: Boolean
		get() = gtk_tool_item_get_visible_horizontal(
			toolItemPointer
		).bool
		set(value) = gtk_tool_item_set_visible_horizontal(
			toolItemPointer,
			value.gtk
		)

	var visibleVertical: Boolean
		get() = gtk_tool_item_get_visible_vertical(
			toolItemPointer
		).bool
		set(value) = gtk_tool_item_set_visible_vertical(
			toolItemPointer,
			value.gtk
		)

	var isImportant: Boolean
		get() = gtk_tool_item_get_is_important(
			toolItemPointer
		).bool
		set(value) = gtk_tool_item_set_is_important(
			toolItemPointer,
			value.gtk
		)

	val ellipsizeMode: EllipsizeMode
		get() = EllipsizeMode.valueOf(
			gtk_tool_item_get_ellipsize_mode(
				toolItemPointer
			)
		)!!
	val iconSize: IconSize
		get() = IconSize.valueOf(
			gtk_tool_item_get_icon_size(
				toolItemPointer
			)
		)!!

	val orientation: Orientation
		get() = Orientation.valueOf(
			gtk_tool_item_get_orientation(
				toolItemPointer
			)
		)!!

	val toolBarStyle: ToolbarStyle
		get() = ToolbarStyle.valueOf(
			gtk_tool_item_get_toolbar_style(
				toolItemPointer
			)
		)!!

	val alignment: Float
		get() = gtk_tool_item_get_text_alignment(toolItemPointer)

	val textOrientation: Orientation
		get() = Orientation.valueOf(
			gtk_tool_item_get_orientation(
				toolItemPointer
			)
		)!!

	val proxyMenuItem: Widget?
		get() = gtk_tool_item_retrieve_proxy_menu_item(toolItemPointer)?.let {
			Widget(
				it
			)
		}

	val textSizeGroup: SizeGroup
		get() =
			SizeGroup(gtk_tool_item_get_text_size_group(toolItemPointer)!!)

	constructor() : this(gtk_tool_item_new()!!.reinterpret())

	fun setTooltipText(text: String) {
		gtk_tool_item_set_tooltip_text(toolItemPointer, text)
	}

	fun setTooltipMarkup(markup: String) {
		gtk_tool_item_set_tooltip_markup(toolItemPointer, markup)
	}

	fun getProxyMenuItem(menuItemId: String): Widget? =
		gtk_tool_item_get_proxy_menu_item(
			toolItemPointer,
			menuItemId
		)?.let { Widget(it) }

	fun setProxyMenuItem(menuItemId: String, widget: Widget) {
		gtk_tool_item_set_proxy_menu_item(
			toolItemPointer,
			menuItemId,
			widget.widgetPointer
		)
	}

	fun rebuildMenu() {
		gtk_tool_item_rebuild_menu(toolItemPointer)
	}

	fun toolbarReconfigured() {
		gtk_tool_item_toolbar_reconfigured(toolItemPointer)
	}

	companion object{

	}
}