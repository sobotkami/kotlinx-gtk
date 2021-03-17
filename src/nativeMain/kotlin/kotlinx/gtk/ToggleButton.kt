package kotlinx.gtk

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.gtk.container.Container
import kotlin.gtk.container.bin.button.ToggleButton

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
@GtkDsl
fun Container.toggleButton(
	label: String? = null,
	mnemonic: Boolean = false,
	buttonBuilder: ToggleButton.() -> Unit = {}
): ToggleButton =
	(if (label != null) ToggleButton(label, mnemonic) else ToggleButton())
		.apply(buttonBuilder)
		.also { add(it) }

@ExperimentalUnsignedTypes
@ExperimentalCoroutinesApi
@GtkDsl
inline fun ToggleButton.onToggle(crossinline onToggle: suspend () -> Unit) {
	GlobalScope.launch(context = Dispatchers.Unconfined) {
		toggledSignal.collectLatest {
			onToggle()
		}
	}
}
