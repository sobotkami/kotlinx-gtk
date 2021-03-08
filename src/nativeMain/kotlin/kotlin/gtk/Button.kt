package kotlin.gtk

import gtk.GtkButton
import gtk.g_signal_handler_disconnect
import gtk.gtk_button_new_with_label
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
class Button internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val buttonPointer: CPointer<GtkButton>
) : Widget(buttonPointer.reinterpret()) {

	constructor(label: String) : this(
		gtk_button_new_with_label(
			label
		)!!.reinterpret()
	)

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val clickedSignal: Flow<Unit> by lazy {
		callbackFlow {

			val id = buttonPointer.connectSignal(
				Signals.CLICKED,
				staticCallback,
				StableRef.create {
					offer(Unit)
				}.asCPointer()
			)

			awaitClose {
				g_signal_handler_disconnect(buttonPointer, id)
			}
		}
	}

}

