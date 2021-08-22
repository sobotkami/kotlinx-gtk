package nativex.glib

import glib.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef

/**
 * kotlinx-gtk
 *
 * 21 / 08 / 2021
 *
 * @see <a href="https://docs.gtk.org/glib/struct.SList.html">GSList</a>
 */
class SList(val listPointer: CPointer<GSList>) {

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.alloc.html"></a>
	 */
	constructor() : this(g_slist_alloc()!!)

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.append.html"></a>
	 */
	fun append(data: VoidPointer): SList =
		g_slist_append(listPointer, data)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.concat.html"></a>
	 */
	fun concat(list: SList): SList =
		g_slist_concat(listPointer, list.listPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.copy.html"></a>
	 */
	fun copy(): SList =
		g_slist_copy(listPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.copy_deep.html"></a>
	 */
	fun copyDeep(func: CopyFunction) =
		g_slist_copy_deep(listPointer, staticCopyFunction, StableRef.create(func).asCPointer())

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.delete_link.html"></a>
	 */
	fun deleteLink(link: SList): SList =
		g_slist_delete_link(listPointer, link.listPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.find.html"></a>
	 */
	fun find(data: VoidPointer): SList? =
		g_slist_find(listPointer, data).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.find_custom.html"></a>
	 */
	fun findCustom(data: VoidPointer, func: GCompareFunc): SList? =
		g_slist_find_custom(listPointer, data, func).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.foreach.html"></a>
	 */
	fun forEach(action: KFunc) {
		g_slist_foreach(listPointer, staticForeachFunction, StableRef.create(action).asCPointer())
	}

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.free.html"></a>
	 */
	fun free() {
		g_slist_free(listPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.free_1.html"></a>
	 */
	fun free1() {
		g_slist_free_1(listPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.free_full.html"></a>
	 */
	@Suppress("unused")
	fun freeFull(freeFunc: DestroyNotify) {
		g_slist_free_full(listPointer, freeFunc)
	}


	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.index.html"></a>
	 */
	fun index(data: VoidPointer): Int =
		g_slist_index(listPointer, data)

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.insert.html"></a>
	 */
	fun insert(position: Int, data: VoidPointer): SList =
		g_slist_insert(listPointer, data, position)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.insert_before.html"></a>
	 */
	fun insertBefore(sibling: SList, data: VoidPointer): SList =
		g_slist_insert_before(listPointer, sibling.listPointer, data)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.insert_sorted.html"></a>
	 */
	@Suppress("unused")
	fun insertSorted(data: VoidPointer, function: GCompareFunc) {
		g_slist_insert_sorted(listPointer, data, function)
	}

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.insert_sorted_with_data.html"></a>
	 */
	fun insertSortedWithData(data: VoidPointer, func: CompareDataFunction): SList =
		g_slist_insert_sorted_with_data(
			listPointer,
			data,
			staticCompareDataFunction,
			StableRef.create(func).asCPointer()
		)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.last.html"></a>
	 */
	fun last(): SList? =
		g_slist_last(listPointer).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.length.html"></a>
	 */
	val length: UInt
		get() = g_slist_length(listPointer)

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.nth.html"></a>
	 */
	fun nth(index: UInt): SList? =
		g_slist_nth(listPointer, index).wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.nth_data.html"></a>
	 */
	fun nthData(index: UInt): VoidPointer? =
		g_slist_nth_data(listPointer, index)

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.position.html"></a>
	 */
	fun position(link: SList): Int =
		g_slist_position(listPointer, link.listPointer)

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.prepend.html"></a>
	 */
	fun prepend(data: VoidPointer): SList {
		g_slist_prepend(listPointer, data)!!.wrap()
		return this
	}

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.remove.html"></a>
	 */
	fun remove(data: VoidPointer): SList =
		g_slist_remove(listPointer, data)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.remove_all.html"></a>
	 */
	fun removeAll(data: VoidPointer): SList =
		g_slist_remove_all(listPointer, data)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.remove_link.html"></a>
	 */
	fun removeLink(link: SList): SList =
		g_slist_remove_link(listPointer, link.listPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.reverse.html"></a>
	 */
	fun reverse(): SList =
		g_slist_reverse(listPointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.sort.html"></a>
	 */
	@Suppress("unused")
	fun sort(compareFunc: GCompareFunc): SList =
		g_slist_sort(listPointer, compareFunc)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/glib/type_func.SList.sort_with_data.html"></a>
	 */
	fun sortWithData(compareDataFunction: CompareDataFunction): SList =
		g_slist_sort_with_data(
			listPointer,
			staticCompareDataFunction,
			StableRef.create(compareDataFunction).asCPointer()
		)!!.wrap()

	companion object {
		/**
		 * @see <a href="https://docs.gtk.org/glib/type_func.SList.alloc.html">g_slist_alloc</a>
		 */
		@Suppress("unused")
		fun alloc() = g_slist_alloc()!!.wrap()

		inline fun CPointer<GSList>?.wrap() =
			this?.wrap()

		inline fun CPointer<GSList>.wrap() =
			SList(this)
	}
}