package kotlin.gtk

import gtk.GtkButton
import gtk.gtk_button_new_with_label
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow

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

	@ExperimentalUnsignedTypes
	val clickedSignal: Flow<Unit> by lazy {
		MutableStateFlow(Unit).apply {
			buttonPointer.connectSignal(
				Signals.CLICKED,
				staticCallback,
				StableRef.create {
					println("Tick ${tryEmit(Unit)}")
				}.asCPointer()
			)
		}

	}
}

