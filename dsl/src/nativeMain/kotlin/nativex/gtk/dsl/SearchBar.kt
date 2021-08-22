package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.SearchBar

/**
 * kotlinx-gtk
 * 19 / 03 / 2021
 */
@GtkDsl
inline fun Widget.searchBar(
	searchEntryBuilder: SearchBar.() -> Unit = {}
) = add(SearchBar().apply(searchEntryBuilder))