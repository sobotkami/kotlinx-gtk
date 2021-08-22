package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.PositionType
import nativex.gtk.widgets.*
import nativex.gtk.widgets.container.*
import nativex.gtk.widgets.ActionBar
import nativex.gtk.widgets.Overlay
import nativex.gtk.widgets.frame.AspectFrame
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

inline fun AspectFrame.box(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
): Box = (this as Widget).box(orientation, spacing, buttonBoxBuilder)

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

class BoxPackStart internal constructor(box: Box) : Box(box), BoxPackable {

	override fun pack(
		child: Widget,
		expand: Boolean,
		fill: Boolean,
		padding: UInt
	) = packStart(child, expand, fill, padding)

}

class BoxPackEnd internal constructor(box: Box) : Box(box), BoxPackable {

	override fun pack(
		child: Widget,
		expand: Boolean,
		fill: Boolean,
		padding: UInt
	) = packEnd(child, expand, fill, padding)
}

@GtkDsl
fun Box.start(builder: BoxPackStart.() -> Unit): BoxPackStart =
	BoxPackStart(this).apply(builder)

@GtkDsl
fun Box.end(builder: BoxPackEnd.() -> Unit): BoxPackEnd =
	BoxPackEnd(this).apply(builder)

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
inline fun Fixed.box(
	orientation: Orientation,
	spacing: Int,
	coordinates: Pair<Int, Int>,
	buttonBoxBuilder: Box.() -> Unit = {}
): Box = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	put(it, coordinates.first, coordinates.second)
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

@GtkDsl
inline fun Paned.boxAddFirst(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
): Box = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	add1(it)
}

@GtkDsl
inline fun Paned.boxAddSecond(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
): Box = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	add2(it)
}

@GtkDsl
inline fun Paned.boxPackFirst(
	orientation: Orientation,
	spacing: Int,
	resize: Boolean,
	shrink: Boolean,
	buttonBoxBuilder: Box.() -> Unit = {}
): Box = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	pack1(it, resize, shrink)
}

@GtkDsl
inline fun Paned.boxPackSecond(
	orientation: Orientation,
	spacing: Int,
	resize: Boolean,
	shrink: Boolean,
	buttonBoxBuilder: Box.() -> Unit = {}
): Box = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	pack2(it, resize, shrink)
}

@GtkDsl
inline fun NumeralPane.boxAdd(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit = {}
): Box = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	add(it)
}

@GtkDsl
inline fun NumeralPane.boxPack(
	orientation: Orientation,
	spacing: Int,
	resize: Boolean,
	shrink: Boolean,
	buttonBoxBuilder: Box.() -> Unit = {}
): Box = Box(orientation, spacing).apply(buttonBoxBuilder).also {
	pack(it, resize, shrink)
}


@GtkDsl
inline fun Widget.box(
	orientation: Orientation,
	spacing: Int,
	buttonBoxBuilder: Box.() -> Unit
) = Box(orientation, spacing).apply(buttonBoxBuilder).also { add(it) }

