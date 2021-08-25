package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.widgets.Fixed
import org.gtk.gtk.widgets.box.Box

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
@GtkDsl
fun Box.appendFixed(
	fixedBuilder: Fixed.() -> Unit
): Fixed {
	val fixed = Fixed()
	append(fixed.apply(fixedBuilder))
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
infix fun FixedMoving.to(coordinates: Pair<Double, Double>) =
	widget.move(coordinates.first, coordinates.second)

@GtkDsl
infix fun Fixed.FixedWidget.moveTo(coordinates: Pair<Double, Double>) =
	move(coordinates.first, coordinates.second)
