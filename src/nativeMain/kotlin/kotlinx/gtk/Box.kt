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
