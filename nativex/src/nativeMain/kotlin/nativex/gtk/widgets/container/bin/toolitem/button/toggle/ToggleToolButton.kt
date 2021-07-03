package nativex.gtk.widgets.container.bin.toolitem.button.toggle

import gtk.GtkToggleToolButton
import gtk.gtk_toggle_tool_button_get_active
import gtk.gtk_toggle_tool_button_new
import gtk.gtk_toggle_tool_button_set_active
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.bool
import nativex.gtk.gtk
import nativex.gtk.widgets.container.bin.toolitem.button.ToolButton

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 */
open class ToggleToolButton(
	@Suppress("MemberVisibilityCanBePrivate")
	 val toggleToolButton: CPointer<GtkToggleToolButton>
) : ToolButton(toggleToolButton.reinterpret()) {
	constructor() : this(gtk_toggle_tool_button_new()!!.reinterpret())

	var active: Boolean
		get() = gtk_toggle_tool_button_get_active(toggleToolButton).bool
		set(value) = gtk_toggle_tool_button_set_active(
			toggleToolButton,
			value.gtk
		)

}