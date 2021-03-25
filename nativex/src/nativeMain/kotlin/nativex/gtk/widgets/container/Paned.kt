package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.gdk.Window
import nativex.gtk.Signals
import nativex.gtk.asWidgetOrNull
import nativex.gtk.bool
import nativex.gtk.common.enums.Orientation
import nativex.gtk.common.enums.ScrollType
import nativex.gtk.gtk
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 24 / 03 / 2021
 */
open class Paned internal constructor(
	internal val panedPointer: CPointer<GtkPaned>
) : Container(panedPointer.reinterpret()) {
	constructor(orientation: Orientation) : this(gtk_paned_new(orientation.gtk)!!.reinterpret())
	constructor(paned: Paned) : this(paned.panedPointer)

	fun add1(widget: Widget) =
		gtk_paned_add1(panedPointer, widget.widgetPointer)

	fun add2(widget: Widget) =
		gtk_paned_add2(panedPointer, widget.widgetPointer)

	fun pack1(widget: Widget, resize: Boolean, shrink: Boolean) =
		gtk_paned_pack1(
			panedPointer,
			widget.widgetPointer,
			resize.gtk,
			shrink.gtk
		)

	fun pack2(widget: Widget, resize: Boolean, shrink: Boolean) =
		gtk_paned_pack2(
			panedPointer,
			widget.widgetPointer,
			resize.gtk,
			shrink.gtk
		)

	val child1: Widget?
		get() = gtk_paned_get_child1(panedPointer).asWidgetOrNull()

	val child2: Widget?
		get() = gtk_paned_get_child2(panedPointer).asWidgetOrNull()

	var position: Int
		get() = gtk_paned_get_position(panedPointer)
		set(value) = gtk_paned_set_position(panedPointer, value)

	val handleWindow: Window
		get() = Window(gtk_paned_get_handle_window(panedPointer)!!)

	var wideHandle: Boolean
		get() = gtk_paned_get_wide_handle(panedPointer).bool
		set(value) = gtk_paned_set_wide_handle(panedPointer, value.gtk)

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val acceptPositionSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.ACCEPT_POSITION)
	}

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val cancelPositionSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.CANCEL_POSITION)
	}

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val cycleChildFocusSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.CYCLE_CHILD_FOCUS)
	}

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val cycleHandleFocusSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.CYCLE_HANDLE_FOCUS)
	}

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val moveHandleSignal: Flow<ScrollType> by lazy {
		callbackSignalFlow(Signals.MOVE_HANDLE, staticMoveHandleCallback)
	}

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	val toggleHandleFocusSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.TOGGLE_HANDLE_FOCUS)
	}

	companion object {
		private val staticMoveHandleCallback: GCallback =
			staticCFunction { _: gpointer?, scrollType: GtkScrollType, data: gpointer? ->
				data?.asStableRef<(ScrollType) -> Unit>()
					?.get()
					?.invoke(ScrollType.valueOf(scrollType)!!)
				Unit
			}.reinterpret()
	}
}