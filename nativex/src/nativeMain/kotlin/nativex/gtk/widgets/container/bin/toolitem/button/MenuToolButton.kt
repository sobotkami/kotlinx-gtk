package nativex.gtk.widgets.container.bin.toolitem.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.asWidgetOrNull
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
class MenuToolButton internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val menuToolButton: CPointer<GtkMenuToolButton>
) : ToolButton(menuToolButton.reinterpret()) {

	var menu: Widget?
		get() =
			gtk_menu_tool_button_get_menu(menuToolButton).asWidgetOrNull()
		set(value) =
			gtk_menu_tool_button_set_menu(menuToolButton, value?.widgetPointer)

	constructor(
		widget: Widget?,
		label: String?
	) : this(
		gtk_menu_tool_button_new(
			widget?.widgetPointer,
			label
		)!!.reinterpret()
	)

	fun setArrowTooltipText(text: String) {
		gtk_menu_tool_button_set_arrow_tooltip_text(menuToolButton, text)
	}

	fun setArrowTooltipMarkup(text: String) {
		gtk_menu_tool_button_set_arrow_tooltip_markup(menuToolButton, text)
	}
}