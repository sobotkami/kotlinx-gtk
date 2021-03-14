package kotlin.gtk

import gtk.GtkTreeModel
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
abstract class TreeModel internal constructor(
	internal val pointer: CPointer<GtkTreeModel>
) {

}