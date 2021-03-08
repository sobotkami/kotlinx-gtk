package kotlin.gtk

import gtk.GtkButton
import gtk.gtk_button_new_with_label
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
class Button internal constructor(
	internal val buttonPointer: CPointer<GtkButton>
) : Widget(buttonPointer.reinterpret()) {

	constructor(label: String) : this(
		gtk_button_new_with_label(
			label
		)!!.reinterpret()
	)

	@ExperimentalUnsignedTypes
	val clickedSignal: Flow<Int> by lazy {
		MutableSharedFlow<Int>().apply {
			pointer.connectSignal(
				Signals.CLICKED,
				staticCallback,
				StableRef.create {
					tryEmit(0)
				}.asCPointer()
			)

		}
	}
}

