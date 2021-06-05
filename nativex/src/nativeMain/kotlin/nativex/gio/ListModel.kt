package nativex.gio

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.staticCFunction
import kotlinx.coroutines.flow.Flow
import nativex.gio.KObject.Companion.wrap
import nativex.glib.KGType

/**
 * kotlinx-gtk
 * 04 / 06 / 2021
 *
 * @see <a href="https://developer.gnome.org/gio/stable/GListModel.html">GListModel</a>
 */
open class ListModel internal constructor(
	internal val listModelPointer: CPointer<GListModel>
) {

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListModel.html#g-list-model-get-item-type">g_list_model_get_item_type</a>
	 */
	val type: KGType
		get() = KGType.valueOf(g_list_model_get_item_type(listModelPointer))

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListModel.html#g-list-model-get-n-items">g_list_model_get_n_items</a>
	 */
	val itemCount: UInt
		get() = g_list_model_get_n_items(listModelPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListModel.html#GListModel-items-changed">items-changed</a>
	 */
	val itemsChangedSignal: Flow<ItemsChanged>
		//by nativex.async.signalFlow(Signals.ITEMS_CHANGED, staticItemsChangedCallback)
		get() = TODO("items-changed")

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListModel.html#g-list-model-get-object">g_list_model_get_object</a>
	 */
	operator fun get(position: UInt): KObject? =
		g_list_model_get_object(listModelPointer, position).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListModel.html#g-list-model-items-changed">g_list_model_items_changed</a>
	 */
	fun itemsChanged(position: UInt, removed: UInt, added: UInt) {
		g_list_model_items_changed(listModelPointer, position, removed, added)
	}

	data class ItemsChanged(
		val position: UInt,
		val removed: UInt,
		val added: UInt
	)

	companion object {
		internal val staticItemsChangedCallback =
			staticCFunction { _: GListModel?, position: UInt, removed: UInt, added: UInt, data: gpointer? ->
				data?.asStableRef<(ItemsChanged) -> Unit>()?.get()?.invoke(ItemsChanged(position, removed, added))
				Unit
			}
	}
}