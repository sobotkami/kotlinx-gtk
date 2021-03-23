package nativex.gtk.dsl

import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.Grid

@GtkDsl
inline fun grid(
	gridBuilder: Grid.() -> Unit = {}
) = Grid().apply(gridBuilder)

@GtkDsl
inline fun Container.grid(
	gridBuilder: Grid.() -> Unit = {}
) = nativex.gtk.dsl.grid(gridBuilder).also { add(it) }

@ExperimentalUnsignedTypes
@GtkDsl
inline fun PackStart.grid(
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	gridBuilder: Grid.() -> Unit,
) = packStart(
	grid(gridBuilder),
	expand,
	fill,
	padding
)

@ExperimentalUnsignedTypes
@GtkDsl
inline fun PackEnd.grid(
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	frameBuilder: Grid.() -> Unit,
) = packEnd(
	grid(frameBuilder),
	expand,
	fill,
	padding
)