package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.box.Box
import nativex.gtk.widgets.button.toggleable.ToggleButton

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
@GtkDsl
fun Box.toggleButton(
	label: String? = null,
	mnemonic: Boolean = false,
	buttonBuilder: ToggleButton.() -> Unit = {}
): ToggleButton =
	(if (label != null) ToggleButton(label, mnemonic) else ToggleButton())
		.apply(buttonBuilder)
		.also { append(it) }


@GtkDsl
inline fun ToggleButton.onToggle(noinline onToggle: () -> Unit) {
	addOnToggledCallback(onToggle)
}
