package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.Grid

@GtkDsl
inline fun grid(
	gridBuilder: Grid.() -> Unit = {}
) = Grid().apply(gridBuilder)

@GtkDsl
inline fun Widget.grid(
	gridBuilder: Grid.() -> Unit = {}
) = nativex.gtk.dsl.grid(gridBuilder).also { add(it) }


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