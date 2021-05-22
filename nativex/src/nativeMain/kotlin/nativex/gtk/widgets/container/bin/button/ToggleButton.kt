package nativex.gtk.widgets.container.bin.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.gtk.Signals
import nativex.gtk.bool
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class ToggleButton internal constructor(
	internal val toggleButtonPointer: CPointer<GtkToggleButton>
) : Button(toggleButtonPointer.reinterpret()) {
	constructor() : this(
		gtk_toggle_button_new()!!.reinterpret()
	)

	constructor(label: String, mnemonic: Boolean = false) : this(
		(if (mnemonic)
			gtk_toggle_button_new_with_label(
				label
			)
		else gtk_toggle_button_new_with_mnemonic(
			label
		))!!.reinterpret()
	)

	fun toggle() {
		gtk_toggle_button_toggled(toggleButtonPointer)
	}

	var mode: Boolean
		get() = gtk_toggle_button_get_mode(toggleButtonPointer).bool
		set(value) = gtk_toggle_button_set_mode(
			toggleButtonPointer,
			value.gtk
		)

	var active: Boolean
		get() = gtk_toggle_button_get_active(toggleButtonPointer).bool
		set(value) = gtk_toggle_button_set_active(
			toggleButtonPointer,
			value.gtk
		)

	var isInconsistent: Boolean
		get() = gtk_toggle_button_get_inconsistent(
			toggleButtonPointer
		)
			.bool
		set(value) = gtk_toggle_button_set_inconsistent(
			toggleButtonPointer,
			value.gtk
		)

	@ExperimentalCoroutinesApi
	
	val toggledSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.TOGGLED)
	}

}