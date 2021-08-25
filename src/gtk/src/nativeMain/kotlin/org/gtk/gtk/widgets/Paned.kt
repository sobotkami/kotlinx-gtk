package org.gtk.gtk.widgets
import glib.gboolean
import glib.gpointer
import gobject.GCallback
import gtk.*
import kotlinx.cinterop.*
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.SignalManager
import org.gtk.gobject.Signals
import org.gtk.gobject.connectSignal
import org.gtk.gtk.common.enums.Orientation
import org.gtk.gtk.common.enums.ScrollType

/**
 * kotlinx-gtk
 * 24 / 03 / 2021
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html">GtkPaned</a>
 */
open class Paned(
	 val panedPointer: CPointer<GtkPaned>
) : Widget(panedPointer.reinterpret()) {
	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-new"></a>
	 */
	constructor(orientation: Orientation) : this(gtk_paned_new(orientation.gtk)!!.reinterpret())

	/**
	 * Constructor for DSL to have fun
	 */
	constructor(paned: Paned) : this(paned.panedPointer)



	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-get-position">gtk_paned_get_position</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-set-position">gtk_paned_set_position</a>
	 */
	var position: Int
		get() = gtk_paned_get_position(panedPointer)
		set(value) = gtk_paned_set_position(panedPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-get-wide-handle">gtk_paned_get_wide_handle</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#gtk-paned-set-wide-handle">gtk_paned_set_wide_handle</a>
	 */
	var wideHandle: Boolean
		get() = gtk_paned_get_wide_handle(panedPointer).bool
		set(value) = gtk_paned_set_wide_handle(panedPointer, value.gtk)

	private var acceptPositionManager: SignalManager? = null

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#GtkPaned-accept-position"></a>
	 */
	fun setAcceptPositionCallback(action: GenericNoArgForBooleanFunction) {
		acceptPositionManager?.disconnect()
		acceptPositionManager = SignalManager(
			panedPointer,
			panedPointer.connectSignal(
				Signals.CANCEL_POSITION,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticNoArgBooleanCallback
			)
		)
	}

	private var cancelPositionManager: SignalManager? = null

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#GtkPaned-cancel-position"></a>
	 */
	fun setCancelPositionCallback(action: GenericNoArgForBooleanFunction) {
		cancelPositionManager?.disconnect()
		cancelPositionManager = SignalManager(
			panedPointer,
			panedPointer.connectSignal(
				Signals.CANCEL_POSITION,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticNoArgBooleanCallback
			)
		)
	}

	private var cycleChildFocusManager: SignalManager? = null

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#GtkPaned-cycle-child-focus"></a>
	 */
	fun setCycleChildFocusCallback(action: GenericCycleFunction) {
		cycleChildFocusManager?.disconnect()
		cycleChildFocusManager = SignalManager(
			panedPointer,
			panedPointer.connectSignal(
				Signals.CYCLE_CHILD_FOCUS,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticCycleCallback
			)
		)
	}

	private var cycleHandleFocusManager: SignalManager? = null

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#GtkPaned-cycle-handle-focus"></a>
	 */
	fun setCycleHandleFocusCallback(action: GenericCycleFunction) {
		cycleHandleFocusManager?.disconnect()
		cycleHandleFocusManager = SignalManager(
			panedPointer,
			panedPointer.connectSignal(
				Signals.CYCLE_HANDLE_FOCUS,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticCycleCallback
			)
		)
	}

	private var moveHandleManager: SignalManager? = null

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#GtkPaned-move-handle"></a>
	 */
	fun setMoveHandleCallback(action: MoveHandleFunction) {
		moveHandleManager?.disconnect()
		moveHandleManager = SignalManager(
			panedPointer,
			panedPointer.connectSignal(
				Signals.MOVE_HANDLE,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticMoveHandleCallback
			)
		)
	}

	private var toggleHandleFocusManager: SignalManager? = null

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPaned.html#GtkPaned-toggle-handle-focus"></a>
	 */
	fun setToggleHandleFocusCallback(action: GenericNoArgForBooleanFunction) {
		toggleHandleFocusManager?.disconnect()
		toggleHandleFocusManager = SignalManager(
			panedPointer,
			panedPointer.connectSignal(
				Signals.TOGGLE_HANDLE_FOCUS,
				callbackWrapper = StableRef.create(action).asCPointer(),
				handler = staticNoArgBooleanCallback
			)
		)
	}


	companion object {
		private val staticMoveHandleCallback: GCallback =
			staticCFunction { _: gpointer?, scrollType: GtkScrollType, data: gpointer? ->
				data?.asStableRef<MoveHandleFunction>()
					?.get()
					?.invoke(ScrollType.valueOf(scrollType)!!).gtk
			}.reinterpret()

		private val staticNoArgBooleanCallback: GCallback =
			staticCFunction { _: CPointer<GtkPaned>, data: gpointer? ->
				data?.asStableRef<GenericNoArgForBooleanFunction>()?.get()?.invoke().gtk
			}.reinterpret()

		private val staticCycleCallback: GCallback =
			staticCFunction { _: CPointer<GtkPaned>, reversed: gboolean, data: gpointer? ->
				data?.asStableRef<GenericCycleFunction>()?.get()?.invoke(reversed.bool).gtk
			}.reinterpret()
	}
}

typealias GenericNoArgForBooleanFunction = () -> Boolean
typealias GenericCycleFunction = (@ParameterName("reversed") Boolean) -> Boolean
typealias MoveHandleFunction = (ScrollType) -> Boolean