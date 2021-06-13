package nativex.gio

import gtk.GCallback
import gtk.GMenuModel
import gtk.gpointer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.async.signalFlow
import nativex.gtk.Signals

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
abstract class MenuModel internal constructor(
	internal val menuModelPointer: CPointer<GMenuModel>
) : KObject(menuModelPointer.reinterpret()) {


	data class ItemsChangedEvent(
		val position: Int,
		val removed: Int,
		val added: Int
	)

	@ExperimentalCoroutinesApi
	
	val itemsChangedSignal: Flow<ItemsChangedEvent> by signalFlow(Signals.ITEMS_CHANGED, staticItemsChangedSignal)


	/**
	 * Impl classes are used to purely wrap pointers returned for [MenuModel]
	 */
	internal class Impl(menuModelPointer: CPointer<GMenuModel>) :
		MenuModel(menuModelPointer) {
		companion object {
			internal inline fun CPointer<GMenuModel>?.wrap() =
				this?.let { Impl(it) }

			internal inline fun CPointer<GMenuModel>.wrap() =
				Impl(this)
		}
	}

	companion object {
		internal val staticItemsChangedSignal: GCallback =
			staticCFunction { _: gpointer?, position: Int, removed: Int, added: Int, data: gpointer? ->
				data?.asStableRef<(ItemsChangedEvent) -> Unit>()?.get()
					?.invoke(ItemsChangedEvent(position, removed, added))
				Unit
			}.reinterpret()
	}
}