package nativex.gtk.widgets.container.bin.toolitem.button.toggle

import gtk.GtkRadioToolButton
import gtk.gtk_radio_tool_button_new
import gtk.gtk_radio_tool_button_new_from_widget
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 20 / 03 / 2021
 *
 * TODO Figure out GSList group
 */
class RadioToolButton(
	 val radioToolButtonPointer: CPointer<GtkRadioToolButton>
) : ToggleToolButton(radioToolButtonPointer.reinterpret()) {
	/**
	 * TODO Figure out GSList group parameter
	 */
	@Deprecated("Improper API Usage", level = DeprecationLevel.WARNING)
	constructor() : this(gtk_radio_tool_button_new(null)!!.reinterpret())

	constructor(radioToolButton: RadioToolButton) : this(
		gtk_radio_tool_button_new_from_widget(radioToolButton.radioToolButtonPointer)!!.reinterpret()
	)
}