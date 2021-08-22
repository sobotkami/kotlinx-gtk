package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.frame.Frame

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
@GtkDsl
fun Widget.frame(
	label: String? = null,
	frameBuilder: Frame.() -> Unit = {}
) =
	Frame(label).apply(frameBuilder).also { add(it) }


@GtkDsl
inline fun BoxPackable.frame(
	label: String? = null,
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	frameBuilder: Frame.() -> Unit,
) = pack(
	Frame(label).apply(frameBuilder),
	expand,
	fill,
	padding
)