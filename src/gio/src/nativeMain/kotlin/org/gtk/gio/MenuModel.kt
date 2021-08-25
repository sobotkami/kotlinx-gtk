package org.gtk.gio

import gio.GMenuModel
import glib.gpointer
import gobject.GCallback
import kotlinx.cinterop.*
import org.gtk.gobject.KGObject
import org.gtk.gobject.SignalManager
import org.gtk.gobject.Signals
import org.gtk.gobject.connectSignal

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
abstract class MenuModel(
	val menuModelPointer: CPointer<GMenuModel>
) : KGObject(menuModelPointer.reinterpret()) {

	data class ItemsChangedEvent(
		val position: Int,
		val removed: Int,
		val added: Int
	)

	fun addOnItemsChangedCallback(action: (ItemsChangedEvent) -> Unit): SignalManager =
		SignalManager(
			menuModelPointer,
			menuModelPointer.connectSignal(
				Signals.ITEMS_CHANGED,
				StableRef.create(action).asCPointer(),
				staticItemsChangedFunction
			)
		)

	/**
	 * Impl classes are used to purely wrap pointers returned for [MenuModel]
	 */
	class Impl(menuModelPointer: CPointer<GMenuModel>) :
		MenuModel(menuModelPointer) {
		companion object {
			inline fun CPointer<GMenuModel>?.wrap() =
				this?.let { Impl(it) }

			inline fun CPointer<GMenuModel>.wrap() =
				Impl(this)
		}
	}

	companion object {
		private val staticItemsChangedFunction: GCallback =
			staticCFunction { _: gpointer?, position: Int, removed: Int, added: Int, data: gpointer? ->
				data?.asStableRef<(ItemsChangedEvent) -> Unit>()?.get()
					?.invoke(ItemsChangedEvent(position, removed, added))
				Unit
			}.reinterpret()
	}
}