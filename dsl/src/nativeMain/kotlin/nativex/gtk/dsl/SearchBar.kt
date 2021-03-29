package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.bin.SearchBar

/**
 * kotlinx-gtk
 * 19 / 03 / 2021
 */
@GtkDsl
inline fun Container.searchBar(
	searchEntryBuilder: SearchBar.() -> Unit = {}
) = add(SearchBar().apply(searchEntryBuilder))