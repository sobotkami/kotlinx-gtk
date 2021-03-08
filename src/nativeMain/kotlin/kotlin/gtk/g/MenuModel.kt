package kotlin.gtk.g

import gtk.GMenuModel
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
class MenuModel
internal constructor(val cPointer: CPointer<GMenuModel>) {
}