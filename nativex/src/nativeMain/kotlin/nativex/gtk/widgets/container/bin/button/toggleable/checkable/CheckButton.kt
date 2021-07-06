package nativex.gtk.widgets.container.bin.button.toggleable.checkable

import gtk.GtkCheckButton
import gtk.gtk_check_button_new
import gtk.gtk_check_button_new_with_label
import gtk.gtk_check_button_new_with_mnemonic
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.container.bin.button.toggleable.ToggleButton

/**
 * kotlinx-gtk
 *
 * 05 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkCheckButton.html">GtkCheckButton</a>
 */
open class CheckButton(val checkButtonPointer: CPointer<GtkCheckButton>) :
	ToggleButton(checkButtonPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkCheckButton.html#gtk-check-button-new">
	 *     gtk_check_button_new</a>
	 */
	constructor() : this(gtk_check_button_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkCheckButton.html#gtk-check-button-new-with-label">
	 *     gtk_check_button_new_with_label</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkCheckButton.html#gtk-check-button-new-with-mnemonic">
	 *     gtk_check_button_new_with_mnemonic</a>
	 */
	constructor(label: String, mnemonic: Boolean = false) : this(
		(if (mnemonic)
			gtk_check_button_new_with_label(
				label
			)
		else gtk_check_button_new_with_mnemonic(
			label
		))!!.reinterpret()
	)
}