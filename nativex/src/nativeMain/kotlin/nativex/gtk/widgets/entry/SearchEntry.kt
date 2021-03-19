package nativex.gtk.widgets.entry

import gtk.GtkSearchEntry
import gtk.gtk_search_entry_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.gdk.Event
import nativex.gtk.Signals

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class SearchEntry internal constructor(
	internal val searchEntryPointer: CPointer<GtkSearchEntry>
) : Entry(searchEntryPointer.reinterpret()) {
	constructor() : this(gtk_search_entry_new()!!.reinterpret())

	fun handleEvent(event: Event): Boolean {
		TODO("gtk_search_entry_handle_event")
	}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val previousMatchSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.PREVIOUS_MATCH)
	}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val searchChangedSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.SEARCH_CHANGED)
	}

	@ExperimentalUnsignedTypes
	@ExperimentalCoroutinesApi
	val stopSearchSignal: Flow<Unit> by lazy {
		callbackSignalFlow(Signals.STOP_SEARCH)
	}
}