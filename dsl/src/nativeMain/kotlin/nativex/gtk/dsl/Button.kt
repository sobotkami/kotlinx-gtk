package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.Grid
import nativex.gtk.widgets.box.Box
import nativex.gtk.widgets.button.Button

@GtkDsl
inline fun Box.appendButton(
	label: String,
	buttonBuilder: Button.() -> Unit = {}
) = Button(label).apply(buttonBuilder).also { append(it) }

@GtkDsl
inline fun Box.prependButton(
	label: String,
	buttonBuilder: Button.() -> Unit = {}
) = Button(label).apply(buttonBuilder).also { prepend(it) }

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