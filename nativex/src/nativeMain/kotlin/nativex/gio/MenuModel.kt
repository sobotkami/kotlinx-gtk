package nativex.gio

import gtk.GMenuModel
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
abstract class MenuModel internal constructor(
	internal val cPointer: CPointer<GMenuModel>
) {
	/**
	 * Impl classes are used to purely wrap pointers returned for [MenuModel]
	 */
	internal class Impl(menuModelPointer: CPointer<GMenuModel>) :
		MenuModel(menuModelPointer)
}