package nativex.gobject

import glib.GDestroyNotify
import glib.gpointer
import gobject.GCallback
import gobject.g_signal_connect_data
import gobject.g_signal_handler_disconnect
import kotlinx.cinterop.*
import nativex.glib.VoidPointer

/**
 * @param signal Signal name
 * @param handler Static C Function that will take event directly from the GTK library, should invoke [callbackWrapper]
 * @param callbackWrapper Passed as the data parameter to `g_signal_connect_data`. Invoked by [handler]
 * @param flags Flags
 */
fun VoidPointer.connectSignal(
	signal: String,
	callbackWrapper: COpaquePointer? = null,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): ULong =
	g_signal_connect_data(
		this,
		detailed_signal = signal,
		c_handler = handler,
		data = callbackWrapper,
		// Destroys the callbackWrapper
		destroy_data = staticCFunction { void: gpointer?, _ ->
			void?.asStableRef<Any>()?.dispose()
		},
		connect_flags = flags
	)

/**
 * Convenience function merging [connectSignal] with [SignalManager]
 */
inline fun <T : CPointed> signalManager(
	pointer: CPointer<T>,
	signal: String,
	callbackWrapper: COpaquePointer? = null,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, callbackWrapper, handler, flags)
	)

/**
 * Convenience function merging [connectSignal] with [SignalManager]
 */
inline fun KGObject.addSignalCallback(
	signal: String,
	callbackWrapper: COpaquePointer? = null,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, callbackWrapper, handler, flags)
	)

inline fun <O> KGObject.addSignalCallback(
	signal: String,
	noinline action: () -> O,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, StableRef.create(action).asCPointer(), handler, flags)
	)

inline fun <I, O> KGObject.addSignalCallback(
	signal: String,
	noinline action: (I) -> O,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, StableRef.create(action).asCPointer(), handler, flags)
	)

inline fun <I0, I1, O> KGObject.addSignalCallback(
	signal: String,
	noinline action: (I0, I1) -> O,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, StableRef.create(action).asCPointer(), handler, flags)
	)


inline fun <I0, I1, I2, O> KGObject.addSignalCallback(
	signal: String,
	noinline action: (I0, I1, I2) -> O,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, StableRef.create(action).asCPointer(), handler, flags)
	)

inline fun <I0, I1, I2, I3, O> KGObject.addSignalCallback(
	signal: String,
	noinline action: (I0, I1, I2, I3) -> O,
	handler: GCallback = staticNoArgGCallback,
	flags: UInt = 0u
): SignalManager =
	SignalManager(
		pointer,
		pointer.connectSignal(signal, StableRef.create(action).asCPointer(), handler, flags)
	)


/**
 * Manages a signal connection
 *
 * @param pointer pointer the signal is attached to
 * @param T type of function this manager is responsible
 * @param signalId id of the signal
 */
class SignalManager(val pointer: VoidPointer, val signalId: ULong) {
	fun disconnect() {
		g_signal_handler_disconnect(pointer, signalId)
	}
}


/**
 * [GCallback] that calls a function with only no arguments
 */
val staticNoArgGCallback: GCallback =
	staticCFunction { _: gpointer?, data: gpointer? ->
		data?.asStableRef<() -> Unit>()?.get()?.invoke()
		Unit
	}.reinterpret()

/**
 * Most of the library uses a stable reference as the user data. This is just a generic destroy for it
 */
val staticDestroyStableRefFunction: GDestroyNotify = staticCFunction { pointer ->
	pointer?.asStableRef<Any>()?.dispose()
	Unit
}


/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
object Signals {

	const val ACTIVATE_CURRENT_LINK = "activate-current-link"

	// Common
	const val BACKSPACE = "backspace"
	const val COPY_CLIPBOARD = "copy-clipboard"
	const val CUT_CLIPBOARD = "cut-clipboard"
	const val INSERT_EMOJI = "insert-emoji"
	const val PASTE_CLIPBOARD = "paste-clipboard"
	const val POPULATE_POPUP = "populate-popup"
	const val TOGGLE_OVERWRITE = "toggle-overwrite"
	const val DELETE_FROM_CURSOR = "delete-from-cursor"
	const val VALUE_CHANGED = "value-changed"
	const val CHANGED = "changed"
	const val TOGGLED = "toggled"


	const val CREATE_WINDOW = "create-window"
	const val MOVE_FOCUS_OUT = "move-focus-out"
	const val CHANGE_CURRENT_PAGE = "change-current-page"
	const val SWITCH_PAGE = "switch-page"
	const val CLICKED = "clicked"
	const val ACTIVATE = "activate"

	// Gtk Application
	const val STARTUP = "startup"
	const val SHUTDOWN = "shutdown"
	const val OPEN = "open"
	const val NAME_LOST = "name-lost"
	const val HANDLE_LOCAL_OPTIONS = "handle-location-options"
	const val COMMAND_LINE = "command-line"


