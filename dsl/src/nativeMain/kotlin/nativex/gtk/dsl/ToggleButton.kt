package nativex.gtk.dsl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import nativex.async.launchUnconfined
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.bin.button.ToggleButton

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
	launchUnconfined {
		toggledSignal.collectLatest {
			onToggle()
		}
	}
}
