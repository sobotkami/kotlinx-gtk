package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.widgets.SearchBar
import org.gtk.gtk.widgets.box.Box

/**
 * kotlinx-gtk
 * 19 / 03 / 2021
 */
@GtkDsl
inline fun Box.searchBar(
	searchEntryBuilder: SearchBar.() -> Unit = {}
) = append(SearchBar().apply(searchEntryBuilder))