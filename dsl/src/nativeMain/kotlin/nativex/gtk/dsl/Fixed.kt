package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.Fixed

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
@GtkDsl
fun Container.fixed(
	fixedBuilder: Fixed.() -> Unit
): Fixed {
	val fixed = Fixed()
	add(fixed.apply(fixedBuilder))
	return fixed
}

class FixedMoving internal constructor(
	internal val widget: Fixed.FixedWidget
)

@GtkDsl
infix fun Fixed.move(
	widget: Fixed.FixedWidget
) = FixedMoving(widget)


@GtkDsl
infix fun FixedMoving.to(coordinates: Pair<Int, Int>) =
	widget.move(coordinates.first, coordinates.second)

@GtkDsl
infix fun Fixed.FixedWidget.moveTo(coordinates: Pair<Int, Int>) =
	move(coordinates.first, coordinates.second)
