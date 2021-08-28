package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.widgets.box.Box
import org.gtk.gtk.widgets.entry.SearchEntry

/**
 * kotlinx-gtk
 * 19 / 03 / 2021
 */
@GtkDsl
inline fun Box.searchEntry(
	searchEntryBuilder: SearchEntry.() -> Unit = {}
) = append(SearchEntry().apply(searchEntryBuilder))


@GtkDsl
inline fun SearchEntry.onPreviousMatch(noinline onPreviousMatch: () -> Unit) {
	addOnPreviousMatchCallback(onPreviousMatch)
}


@GtkDsl
inline fun SearchEntry.onSearchChanged(noinline onSearchChanged: () -> Unit) {
	addOnSearchChangedCallback(onSearchChanged)
}


@GtkDsl
inline fun SearchEntry.onStopSearch(noinline onStopSearch: () -> Unit) {
	addOnStopSearchCallback(onStopSearch)
}