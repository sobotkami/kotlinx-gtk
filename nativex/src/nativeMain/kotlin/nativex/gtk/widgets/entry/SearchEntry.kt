package nativex.gtk.widgets.entry

import gtk.GtkSearchEntry
import gtk.gtk_search_entry_handle_event
import gtk.gtk_search_entry_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.reinterpret
import nativex.gdk.Event
import nativex.glib.bool
import nativex.gobject.Signals
import nativex.gobject.connectSignal
import nativex.gobject.SignalManager

/**
 * kotlinx-gtk
 *
 * 16 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSearchEntry.html">GtkSearchEntry</a>
 */
class SearchEntry(val searchEntryPointer: CPointer<GtkSearchEntry>) : Entry(searchEntryPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSearchEntry.html#gtk-search-entry-new">
	 *     gtk_search_entry_new</a>
	 */
	constructor() : this(gtk_search_entry_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSearchEntry.html#GtkSearchEntry-next-match">
	 *     next-match</a>
	 */
	fun addOnNextMatchCallback(action: () -> Unit): SignalManager =
		SignalManager(
			searchEntryPointer,
			searchEntryPointer.connectSignal(
				Signals.NEXT_MATCH,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSearchEntry.html#GtkSearchEntry-previous-match">
	 *     previous-match</a>
	 */
	fun addOnPreviousMatchCallback(action: () -> Unit): SignalManager =
		SignalManager(
			searchEntryPointer,
			searchEntryPointer.connectSignal(
				Signals.PREVIOUS_MATCH,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSearchEntry.html#GtkSearchEntry-search-changed">
	 *     search-changed</a>
	 */
	fun addOnSearchChangedCallback(action: () -> Unit): SignalManager =
		SignalManager(
			searchEntryPointer,
			searchEntryPointer.connectSignal(
				Signals.SEARCH_CHANGED,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSearchEntry.html#GtkSearchEntry-stop-search">
	 *     stop-search</a>
	 */
	fun addOnStopSearchCallback(action: () -> Unit): SignalManager =
		SignalManager(
			searchEntryPointer,
			searchEntryPointer.connectSignal(
				Signals.STOP_SEARCH,
				callbackWrapper = StableRef.create(action).asCPointer()
			)
		)
}