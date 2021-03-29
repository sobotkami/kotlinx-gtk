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

@ExperimentalUnsignedTypes
@ExperimentalCoroutinesApi
@GtkDsl
inline fun SearchEntry.onPreviousMatch(crossinline onPreviousMatch: suspend () -> Unit) {
	launchUnconfined {
		previousMatchSignal.collectLatest {
			onPreviousMatch()
		}
	}
}

@ExperimentalUnsignedTypes
@ExperimentalCoroutinesApi
@GtkDsl
inline fun SearchEntry.onSearchChanged(crossinline onSearchChanged: suspend () -> Unit) {
	launchUnconfined {
		searchChangedSignal.collectLatest {
			onSearchChanged()
		}
	}
}

@ExperimentalUnsignedTypes
@ExperimentalCoroutinesApi
@GtkDsl
inline fun SearchEntry.onStopSearch(crossinline onStopSearch: suspend () -> Unit) {
	launchUnconfined {
		stopSearchSignal.collectLatest {
			onStopSearch()
		}
	}
}