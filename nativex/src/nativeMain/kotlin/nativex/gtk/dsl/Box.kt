package nativex.gtk.dsl

import nativex.gtk.common.enums.Orientation
import nativex.gtk.widgets.container.Box
import nativex.gtk.widgets.container.Container


// Container

@GtkDsl
inline fun Container.box(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit
) = add(Box(orientation, spacing).apply(buttonBoxBuilder))

class PackStart internal constructor(box: Box) : Box(box.boxPointer)

class PackEnd internal constructor(box: Box) : Box(box.boxPointer)

@GtkDsl
fun Box.start(builder: PackStart.() -> Unit) {
	PackStart(this).apply(builder)
}

@GtkDsl
fun Box.end(builder: PackEnd.() -> Unit) {
	PackEnd(this).apply(builder)
}