package nativex.gtk.dsl

import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.PositionType
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.Box
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.Grid
import nativex.gtk.widgets.container.Notebook


// Container
@GtkDsl
inline fun box(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit
) = Box(orientation, spacing).apply(buttonBoxBuilder)

@GtkDsl
@ExperimentalUnsignedTypes
inline fun PackStart.box(
	orientation: Orientation,
	spacing: Int,
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	buttonBoxBuilder: Box.() -> Unit
) = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	packStart(it, expand, fill, padding)
}

@GtkDsl
@ExperimentalUnsignedTypes
inline fun PackEnd.box(
	orientation: Orientation,
	spacing: Int,
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	buttonBoxBuilder: Box.() -> Unit
) = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	packEnd(it, expand, fill, padding)
}

@GtkDsl
inline fun Grid.box(
	orientation: Orientation,
	spacing: Int,
	left: Int,
	top: Int,
	width: Int,
	height: Int,
	buttonBoxBuilder: Box.() -> Unit
) = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	attach(it, left, top, width, height)
}

@GtkDsl
inline fun Grid.boxAttachNextTo(
	orientation: Orientation,
	spacing: Int,
	sibling: Widget? = null,
	positionType: PositionType,
	width: Int,
	height: Int,
	buttonBoxBuilder: Box.() -> Unit
) = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	attachNextTo(it, sibling, positionType, width, height)
}

inline fun Notebook.box(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit
) = Box(orientation, spacing).apply(buttonBoxBuilder).also {

}

@GtkDsl
inline fun Container.box(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit
) = Box(orientation, spacing).apply(buttonBoxBuilder).also { add(it) }


class PackStart internal constructor(box: Box) : Box(box)

class PackEnd internal constructor(box: Box) : Box(box)

@GtkDsl
fun Box.start(builder: PackStart.() -> Unit) {
	PackStart(this).apply(builder)
}

@GtkDsl
fun Box.end(builder: PackEnd.() -> Unit) {
	PackEnd(this).apply(builder)
}