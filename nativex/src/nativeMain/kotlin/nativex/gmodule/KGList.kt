package nativex.gmodule

import glib.*
import kotlinx.cinterop.CPointer
import nativex.glib.VoidPointer
import nativex.gobject.KObject

/**
 * kotlinx-gtk
 *
 * 09 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#GList">GList</a>
 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html">Doubly-Linked Lists</a>
 */
class KGList<T : KObject>(val listPointer: CPointer<GList>) {

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-append">g_list_append</a>
	 */
	fun append(data: T): KGList<T>? =
		g_list_append(listPointer, data.pointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-prepend">g_list_prepend</a>
	 */
	fun prepend(data: T): KGList<T> =
		g_list_prepend(listPointer, data.pointer).wrapNotNull()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-insert">g_list_insert</a>
	 */
	fun insert(position: Int, data: T): KGList<T> =
		g_list_insert(listPointer, data.pointer, position).wrapNotNull()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-insert-before">g_list_insert_before</a>
	 */
	fun insertBefore(sibling: KGList<T>?, data: T): KGList<T> =
		g_list_insert_before(listPointer, sibling?.listPointer, data.pointer).wrapNotNull()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-insert-before-link">g_list_insert_before_link</a>
	 */
	fun insertBeforeLink(sibling: KGList<T>?, link: KGList<T>): KGList<T> =
		g_list_insert_before_link(listPointer, sibling?.listPointer, link.listPointer).wrapNotNull()

	// fun insertSorted(); https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-insert-sorted

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-remove">g_list_remove</a>
	 */
	fun remove(data: T): KGList<T> =
		g_list_remove(listPointer, data.pointer).wrapNotNull()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-remove-link">g_list_remove_link</a>
	 */
	fun removeLink(link: KGList<T>): KGList<T> =
		g_list_remove_link(listPointer, link.listPointer).wrapNotNull()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-delete-link">g_list_delete_link</a>
	 */
	fun deleteLink(link: KGList<T>): KGList<T> =
		g_list_delete_link(listPointer, link.listPointer).wrapNotNull()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-remove-all">g_list_remove_all</a>
	 */
	fun removeAll(data: T): KGList<T> =
		g_list_remove_all(listPointer, data.pointer).wrapNotNull()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-free">g_list_free</a>
	 */
	fun free() {
		g_list_free(listPointer)
	}

	// fun freeFull(); Ignored due to implications
	// fun clearList(); Ignored due to implications

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
	fun copy(): KGList<T> =
		g_list_copy(listPointer).wrapNotNull()

	//	fun copyDeep(func: CopyFunction<T>) {}; Ignored due to implications

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-reverse">g_list_reverse</a>
	 */
	fun reverse(): KGList<T> =
		g_list_reverse(listPointer).wrapNotNull()

	// fun sort();

	// fun insertSortedWithData();

	// fun sortWithData();
	// CompareDataFunction

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-concat">g_list_concat</a>
	 */
	fun concat(list: KGList<T>): KGList<T> =
		g_list_concat(listPointer, list.listPointer).wrapNotNull()

	// fun forEach();
	// GFunc

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-first">g_list_first</a>
	 */
	fun first(): KGList<T>? =
		g_list_first(listPointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-last">g_list_last</a>
	 */
	fun last(): KGList<T>? =
		g_list_last(listPointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-nth">g_list_nth</a>
	 */
	fun get(index: UInt): KGList<T>? =
		g_list_nth(listPointer, index).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-nth-data">g_list_nth_data</a>
	 */
	fun getData(index: UInt): VoidPointer? =
		g_list_nth_data(listPointer, index)

	// fun getPrev();

	// fun find();

	// fun findCustom();

	/**
	 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-index">g_list_index</a>
	 */
	fun getIndex(data: T): Int =
		g_list_index(listPointer, data.pointer)

			;

	companion object {
//		private val staticCopyFunction: GCopyFunc = staticCFunction { src: gconstpointer, data: gpointer-> }

		/**
		 * @see <a href="https://developer.gnome.org/glib/unstable/glib-Doubly-Linked-Lists.html#g-list-alloc"></a>
		 */
		fun <T : KObject> alloc() = g_list_alloc().wrapNotNull<T>()

		inline fun <T : KObject> CPointer<GList>?.wrapNotNull() =
			this!!.wrap<T>()

		inline fun <T : KObject> CPointer<GList>?.wrap() =
			this?.wrap<T>()

		inline fun <T : KObject> CPointer<GList>.wrap() =
			KGList<T>(this)
	}
}