package kotlin.gtk.container.bin.button

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlin.async.callbackSignalFlow
import kotlin.gtk.Signals
import kotlin.gtk.from
import kotlin.gtk.gtkValue

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
		get() = Boolean.from(gtk_toggle_button_get_mode(toggleButtonPointer))
		set(value) = gtk_toggle_button_set_mode(
			toggleButtonPointer,
			value.gtkValue
		)

	var active: Boolean
		get() = Boolean.from(gtk_toggle_button_get_active(toggleButtonPointer))
		set(value) = gtk_toggle_button_set_active(
			toggleButtonPointer,
			value.gtkValue
		)

	var isInconsistent: Boolean
		get() = Boolean.from(
			gtk_toggle_button_get_inconsistent(
				toggleButtonPointer
			)
		)
		set(value) = gtk_toggle_button_set_inconsistent(
			toggleButtonPointer,
			value.gtkValue
		)

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val toggledSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.TOGGLED)
	}

}