	// GtkSearchEntry
	const val NEXT_MATCH = "next-match"
	const val PREVIOUS_MATCH = "previous-match"
	const val SEARCH_CHANGED = "search-changed"
	const val STOP_SEARCH = "stop-search"

	// GtkTextView
	const val EXTEND_SELECTION = "extend-selection"
	const val INSERT_AT_CURSOR = "insert-at-cursor"
	const val MOVE_CURSOR = "move-cursor"
	const val MOVE_VIEWPORT = "move-viewport"
	const val PREEDIT_CHANGED = "preedit-changed"
	const val SELECT_ALL = "select-all"
	const val SET_ANCHOR = "set-anchor"
	const val TOGGLE_CURSOR_VISIBLE = "toggle-cursor-visible"

	// GtkTreeModel
	const val ROW_CHANGED = "row-changed"
	const val ROW_DELETED = "row-deleted"
	const val ROW_HAS_CHILD_TOGGLED = "row-has-child-toggled"
	const val ROW_INSERTED = "row-inserted"
	const val ROWS_REORDERED = "rows-reordered"

	// GtkApplication
	const val QUERY_END = "query-end"
	const val WINDOW_ADDED = "window-added"
	const val WINDOW_REMOVED = "window-removed"

	// GtkWindow
	const val ACTIVATE_FOCUS = "activate-focus"
	const val ACTIVATE_DEFAULT = "activate-default"
	const val ENABLE_DEBUGGING = "enable-debugging"
	const val KEYS_CHANGED = "keys-changed"
	const val SET_FOCUS = "set-focus"

	// GtkColorChooser
	const val COLOR_ACTIVATED = "color-activated"

	// GtkPaned
	const val ACCEPT_POSITION = "accept-position"
	const val CANCEL_POSITION = "cancel-position"
	const val CYCLE_CHILD_FOCUS = "cycle-child-focus"
	const val CYCLE_HANDLE_FOCUS = "cycle-handle-focus"
	const val MOVE_HANDLE = "move-handle"
	const val TOGGLE_HANDLE_FOCUS = "toggle-handle-focus"

	// GtkInfoBar
	const val CLOSE = "close"
	const val RESPONSE = "response"

	// GMenuModel
	const val ITEMS_CHANGED = "items-changed"

	// GdkFrameClock
	const val AFTER_PAINT = "after-paint"
	const val BEFORE_PAINT = "before-paint"
	const val FLUSH_EVENTS = "flush-events"
	const val LAYOUT = "layout"
	const val PAINT = "paint"
	const val RESUME_EVENTS = "resume-events"
	const val UPDATE = "update"

	// GdkMonitor
	const val INVALIDATE = "invalidate"

	// GdkDisplay
	const val CLOSED = CLOSE + "d"
	const val OPENED = OPEN + "ed"
	const val SEAT_ADDED = "seat-added"
	const val SEAT_REMOVED = "seat-removed"
	const val SETTING_CHANGED = "setting-changed"

	// GtkFlowBox
	const val UNSELECT_ALL = "unselect-all"
	const val SELECTED_CHILDREN_CHANGED = "selected-children-changed"
	const val TOGGLE_CURSOR_CHILD = "toggle-cursor-child"
	const val CHILD_ACTIVATED = "child-activated"
	const val ACTIVATE_CURSOR_CHILD = "activate-cursor-child"

	// GtkNotebook
	const val FOCUS_TAB = "focus-tab"
	const val REORDER_TAB = "reorder-tab"
	const val SELECT_PAGE = "select-page"
	const val PAGE_ADDED = "page-added"
	const val PAGE_REMOVED = "page-removed"
	const val PAGE_REORDERED = "page-reordered"

	// GtkListBox
	const val ACTIVATE_CURSOR_ROW = "activate-cursor-row"
	const val SELECTED_ROWS_CHANGED = "selected-rows-changed"
	const val TOGGLE_CURSOR_ROW = "toggle-cursor-row"
	const val ROW_ACTIVATED = "row-activated"
	const val ROW_SELECTED = "row-selected"

