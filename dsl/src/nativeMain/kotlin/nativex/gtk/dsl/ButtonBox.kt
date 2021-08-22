package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.box.Box
import nativex.gtk.widgets.ButtonBox
import nativex.gtk.widgets.Fixed
import nativex.gtk.widgets.ListBox

// Container

@GtkDsl
inline fun Widget.verticalButtonBox(
	buttonBoxBuilder: ButtonBox.VerticalButtonBox.() -> Unit
) = add(ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder))

@GtkDsl
inline fun Widget.horizontalButtonBox(
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
) = prepend(nativex.gtk.widgets.ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder))

@GtkDsl
inline fun ListBox.horizontalButtonBox(
	buttonBoxBuilder: ButtonBox.HorizontalButtonBox.() -> Unit
) = prepend(nativex.gtk.widgets.ButtonBox.HorizontalButtonBox().apply(buttonBoxBuilder))


@GtkDsl
inline fun ListBox.verticalButtonBox(
	buttonBoxBuilder: ButtonBox.VerticalButtonBox.() -> Unit,
	position: Int
) = insert(nativex.gtk.widgets.ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder), position)

@GtkDsl
inline fun ListBox.horizontalButtonBox(
	buttonBoxBuilder: ButtonBox.HorizontalButtonBox.() -> Unit,
	position: Int
) = insert(nativex.gtk.widgets.ButtonBox.HorizontalButtonBox().apply(buttonBoxBuilder), position)

// Box


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


@GtkDsl
inline fun BoxPackable.verticalButtonBox(
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	buttonBoxBuilder: ButtonBox.VerticalButtonBox.() -> Unit,
) = pack(
	ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder),
	expand,
	fill,
	padding
)


@GtkDsl
inline fun BoxPackable.horizontalButtonBox(
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	buttonBoxBuilder: ButtonBox.HorizontalButtonBox.() -> Unit
) = pack(
	ButtonBox.HorizontalButtonBox().apply(buttonBoxBuilder),
	expand,
	fill,
	padding
)

