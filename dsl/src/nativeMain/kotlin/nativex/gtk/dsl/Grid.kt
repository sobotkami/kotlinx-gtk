package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.Grid
import nativex.gtk.widgets.box.Box

@GtkDsl
inline fun grid(
	gridBuilder: Grid.() -> Unit = {}
) = Grid().apply(gridBuilder)

@GtkDsl
inline fun Box.appendGrid(
	gridBuilder: Grid.() -> Unit = {}
) = grid(gridBuilder).also { append(it) }


@GtkDsl
inline fun BoxPackable.grid(
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	gridBuilder: Grid.() -> Unit,
) = pack(
	grid(gridBuilder),
	expand,
	fill,
	padding
)