	// GtkWidget
	const val REALIZE = "realize"
	const val UNREALIZE = "unrealize"
	const val UNMAP = "unmap"
	const val STYLE_UPDATED = "style-updated"
	const val GRAB_FOCUS = "grab-focus"
	const val DESTROY = "destroy"
	const val ACCEL_CLOSURES_CHANGED = "accel-closures-changed"
	const val SHOW = "show"
	const val WINDOW_STATE_EVENT = "window-state-event"
	const val UNMAP_EVENT = "unmap-event"
	const val TOUCH_EVENT = "touch-event"
	const val SELECTION_REQUEST = "selection-request"
	const val SELECTION_RECEIVED = "selection-received"
	const val SELECTION_NOTIFY_EVENT = "selection-notify-event"
	const val SELECTION_CLEAR_EVENT = "selection-clear-event"
	const val SCROLL_EVENT = "scroll-event"
	const val PROXIMITY_IN_EVENT = "proximity-in-event"
	const val PROXIMITY_OUT_EVENT = "proximity-OUT-event"
	const val PROPERTY_NOTIFY_EVENT = "property-notify-event"
	const val MOTION_NOTIFY_EVENT = "motion-notify-event"
	const val LEAVE_NOTIFY_EVENT = "leave-notify-event"
	const val KEY_RELEASE_EVENT = "key-release-event"
	const val KEY_PRESS_EVENT = "key-press-event"
	const val GRAB_BROKEN_EVENT = "grab-broken-event"
	const val FOCUS_OUT_EVENT = "focus-out-event"
	const val FOCUS_IN_EVENT = "focus-in-event"
	const val EVENT = "event"
	const val ENTER_NOTIFY_EVENT = "enter-notify-event"
	const val DESTROY_EVENT = "destroy-event"
	const val DELETE_EVENT = "delete-event"
	const val CONFIGURE_EVENT = "configure-event"
	const val BUTTON_RELEASE_EVENT = "button-release-event"
	const val BUTTON_PRESS_EVENT = "button-press-event"
	const val DAMAGE_EVENT = "damage-event"
	const val CAN_ACTIVATE_ACCEL = "can-activate-accel"
	const val DIRECTION_CHANGED = "direction-changed"
	const val DRAG_BEING_MANAGER = "drag-being-manager"
	const val DRAG_DATA_DELETE = "drag-data-delete"
	const val DRAG_DATA_RECEIVED = "drag-data-received"
	const val DRAG_DROP = "drag-drop"
	const val DRAG_END = "drag-end"
	const val EVENT_AFTER = "event-after"
	const val GRAB_NOTIFY = "grab-notify"
	const val HIDE = "hide"
	const val HIERARCHY_CHANGED = "hierarchy-changed"
	const val MOVE_FOCUS = "move-focus"
	const val PARENT_SET = "parent-set"
	const val SCREEN_CHANGED = "screen-changed"
	const val SELECTION_GET = "selection-get"
	const val SIZE_ALLOCATE = "size-allocate"
	const val STATE_FLAGS_CHANGED = "state-flags-changed"
	const val DRAG_DATA_GET = "drag-data-get"
	const val MAP_EVENT = "map-event"
	const val DRAG_MOTION = "drag-motion"
	const val DRAG_FAILED = "drag-failed"
	const val DRAG_LEAVE = "drag-leave"
	const val FOCUS = "focus"
	const val KEYNAV_FAILED = "keynav-failed"
	const val MAP = "map"
	const val MNEMONIC_ACTIVATE = "mnemonic-activate"
	const val POPUP_MENU = "popup-menu"
	const val QUERY_TOOLTIP = "query-tooltip"
	const val SHOW_HELP = "show-help"

	// GtkAboutDialog
	const val ACTIVATE_LINK = "activate-link"

	// GtkPrinter
	//const val DETAILS_ACQUIRED = "details-acquired"

	// GtkScrolledWindow
	const val SCROLL_CHILD = "scroll-child"
	const val EDGE_OVERSHOT = "edge-overshot"
	const val EDGE_REACHED = "edge-reached"

	// GtkToolbar
	const val ORIENTATION_CHANGED = "orientation-changed"
	const val STYLE_CHANGED = "style-changed"
	const val FOCUS_HOME_OR_END = "focus-home-or-end"
	const val POPUP_CONTEXT_MENU = "popup-context-menu"

	// GtkEntryBuffer
	const val DELETED_TEXT = "deleted-text"
	const val INSERTED_TEXT = "inserted-text"

	// GtkEntry
	const val ICON_PRESS = "icon-press"
	const val ICON_RELEASE = "icon-release"

	// GtkSpinButton
	const val OUTPUT = "output"
	const val WRAPPED = "wrapped"
	const val CHANGE_VALUE = "change-value"
	const val INPUT = "input"

	// GtkRadioButton
	const val GROUP_CHANGED = "group-changed"

	// GtkComboBox
	const val POPDOWN = "popdown"
	const val POPUP = "popup"
	const val MOVE_ACTIVE = "move-active"
	const val FORMAT_ENTRY_TEXT = "format-entry-text"

	// GtkFileChooserButton
	const val FILE_SET = "file-set"

	// GtkFontButton
	const val FONT_SET = "font-set"

	// GtkAppChooserButton
	const val CUSTOM_ITEM_ACTIVATED = "custom-item-activated"

	// GtkScale
	const val FORMAT_VALUE = "format-value"

	// GtkLevelBar
	const val OFFSET_CHANGED = "offset-changed"


	// GAppLaunchContext
	const val LAUNCH_FAILED = "launch-failed"
	const val LAUNCHED = "launched"

}



