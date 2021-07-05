package nativex.gtk.widgets.container.bin.button.toggleable.checkable

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.reinterpret
import nativex.async.SignalManager
import nativex.gtk.Signals
import nativex.gtk.connectSignal

/**
 * kotlinx-gtk
 *
 * 05 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRadioButton.html">GtkRadioButton</a>
 */
class RadioButton(val radioButtonPointer: CPointer<GtkRadioButton>) : CheckButton(radioButtonPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRadioButton.html#gtk-radio-button-new-from-widget">
	 *     gtk_radio_button_new_from_widget</a>
	 */
	constructor(radioGroupMember: RadioButton) : this(
		gtk_radio_button_new_from_widget(radioGroupMember.radioButtonPointer)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRadioButton.html#gtk-radio-button-new-with-label-from-widget">
	 *     gtk_radio_button_new_with_label_from_widget</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRadioButton.html#gtk-radio-button-new-with-mnemonic-from-widget">
	 *     gtk_radio_button_new_with_mnemonic_from_widget</a>
	 */
	constructor(radioGroupMember: RadioButton, label: String, mnemonic: Boolean = false) : this(
		if (mnemonic) {
			gtk_radio_button_new_with_mnemonic_from_widget(radioGroupMember.radioButtonPointer, label)
		} else {
			gtk_radio_button_new_with_label_from_widget(radioGroupMember.radioButtonPointer, label)
		}
		!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRadioButton.html#gtk-radio-button-join-group">
	 *     gtk_radio_button_join_group</a>
	 */
	fun join(groupSource: RadioButton) {
		gtk_radio_button_join_group(radioButtonPointer, groupSource.radioButtonPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkRadioButton.html#GtkRadioButton-group-changed">
	 *     group-changed</a>
	 */
	fun addOnGroupChangedCallback(action: () -> Unit): SignalManager =
		SignalManager(
			radioButtonPointer,
			radioButtonPointer.connectSignal(
				Signals.GROUP_CHANGED,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)
}