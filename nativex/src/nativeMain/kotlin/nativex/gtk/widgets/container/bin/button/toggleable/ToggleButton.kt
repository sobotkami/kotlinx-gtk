package nativex.gtk.widgets.container.bin.button.toggleable

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.reinterpret
import nativex.async.SignalManager
import nativex.gtk.Signals
import nativex.gtk.bool
import nativex.gtk.connectSignal
import nativex.gtk.gtk
import nativex.gtk.widgets.container.bin.button.Button

/**
 * kotlinx-gtk
 *
 * 16 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToggleButton.html">GtkToggleButton</a>
 */
class ToggleButton(
	val toggleButtonPointer: CPointer<GtkToggleButton>
) : Button(toggleButtonPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToggleButton.html#gtk-toggle-button-get-mode">
	 *     gtk_toggle_button_get_mode</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToggleButton.html#gtk-toggle-button-set-mode">
	 *     gtk_toggle_button_set_mode</a>
	 */
	var mode: Boolean
		get() = gtk_toggle_button_get_mode(toggleButtonPointer).bool
		set(value) = gtk_toggle_button_set_mode(
			toggleButtonPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToggleButton.html#gtk-toggle-button-get-active">
	 *     gtk_toggle_button_get_active</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToggleButton.html#gtk-toggle-button-set-active">
	 *     gtk_toggle_button_set_active</a>
	 */
	var active: Boolean
		get() = gtk_toggle_button_get_active(toggleButtonPointer).bool
		set(value) = gtk_toggle_button_set_active(
			toggleButtonPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToggleButton.html#gtk-toggle-button-get-inconsistent">
	 *     gtk_toggle_button_get_inconsistent</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToggleButton.html#gtk-toggle-button-set-inconsistent">
	 *     gtk_toggle_button_set_inconsistent</a>
	 */
	var isInconsistent: Boolean
		get() = gtk_toggle_button_get_inconsistent(
			toggleButtonPointer
		)
			.bool
		set(value) = gtk_toggle_button_set_inconsistent(
			toggleButtonPointer,
			value.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToggleButton.html#gtk-toggle-button-new">gtk_toggle_button_new</a>
	 */
	constructor() : this(
		gtk_toggle_button_new()!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToggleButton.html#gtk-toggle-button-new-with-label">
	 *     gtk_toggle_button_new_with_label</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToggleButton.html#gtk-toggle-button-new-with-mnemonic">
	 *     gtk_toggle_button_new_with_mnemonic</a>
	 */
	constructor(label: String, mnemonic: Boolean = false) : this(
		(if (mnemonic)
			gtk_toggle_button_new_with_label(
				label
			)
		else gtk_toggle_button_new_with_mnemonic(
			label
		))!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToggleButton.html#gtk-toggle-button-toggled">
	 *     gtk_toggle_button_toggled</a>
	 */
	fun toggle() {
		gtk_toggle_button_toggled(toggleButtonPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkToggleButton.html#GtkToggleButton-toggled">toggled</a>
	 */
	fun addOnToggledCallback(action: () -> Unit): SignalManager =
		SignalManager(
			toggleButtonPointer,
			toggleButtonPointer.connectSignal(
				Signals.TOGGLED,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)
}