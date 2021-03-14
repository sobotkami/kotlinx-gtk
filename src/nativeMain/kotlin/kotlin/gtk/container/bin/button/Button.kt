package kotlin.gtk.container.bin.button

import gtk.GtkButton
import gtk.gtk_button_new_with_label
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlin.async.callbackSignalFlow
import kotlin.gtk.Signals.CLICKED
import kotlin.gtk.container.bin.Bin

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
class Button internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val buttonPointer: CPointer<GtkButton>
) : Bin(buttonPointer.reinterpret()) {

	constructor(label: String) : this(
		gtk_button_new_with_label(
			label
		)!!.reinterpret()
	)

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val clickedSignal: Flow<Unit> by lazy {
		callbackSignalFlow(CLICKED)
	}

}

