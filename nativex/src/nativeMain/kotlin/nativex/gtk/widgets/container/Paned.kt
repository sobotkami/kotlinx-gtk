package nativex.gtk.widgets.container

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.async.signalFlow
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
 * @see <a href=""></a>
 */
open class Paned internal constructor(
	internal val panedPointer: CPointer<GtkPaned>
) : Container(panedPointer.reinterpret()) {
	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-new"></a>
	 */
	constructor(orientation: Orientation) : this(gtk_paned_new(orientation.gtk)!!.reinterpret())

	/**
	 * Constructor for DSL to have fun
	 */
	constructor(paned: Paned) : this(paned.panedPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-add1">gtk_paned_add1</a>
	 */
	fun add1(widget: Widget) =
		gtk_paned_add1(panedPointer, widget.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-add2">gtk_paned_add2</a>
	 */
	fun add2(widget: Widget) =
		gtk_paned_add2(panedPointer, widget.widgetPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-pack1">gtk_paned_pack1</a>
	 */
	fun pack1(widget: Widget, resize: Boolean, shrink: Boolean) =
		gtk_paned_pack1(
			panedPointer,
			widget.widgetPointer,
			resize.gtk,
			shrink.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-pack2">gtk_paned_pack2</a>
	 */
	fun pack2(widget: Widget, resize: Boolean, shrink: Boolean) =
		gtk_paned_pack2(
			panedPointer,
			widget.widgetPointer,
			resize.gtk,
			shrink.gtk
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-get-child1">gtk_paned_get_child1</a>
	 */
	val child1: Widget?
		get() = gtk_paned_get_child1(panedPointer).asWidgetOrNull()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-get-child2">gtk_paned_get_child2</a>
	 */
	val child2: Widget?
		get() = gtk_paned_get_child2(panedPointer).asWidgetOrNull()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-get-position">gtk_paned_get_position</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-set-position">gtk_paned_set_position</a>
	 */
	var position: Int
		get() = gtk_paned_get_position(panedPointer)
		set(value) = gtk_paned_set_position(panedPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-get-handle-window">gtk_paned_get_handle_window</a>
	 */
	val handleWindow: Window
		get() = Window(gtk_paned_get_handle_window(panedPointer)!!)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-get-wide-handle">gtk_paned_get_wide_handle</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-set-wide-handle">gtk_paned_set_wide_handle</a>
	 */
	var wideHandle: Boolean
		get() = gtk_paned_get_wide_handle(panedPointer).bool
		set(value) = gtk_paned_set_wide_handle(panedPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#GtkPaned-accept-position"></a>
	 */
	@ExperimentalCoroutinesApi
	val acceptPositionSignal: Flow<Unit> by signalFlow(Signals.ACCEPT_POSITION)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#GtkPaned-cancel-position"></a>
	 */
	@ExperimentalCoroutinesApi
	val cancelPositionSignal: Flow<Unit> by signalFlow(Signals.CANCEL_POSITION)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#GtkPaned-cycle-child-focus"></a>
	 */
	@ExperimentalCoroutinesApi
	val cycleChildFocusSignal: Flow<Unit> by signalFlow(Signals.CYCLE_CHILD_FOCUS)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#GtkPaned-cycle-handle-focus"></a>
	 */
	@ExperimentalCoroutinesApi
	val cycleHandleFocusSignal: Flow<Unit> by signalFlow(Signals.CYCLE_HANDLE_FOCUS)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#GtkPaned-move-handle"></a>
	 */
	@ExperimentalCoroutinesApi
	val moveHandleSignal: Flow<ScrollType> by signalFlow(Signals.MOVE_HANDLE, staticMoveHandleCallback)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#GtkPaned-toggle-handle-focus"></a>
	 */
	@ExperimentalCoroutinesApi
	val toggleHandleFocusSignal: Flow<Unit> by signalFlow(Signals.TOGGLE_HANDLE_FOCUS)


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