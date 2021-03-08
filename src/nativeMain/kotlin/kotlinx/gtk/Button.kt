package kotlinx.gtk

import kotlin.gtk.Button
import kotlin.gtk.Container

@GtkDsl
inline fun Container.button(label: String, buttonBuilder: Button.() -> Unit = {}) =
	add(Button(label).apply(buttonBuilder))
