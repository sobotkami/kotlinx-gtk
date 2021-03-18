package nativex.gtk

import gtk.GtkTreeIter
import gtk.GtkTreeModel
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.pointed

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
open class TreeModel internal constructor(
	internal val pointer: CPointer<GtkTreeModel>
) {

	data class Iter internal constructor(
		internal val iter: CPointer<GtkTreeIter>
	) {
		val stamp: Int
			get() = iter.pointed.stamp
	}

}