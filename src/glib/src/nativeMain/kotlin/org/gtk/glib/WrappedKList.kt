package org.gtk.glib

import glib.GList
import kotlinx.cinterop.CPointer
import org.gtk.glib.KList.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 10 / 07 / 2021
 *
 * Directly wraps a [kList], providing direct access to functions in [kList] while adhering to the type of [T]
 */
class WrappedKList<T>(
	val kList: KList,
	private val wrapPointer: VoidPointer.() -> T,
	private val getPointer: T.() -> VoidPointer
) {
	/**
	 * @see [KList.append]
	 */
	fun append(data: T): WrappedKList<T> =
		kList.append(data.getPointer()).wrap()

	/**
	 * @see [KList.prepend]
	 */
	fun prepend(data: T): WrappedKList<T> =
		kList.prepend(data.getPointer()).wrap()

	/**
	 * @see [KList.insert]
	 */
	fun insert(position: Int, data: T): WrappedKList<T> =
		kList.insert(position, data.getPointer()).wrap()

	/**
	 * @see [KList.insertBefore]
	 */
	fun insertBefore(sibling: WrappedKList<T>, data: T): WrappedKList<T> =
		kList.insertBefore(sibling.kList, data.getPointer()).wrap()

	/**
	 * @see [KList.insertBeforeLink]
	 */
	fun insertBeforeLink(sibling: WrappedKList<T>, link: WrappedKList<T>) =
		kList.insertBeforeLink(sibling.kList, link.kList).wrap()

	/**
	 * @see [KList.remove]
	 */
	fun remove(data: T) =
		kList.remove(data.getPointer()).wrap()

	/**
	 * @see [KList.removeLink]
	 */
	fun removeLink(link: WrappedKList<T>) =
		kList.removeLink(link.kList).wrap()

	/**
	 * @see [KList.deleteLink]
	 */
	fun deleteLink(link: WrappedKList<T>) =
		kList.deleteLink(link.kList).wrap()

	/**
	 * @see [KList.removeAll]
	 */
	fun removeAll(data: T) =
		kList.removeAll(data.getPointer()).wrap()

	/**
	 * @see [KList.free]
	 */
	fun free() {
		kList.free()
	}

	/**
	 * @see [KList.free1]
	 */
	fun free1() {
		kList.free1()
	}

	/**
	 * @see [KList.length]
	 */
	val length: UInt
		get() = kList.length

	/**
	 * @see [KList.copy]
	 */
	fun copy() =
		kList.copy().wrap()

	/**
	 * @see [KList.copyDeep]
	 */
	fun copyDeep(func: (T) -> T) =
		kList.copyDeep {
			func(it.wrapPointer()).getPointer()
		}.wrap()

	/**
	 * @see [KList.reverse]
	 */
	fun reverse() =
		kList.reverse().wrap()

	/**
	 * @see [KList.insertSortedWithData]
	 */
	fun insertSorted(data: T, compare: (T?, T?) -> Int) =
		kList.insertSortedWithData(data.getPointer()) { a, b ->
			compare(a?.let(wrapPointer), b?.let(wrapPointer))
		}.wrap()

	/**
	 * @see [KList.sortWithData]
	 */
	fun sort(compare: (T?, T?) -> Int) =
		kList.sortWithData { a, b ->
			compare(a?.let(wrapPointer), b?.let(wrapPointer))
		}

	/**
	 * @see [KList.concat]
	 */
	fun concat(list: WrappedKList<T>) =
		kList.concat(list.kList).wrap()

	/**
	 * @see [KList.forEach]
	 */
	fun forEach(action: (T) -> Unit) {
		kList.forEach {
			action(it.wrapPointer())
		}
	}

	/**
	 * @see [KList.first]
	 */
	fun first() =
		kList.first().wrap()

	/**
	 * @see [KList.last]
	 */
	fun last() =
		kList.last().wrap()

	/**
	 * @see [KList.nth]
	 */
	fun get(index: UInt) =
		kList.nth(index).wrap()

	/**
	 * @see [KList.nthData]
	 */
	fun getData(index: UInt) =
		kList.nthData(index)?.let(wrapPointer)

	/**
	 * @see [KList.nthPrev]
	 */
	fun getPrev(index: UInt) =
		kList.nthPrev(index).wrap()

	/**
	 * @see [KList.find]
	 */
	fun find(data: T) =
		kList.find(data.getPointer()).wrap()

	/**
	 * @see [KList.position]
	 */
	fun getPosition(link: WrappedKList<T>) =
		kList.position(link.kList)

	/**
	 * @see [KList.index]
	 */
	fun getIndex(data: T) =
		kList.index(data.getPointer())

	/**
	 * Simple pass through wrap
	 */
	private fun KList.wrap(): WrappedKList<T> =
		wrap(wrapPointer, getPointer)

	/**
	 * Simple pass through wrap
	 */
	private fun KList?.wrap(): WrappedKList<T>? =
		this?.wrap()

	companion object {
		inline fun <T> KList.wrap(
			noinline wrapPointer: VoidPointer.() -> T,
			noinline getPointer: T.() -> VoidPointer
		): WrappedKList<T> = WrappedKList(this, wrapPointer, getPointer)

		inline fun <T> CPointer<GList>.asWrappedKList(
			noinline wrapPointer: VoidPointer.() -> T,
			noinline getPointer: T.() -> VoidPointer
		): WrappedKList<T> = WrappedKList(this.wrap(), wrapPointer, getPointer)
	}
}