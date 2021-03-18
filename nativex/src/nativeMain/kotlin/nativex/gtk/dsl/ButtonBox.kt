package nativex.gtk.dsl

import nativex.gtk.widgets.container.Box
import nativex.gtk.widgets.container.ButtonBox
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.Fixed
import nativex.gtk.widgets.container.bin.ListBox

// Container

@GtkDsl
inline fun Container.verticalButtonBox(
	buttonBoxBuilder: ButtonBox.VerticalButtonBox.() -> Unit
) = add(ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder))

@GtkDsl
inline fun Container.horizontalButtonBox(
	buttonBoxBuilder: ButtonBox.HorizontalButtonBox.() -> Unit
) = add(ButtonBox.HorizontalButtonBox().apply(buttonBoxBuilder))

// Fixed

@GtkDsl
inline fun Fixed.verticalButtonBox(
	x: Int,
	y: Int,
	buttonBoxBuilder: ButtonBox.VerticalButtonBox.() -> Unit,
) = put(ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder), x, y)

@GtkDsl
inline fun Fixed.horizontalButtonBox(
	x: Int,
	y: Int,
	buttonBoxBuilder: ButtonBox.HorizontalButtonBox.() -> Unit,
) = put(ButtonBox.HorizontalButtonBox().apply(buttonBoxBuilder), x, y)

// ListBox

@GtkDsl
inline fun ListBox.verticalButtonBox(
	buttonBoxBuilder: ButtonBox.VerticalButtonBox.() -> Unit
) = prepend(ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder))

@GtkDsl
inline fun ListBox.horizontalButtonBox(
	buttonBoxBuilder: ButtonBox.HorizontalButtonBox.() -> Unit
) = prepend(ButtonBox.HorizontalButtonBox().apply(buttonBoxBuilder))


@GtkDsl
inline fun ListBox.verticalButtonBox(
	buttonBoxBuilder: ButtonBox.VerticalButtonBox.() -> Unit,
	position: Int
) = insert(ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder), position)

@GtkDsl
inline fun ListBox.horizontalButtonBox(
	buttonBoxBuilder: ButtonBox.HorizontalButtonBox.() -> Unit,
	position: Int
) = insert(ButtonBox.HorizontalButtonBox().apply(buttonBoxBuilder), position)

// Box

@ExperimentalUnsignedTypes
@GtkDsl
inline fun Box.verticalButtonBoxStart(
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	buttonBoxBuilder: ButtonBox.VerticalButtonBox.() -> Unit,

	) = packStart(
	ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder),
	expand,
	fill,
	padding
)

@ExperimentalUnsignedTypes
@GtkDsl
inline fun Box.horizontalButtonBoxStart(
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	buttonBoxBuilder: ButtonBox.HorizontalButtonBox.() -> Unit,
) = packStart(
	ButtonBox.HorizontalButtonBox().apply(buttonBoxBuilder),
	expand,
	fill,
	padding
)

@ExperimentalUnsignedTypes
@GtkDsl
inline fun Box.verticalButtonBoxEnd(
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	buttonBoxBuilder: ButtonBox.VerticalButtonBox.() -> Unit,
) = packEnd(
	ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder),
	expand,
	fill,
	padding
)

@ExperimentalUnsignedTypes
@GtkDsl
inline fun Box.horizontalButtonBoxEnd(
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	buttonBoxBuilder: ButtonBox.HorizontalButtonBox.() -> Unit,
) = packEnd(
	ButtonBox.HorizontalButtonBox().apply(buttonBoxBuilder),
	expand,
	fill,
	padding
)

@ExperimentalUnsignedTypes
@GtkDsl
inline fun PackStart.verticalButtonBox(
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	buttonBoxBuilder: ButtonBox.VerticalButtonBox.() -> Unit,
) = packStart(
	ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder),
	expand,
	fill,
	padding
)

@ExperimentalUnsignedTypes
@GtkDsl
inline fun PackStart.horizontalButtonBox(
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	buttonBoxBuilder: ButtonBox.HorizontalButtonBox.() -> Unit
) = packStart(
	ButtonBox.HorizontalButtonBox().apply(buttonBoxBuilder),
	expand,
	fill,
	padding
)

@ExperimentalUnsignedTypes
@GtkDsl
inline fun PackEnd.verticalButtonBox(
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	buttonBoxBuilder: ButtonBox.VerticalButtonBox.() -> Unit,
) = packEnd(
	ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder),
	expand,
	fill,
	padding
)

@ExperimentalUnsignedTypes
@GtkDsl
inline fun PackEnd.horizontalButtonBox(
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	buttonBoxBuilder: ButtonBox.HorizontalButtonBox.() -> Unit,
) = packEnd(
	ButtonBox.HorizontalButtonBox().apply(buttonBoxBuilder),
	expand,
	fill,
	padding
)




