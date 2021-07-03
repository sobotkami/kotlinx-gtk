package nativex.gtk.widgets.container.bin.toolitem.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.gtk.asWidgetOrNull
import nativex.gtk.bool
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.bin.toolitem.ToolItem

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
open class ToolButton(
	@Suppress("MemberVisibilityCanBePrivate")
	 val toolButtonPointer: CPointer<GtkToolButton>
) : ToolItem(toolButtonPointer.reinterpret()) {

	var label: String?
		get() = gtk_tool_button_get_label(toolButtonPointer)?.toKString()
		set(value) = gtk_tool_button_set_label(toolButtonPointer, value)

	var useUnderline: Boolean
		get() = gtk_tool_button_get_use_underline(toolButtonPointer).bool
		set(value) = gtk_tool_button_set_use_underline(
			toolButtonPointer,
			value.gtk
		)

	var iconName: String?
		get() = gtk_tool_button_get_icon_name(toolButtonPointer)?.toKString()
		set(value) = gtk_tool_button_set_icon_name(toolButtonPointer, value)

	var iconWidget: Widget?
		get() = gtk_tool_button_get_icon_widget(toolButtonPointer).asWidgetOrNull()
		set(value) = gtk_tool_button_set_icon_widget(
			toolButtonPointer,
			value?.widgetPointer
		)

	var labelWidget: Widget?
		get() = gtk_tool_button_get_label_widget(toolButtonPointer)?.let {
			Widget(it)
		}
		set(value) = gtk_tool_button_set_label_widget(
			toolButtonPointer,
			value?.widgetPointer
		)

	constructor(label: String?, iconWidget: Widget?) : this(
		gtk_tool_button_new(
			iconWidget?.widgetPointer,
			label
		)!!.reinterpret()
	)
}