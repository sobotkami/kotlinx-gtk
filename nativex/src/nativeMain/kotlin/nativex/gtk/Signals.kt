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
	const val CLICKED = "clicked"
	const val ACTIVATE = "activate"
	const val ACTIVATE_DEFAULT = "activate-default"
	const val ACTIVATE_FOCUS = "activate-focus"
	const val KEYS_CHANGED = "keys-changed"
	const val SET_FOCUS = "set-focus"

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
}

@ExperimentalUnsignedTypes
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
