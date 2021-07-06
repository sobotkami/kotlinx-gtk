package nativex.gtk.dsl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import nativex.GtkDsl
import nativex.async.launchUnconfined
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.entry.SearchEntry

/**
 * kotlinx-gtk
 * 19 / 03 / 2021
 */
@GtkDsl
inline fun Container.searchEntry(
	searchEntryBuilder: SearchEntry.() -> Unit = {}
) = add(SearchEntry().apply(searchEntryBuilder))


@GtkDsl
inline fun SearchEntry.onPreviousMatch(noinline onPreviousMatch:  () -> Unit) {
	addOnPreviousMatchCallback(onPreviousMatch)
}


@GtkDsl
inline fun SearchEntry.onSearchChanged(noinline onSearchChanged: () -> Unit) {
	addOnSearchChangedCallback(onSearchChanged)
}


@GtkDsl
inline fun SearchEntry.onStopSearch(noinline onStopSearch:  () -> Unit) {
	addOnStopSearchCallback(onStopSearch)
}