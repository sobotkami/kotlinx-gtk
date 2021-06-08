package nativex.gtk

import gtk.GCallback
import gtk.g_signal_connect_data
import gtk.gpointer
import kotlinx.cinterop.COpaquePointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
object Signals {

	const val ACTIVATE_CURRENT_LINK = "activate-current-link"
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

	const val CHANGED = "changed"
	const val VALUE_CHANGED = "value-changed"
	const val TOGGLED = "toggled"

	const val PREVIOUS_MATCH = "previous-match"
	const val SEARCH_CHANGED = "search-changed"
	const val STOP_SEARCH = "stop-search"

	// GtkTextView
	const val BACKSPACE = "backspace"
	const val COPY_CLIPBOARD = "copy-clipboard"
	const val CUT_CLIPBOARD = "cut-clipboard"
	const val DELETE_FROM_CURSOR = "delete-from-cursor"
	const val EXTEND_SELECTION = "extend-selection"
	const val INSERT_AT_CURSOR = "insert-at-cursor"
	const val INSERT_EMOJI = "insert-emoji"
	const val MOVE_CURSOR = "move-cursor"
	const val MOVE_VIEWPORT = "move-viewport"
	const val PASTE_CLIPBOARD = "paste-clipboard"
	const val POPULATE_POPUP = "populate-popup"
	const val PREEDIT_CHANGED = "preedit-changed"
	const val SELECT_ALL = "select-all"
	const val SET_ANCHOR = "set-anchor"
	const val TOGGLE_CURSOR_VISIBLE = "toggle-cursor-visible"
	const val TOGGLE_OVERWRITE = "toggle-overwrite"

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
	const val MONITOR_ADDED = "monitor-added"
	const val MONITOR_REMOVED = "monitor-removed"
	const val SEAT_ADDED = "seat-added"
	const val SEAT_REMOVED = "seat-removed"

	// GtkFlowBox
	const val UNSELECT_ALL = "unselect-all"
	const val SELECTED_CHILDREN_CHANGED = "selected-children-changed"
	const val TOGGLE_CURSOR_CHILD = "toggle-cursor-child"
	const val CHILD_ACTIVATED: String = "child-activated"
	const val ACTIVATE_CURSOR_CHILD: String = "activate-cursor-child"

	// GtkNotebook
	const val FOCUS_TAB = "focus-tab"
	const val REORDER_TAB = "reorder-tab"
	const val SELECT_PAGE = "select-page"

}

/**
 * @param signal Signal name
 * @param handler Static C Function that will take event directly from the GTK library, should invoke [callbackWrapper]
 * @param callbackWrapper Passed as the data parameter to `g_signal_connect_data`. Invoked by [handler]
 * @param flags Flags
 */
internal fun VoidPointer.connectSignal(
	signal: String,
	handler: GCallback = staticNoArgGCallback,
	callbackWrapper: COpaquePointer? = null,
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
 * [GCallback] that calls a function with only no arguments
 */
internal val staticNoArgGCallback: GCallback =
	staticCFunction { _: gpointer?, data: gpointer? ->
		data?.asStableRef<() -> Unit>()?.get()?.invoke()
		Unit
	}.reinterpret()
