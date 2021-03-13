package kotlinx.gtk

import kotlin.gtk.common.enums.Orientation
import kotlin.gtk.container.ButtonBox
import kotlin.gtk.container.Container
import kotlin.gtk.container.Fixed


@GtkDsl
inline fun Container.buttonBox(
	orientation: Orientation,
	buttonBoxBuilder: ButtonBox.() -> Unit
) = add(ButtonBox(orientation).apply(buttonBoxBuilder))

@GtkDsl
inline fun Container.verticalButtonBox(
	buttonBoxBuilder: ButtonBox.VerticalButtonBox.() -> Unit
) = add(ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder))

@GtkDsl
inline fun Container.horizontalButtonBox(
	buttonBoxBuilder: ButtonBox.HorizontalButtonBox.() -> Unit
) = add(ButtonBox.HorizontalButtonBox().apply(buttonBoxBuilder))

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
