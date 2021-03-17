package kotlinx.gtk

import kotlin.gtk.common.enums.Orientation
import kotlin.gtk.container.Box
import kotlin.gtk.container.Container

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