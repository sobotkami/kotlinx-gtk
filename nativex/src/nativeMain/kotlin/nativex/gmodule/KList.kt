package nativex.gmodule

import glib.*
import kotlinx.cinterop.*
import nativex.glib.VoidPointer

/**
 * kotlinx-gtk
 *
 * 09 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#GList">GList</a>
 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html">Doubly-Linked Lists</a>
 */
class KList(val listPointer: CPointer<GList>) {

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-append">g_list_append</a>
	 */
	fun append(data: VoidPointer): KList? =
		g_list_append(listPointer, data).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-prepend">g_list_prepend</a>
	 */
	fun prepend(data: VoidPointer): KList =
		g_list_prepend(listPointer, data)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-insert">g_list_insert</a>
	 */
	fun insert(position: Int, data: VoidPointer): KList =
		g_list_insert(listPointer, data, position)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-insert-before">g_list_insert_before</a>
	 */
	fun insertBefore(sibling: KList?, data: VoidPointer): KList =
		g_list_insert_before(listPointer, sibling?.listPointer, data)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-insert-before-link">g_list_insert_before_link</a>
	 */
	fun insertBeforeLink(sibling: KList?, link: KList): KList =
		g_list_insert_before_link(listPointer, sibling?.listPointer, link.listPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-insert-sorted">
	 *     g_list_insert_sorted</a>
	 */
	fun insertSorted(data: VoidPointer, function: GCompareFunc) {
		g_list_insert_sorted(listPointer, data, function)
	}

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-remove">g_list_remove</a>
	 */
	fun remove(data: VoidPointer): KList =
		g_list_remove(listPointer, data)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-remove-link">g_list_remove_link</a>
	 */
	fun removeLink(link: KList): KList =
		g_list_remove_link(listPointer, link.listPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-delete-link">g_list_delete_link</a>
	 */
	fun deleteLink(link: KList): KList =
		g_list_delete_link(listPointer, link.listPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-remove-all">g_list_remove_all</a>
	 */
	fun removeAll(data: VoidPointer): KList =
		g_list_remove_all(listPointer, data)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-free">g_list_free</a>
	 */
	fun free() {
		g_list_free(listPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-free-full"></a>
	 */
	fun freeFull(freeFunc: GDestroyNotify) {
		g_list_free_full(listPointer, freeFunc)
	}

	// fun clearList(); Ignored due to implications of handling pointers of pointers

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-free-1">g_list_free_1</a>
	 */
	fun free1() {
		g_list_free_1(listPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-length">g_list_length</a>
	 */
	val length: UInt
		get() = g_list_length(listPointer)

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-copy">g_list_copy</a>
	 */
	fun copy(): KList =
		g_list_copy(listPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-copy-deep">
	 *     g_list_copy_deep</a>
	 */
	fun copyDeep(func: CopyFunction) =
		g_list_copy_deep(listPointer, staticCopyFunction, StableRef.create(func).asCPointer())!!.wrap()


	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-reverse">g_list_reverse</a>
	 */
	fun reverse(): KList =
		g_list_reverse(listPointer)!!.wrap()

	/**
	 * @see <a href=""></a>
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-sort">
	 *     g_list_sort</a>
	 */
	fun sort(compareFunc: GCompareFunc): KList =
		g_list_sort(listPointer, compareFunc)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-insert-sorted-with-data">
	 *     g_list_insert_sorted_with_data</a>
	 */
	fun insertSortedWithData(data: VoidPointer, func: CompareDataFunction): KList =
		g_list_insert_sorted_with_data(
			listPointer,
			data,
			staticCompareDataFunction,
			StableRef.create(func).asCPointer()
		)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-sort-with-data">
	 *     g_list_sort_with_data</a>
	 */
	fun sortWithData(compareDataFunction: CompareDataFunction): KList =
		g_list_sort_with_data(
			listPointer,
			staticCompareDataFunction,
			StableRef.create(compareDataFunction).asCPointer()
		)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-concat">g_list_concat</a>
	 */
	fun concat(list: KList): KList =
		g_list_concat(listPointer, list.listPointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-foreach"></a>
	 */
	fun forEach(action: KListForEachFunction) {
		g_list_foreach(listPointer, staticForeachFunction, StableRef.create(action).asCPointer())
	}

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-first">g_list_first</a>
	 */
	fun first(): KList? =
		g_list_first(listPointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-last">g_list_last</a>
	 */
	fun last(): KList? =
		g_list_last(listPointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-nth">g_list_nth</a>
	 */
	fun get(index: UInt): KList? =
		g_list_nth(listPointer, index).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-nth-data">
	 *     g_list_nth_data</a>
	 */
	fun getData(index: UInt): VoidPointer? =
		g_list_nth_data(listPointer, index)

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-nth-prev"></a>
	 */
	fun getPrev(index: UInt): KList? =
		g_list_nth_prev(listPointer, index).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-find">
	 *     g_list_find</a>
	 */
	fun find(data: VoidPointer): KList? =
		g_list_find(listPointer, data).wrap()

	/**
	 * @see <a href=""></a>
	 */
	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-find-custom">
	 *     g_list_find_custom</a>
	 */
	fun findCustom(data: VoidPointer, func: GCompareFunc): KList? =
		g_list_find_custom(listPointer, data, func).wrap()


	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-foreach">
	 *     g_list_position</a>
	 */
	fun getPosition(link: KList): Int =
		g_list_position(listPointer, link.listPointer)

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-position">
	 *     g_list_index</a>
	 */
	fun getIndex(data: VoidPointer): Int =
		g_list_index(listPointer, data)

			;

	companion object {
		private val staticCopyFunction: GCopyFunc = staticCFunction { src: gconstpointer, data: gpointer ->
			data.asStableRef<CopyFunction>().get().invoke(src)
		}.reinterpret()

		private val staticCompareDataFunction: GCompareDataFunc =
			staticCFunction { p1: gpointer, p2: gpointer, data: gpointer ->
				data.asStableRef<CompareDataFunction>().get().invoke(p1, p2)
			}.reinterpret()

		private val staticForeachFunction: GFunc = staticCFunction { entity: gpointer, data: gpointer ->
			data.asStableRef<KListForEachFunction>().get().invoke(entity)
			Unit
		}.reinterpret()

		/**
		 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-alloc"></a>
		 */
		fun alloc() = g_list_alloc()!!.wrap()

		inline fun CPointer<GList>?.wrap() =
			this?.wrap()

		inline fun CPointer<GList>.wrap() =
			KList(this)

		/**
		 * Use the [KList], Then free it while returning the result
		 */
		inline fun <R> KList.use(use: (KList) -> R): R =
			use(this).also { free() }
	}
}

/**
 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#GFunc">GFunc</a>
 */
typealias KListForEachFunction = (VoidPointer) -> Unit

/**
 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#GCompareDataFunc">
 *     GCompareDataFunc</a>
 */
typealias CompareDataFunction = (a: VoidPointer?, b: VoidPointer?) -> Int