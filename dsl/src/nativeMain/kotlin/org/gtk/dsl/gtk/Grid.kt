package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.widgets.Grid
import org.gtk.gtk.widgets.box.Box
import org.gtk.gtk.widgets.windows.Window

@GtkDsl
inline fun grid(
	gridBuilder: Grid.() -> Unit = {}
) = Grid().apply(gridBuilder)

@GtkDsl
inline fun Window.grid(
	gridBuilder: Grid.() -> Unit = {}
) = Grid().apply(gridBuilder).also { child = it }

@GtkDsl
inline fun Box.appendGrid(
	gridBuilder: Grid.() -> Unit = {}
) = grid(gridBuilder).also { append(it) }