package nativex.gtk.dsl

import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.bin.frame.Frame

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
@GtkDsl
fun Container.frame(
	label: String? = null,
	frameBuilder: Frame.() -> Unit = {}
) =
	Frame(label).apply(frameBuilder).also { add(it) }


@ExperimentalUnsignedTypes
@GtkDsl
inline fun PackStart.frame(
	label: String? = null,
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	frameBuilder: Frame.() -> Unit,
) = packStart(
	Frame(label).apply(frameBuilder),
	expand,
	fill,
	padding
)


@ExperimentalUnsignedTypes
@GtkDsl
inline fun PackEnd.frame(
	label: String? = null,
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	frameBuilder: Frame.() -> Unit,
) = packEnd(
	Frame(label).apply(frameBuilder),
	expand,
	fill,
	padding
)