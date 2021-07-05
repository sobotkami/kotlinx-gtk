package nativex.gtk.dsl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import nativex.GtkDsl
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.bin.button.toggleable.ToggleButton

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


@GtkDsl
inline fun ToggleButton.onToggle(noinline onToggle: () -> Unit) {
	addOnToggledCallback(onToggle)
}
