package nativex.gtk.dsl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import nativex.async.launchUnconfined
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.bin.SearchBar
import nativex.gtk.widgets.entry.SearchEntry

/**
 * kotlinx-gtk
 * 19 / 03 / 2021
 */
@GtkDsl
inline fun Container.searchBar(
	searchEntryBuilder: SearchBar.() -> Unit = {}
) = add(SearchBar().apply(searchEntryBuilder))