package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.widgets.Grid
import org.gtk.gtk.widgets.box.Box

@GtkDsl
inline fun grid(
	gridBuilder: Grid.() -> Unit = {}
) = Grid().apply(gridBuilder)

@GtkDsl
inline fun Box.appendGrid(
	gridBuilder: Grid.() -> Unit = {}
) = grid(gridBuilder).also { append(it) }