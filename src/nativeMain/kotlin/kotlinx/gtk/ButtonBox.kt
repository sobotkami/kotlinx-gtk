package kotlinx.gtk

import kotlin.gtk.container.ButtonBox
import kotlin.gtk.container.Container
import kotlin.gtk.common.enums.Orientation

@GtkDsl
inline fun Container.buttonBox(buttonBox: ButtonBox) {
	add(buttonBox)
}

@GtkDsl
inline fun Container.buttonBox(
	orientation: Orientation,
	buttonBoxBuilder: ButtonBox.() -> Unit
) = buttonBox(ButtonBox(orientation).apply(buttonBoxBuilder))

@GtkDsl
inline fun Container.verticalButtonBox(
	buttonBoxBuilder: ButtonBox.VerticalButtonBox.() -> Unit
) = buttonBox(ButtonBox.VerticalButtonBox().apply(buttonBoxBuilder))

@GtkDsl
inline fun Container.horizontalButtonBox(
	buttonBoxBuilder: ButtonBox.HorizontalButtonBox.() -> Unit
) = buttonBox(ButtonBox.HorizontalButtonBox().apply(buttonBoxBuilder))
