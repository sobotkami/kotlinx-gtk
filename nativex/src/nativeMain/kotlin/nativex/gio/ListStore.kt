package nativex.gio

import gtk.*
import kotlinx.cinterop.*
import nativex.glib.KGType
import nativex.glib.KGValue
import nativex.gtk.bool

/**
 * kotlinx-gtk
 * 05 / 06 / 2021
 *
 * @see <a href="https://developer.gnome.org/gio/stable/GListStore.html">GListStore</a>
 */
class ListStore(
	 val listStorePointer: CPointer<GListStore>
) : ListModel(listStorePointer.reinterpret()) {
	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListStore.html#g-list-store-new">g_list_store_new</a>
	 */
	constructor(type: KGType) : this(g_list_store_new(type.glib)!!)

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListStore.html#g-list-store-insert">g_list_store_insert</a>
	 */
	fun insert(position: UInt, item: KGValue) {
		g_list_store_insert(listStorePointer, position, item.pointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListStore.html#g-list-store-insert-sorted">g_list_store_insert_sorted</a>
	 */
	fun insertSorted(item: KGValue, sort: CompareKGValueFunction) {
		val ref = StableRef.create(sort)
		g_list_store_insert_sorted(
			listStorePointer,
			item.pointer,
			staticGValueCompareFunction,
			ref.asCPointer()
		)
		ref.dispose()
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListStore.html#g-list-store-append">g_list_store_append</a>
	 */
	fun append(item: KGValue) {
		g_list_store_append(listStorePointer, item.pointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListStore.html#g-list-store-remove">g_list_store_remove</a>
	 */
	fun remove(position: UInt) {
		g_list_store_remove(listStorePointer, position)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListStore.html#g-list-store-remove-all">g_list_store_remove_all</a>
	 */
	fun removeAll() {
		g_list_store_remove_all(listStorePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListStore.html#g-list-store-splice">g_list_store_splice</a>
	 */
	fun splice(position: UInt, numberRemovals: UInt, vararg additions: KObject) {
		memScoped {
			val values = allocArray<CPointerVar<GObject>>(additions.size)
			additions.forEachIndexed { index, kObject ->
				values[index] = kObject.pointer
			}

			g_list_store_splice(
				listStorePointer,
				position,
				numberRemovals,
				values.reinterpret(),
				additions.size.toUInt()
			)

		}
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListStore.html#g-list-store-sort">g_list_store_sort</a>
	 */
	fun sort(sort: CompareKGValueFunction) {
		val ref = StableRef.create(sort)
		g_list_store_sort(
			listStorePointer,
			staticGValueCompareFunction,
			ref.asCPointer()
		)
		ref.dispose()
	}

	/**
	 * @see <a href="https://developer.gnome.org/gio/stable/GListStore.html#g-list-store-find">g_list_store_find</a>
	 */
	fun find(item: KGValue): UInt {
		memScoped {
			val position = cValue<UIntVar>()
			return if (g_list_store_find(listStorePointer, item.pointer, position).bool) {
				position.ptr.pointed.value
			} else UInt.MIN_VALUE
		}
	}

	companion object {
		 val staticGValueCompareFunction: GCompareDataFunc =
			staticCFunction { a: CPointer<GValue>, b: CPointer<GValue>, c: gpointer? ->
				c?.asStableRef<CompareKGValueFunction>()?.get()?.invoke(KGValue(a), KGValue(b)) ?: 0
			}.reinterpret()
	}
}

typealias CompareKGValueFunction = (KGValue, KGValue) -> Int