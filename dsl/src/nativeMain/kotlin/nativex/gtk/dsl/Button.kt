package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.Grid
import nativex.gtk.widgets.container.bin.button.Button

@GtkDsl
inline fun Container.button(
	label: String,
	buttonBuilder: Button.() -> Unit = {}
) = Button(label).apply(buttonBuilder).also { add(it) }

@GtkDsl
inline fun Grid.button(
	label: String,
	x: Int,
	y: Int,
	width: Int,
	height: Int,
	buttonBuilder: Button.() -> Unit = {}
) = Button(label).apply(buttonBuilder).also {
	attach(it, x, y, width, height)
}


@GtkDsl
inline fun Button.onClicked(crossinline action: () -> Unit) = addOnClickedCallback { action() }