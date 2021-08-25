package org.gtk.dsl.gtk

import nativex.GtkDsl
import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.PositionType
import nativex.gtk.widgets.*
import nativex.gtk.widgets.box.Box


@GtkDsl
inline fun box(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
): Box = Box(orientation, spacing).apply(buttonBoxBuilder)


@GtkDsl
inline fun ActionBar.boxStart(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
): Box = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	packStart(it)
}

@GtkDsl
inline fun ActionBar.boxEnd(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
): Box = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	packEnd(it)
}

@GtkDsl
inline fun ActionBarPackStart.box(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
) = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	packStart(it)
}

@GtkDsl
inline fun ActionBarPackEnd.box(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
) = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	packEnd(it)
}

/**
 * Similar to [SimplePacking], except specifically for [Box]
 *
 * @see SimplePacking
 */
interface BoxPackable {

	fun pack(
		child: Widget,
		expand: Boolean,
		fill: Boolean,
		padding: UInt
	)
}


@GtkDsl

inline fun BoxPackable.box(
	orientation: Orientation,
	spacing: Int,
	expand: Boolean,
	fill: Boolean,
	padding: UInt,
	buttonBoxBuilder: Box.() -> Unit
): Box = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	pack(it, expand, fill, padding)
}

@GtkDsl
inline fun FlowBox.box(
	orientation: Orientation,
	spacing: Int,
	position: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
) = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	insert(it, position)
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


@GtkDsl
inline fun HeaderBar.boxEnd(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
): Box = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	packEnd(it)
}

@GtkDsl
inline fun HeaderBarPackStart.box(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
) = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	packStart(it)
}

@GtkDsl
inline fun HeaderBarPackEnd.box(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
) = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	packEnd(it)
}

@GtkDsl
inline fun Notebook.box(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit
) = Box(orientation, spacing).apply(buttonBoxBuilder).also {

}

@GtkDsl
inline fun Overlay.boxOverlay(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
): Box = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	addOverlay(it)
}