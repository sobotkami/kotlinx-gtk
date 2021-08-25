package nativex.gtk.dsl

import nativex.GtkDsl
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.SearchBar
import nativex.gtk.widgets.box.Box

/**
 * kotlinx-gtk
 * 19 / 03 / 2021
 */
@GtkDsl
inline fun Box.searchBar(
	searchEntryBuilder: SearchBar.() -> Unit = {}
) = append(SearchBar().apply(searchEntryBuilder))