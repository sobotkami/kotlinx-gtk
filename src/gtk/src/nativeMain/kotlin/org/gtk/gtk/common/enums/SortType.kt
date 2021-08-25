package org.gtk.gtk.common.enums

import gtk.GtkSortType
import gtk.GtkSortType.GTK_SORT_ASCENDING
import gtk.GtkSortType.GTK_SORT_DESCENDING

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
enum class SortType(val key: Int,  val gtk: GtkSortType) {
	ASCENDING(0, GTK_SORT_ASCENDING),
	DESCENDING(1, GTK_SORT_DESCENDING);

	companion object {
		fun valueOf(key: Int) =
			values().find { it.key == key }

		 fun valueOf(gtk: GtkSortType) =
			values().find { it.gtk == gtk }
	}
}