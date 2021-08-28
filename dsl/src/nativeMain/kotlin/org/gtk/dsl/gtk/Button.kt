package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.widgets.Grid
import org.gtk.gtk.widgets.box.Box
import org.gtk.gtk.widgets.button.Button
import org.gtk.gtk.widgets.frame.Frame
import org.gtk.gtk.widgets.windows.Window

@GtkDsl
inline fun Box.button(
	label: String,
	append: Boolean = true,
	buttonBuilder: Button .() -> Unit = {}
) = Button(label).apply(buttonBuilder).also { if (append) append(it) else prepend(it) }

@GtkDsl
inline fun Frame.button(
	label: String,
	buttonBuilder: Button .() -> Unit = {}
) = Button(label).apply(buttonBuilder).also { child = it }

@GtkDsl
inline fun Window.button(
	label: String,
	buttonBuilder: Button .() -> Unit = {}
) = Button(label).apply(buttonBuilder).also { child = it }

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