package nativex.gmodule

import nativex.glib.VoidPointer

/**
 * kotlinx-gtk
 *
 * 10 / 07 / 2021
 *
 * Directly wraps a [klist], providing direct access to functions while adhering to the type of [T]
 */
class KWrappedList<T>(
	val klist: KList,
	private val wrapPointer: VoidPointer.() -> T,
	private val getPointer: T.() -> VoidPointer
) {
	/**
	 * @see [KList.append]
	 */
	fun append(data: T): KWrappedList<T>? =
		klist.append(data.getPointer()).wrap()

	/**
	 * @see [KList.prepend]
	 */
	fun prepend(data: T): KWrappedList<T> =
		klist.prepend(data.getPointer()).wrap()

	/**
	 * @see [KList.insert]
	 */
	fun insert(position: Int, data: T): KWrappedList<T> =
		klist.insert(position, data.getPointer()).wrap()

	/**
	 * @see [KList.insertBefore]
	 */
	fun insertBefore(sibling: KWrappedList<T>, data: T): KWrappedList<T> =
		klist.insertBefore(sibling.klist, data.getPointer()).wrap()

	/**
	 * @see [KList.insertBeforeLink]
	 */
	fun insertBeforeLink(sibling: KWrappedList<T>, link: KWrappedList<T>) =
		klist.insertBeforeLink(sibling.klist, link.klist).wrap()

	/**
	 * @see [KList.remove]
	 */
	fun remove(data: T) =
		klist.remove(data.getPointer()).wrap()

	/**
	 * @see [KList.removeLink]
	 */
	fun removeLink(link: KWrappedList<T>) =
		klist.removeLink(link.klist).wrap()

	/**
	 * @see [KList.deleteLink]
	 */
	fun deleteLink(link: KWrappedList<T>) =
		klist.deleteLink(link.klist).wrap()

	/**
	 * @see [KList.removeAll]
	 */
	fun removeAll(data: T) =
		klist.removeAll(data.getPointer()).wrap()

	/**
	 * @see [KList.free]
	 */
	fun free() {
		klist.free()
	}

	/**
	 * @see [KList.free1]
	 */
	fun free1() {
		klist.free1()
	}

	/**
	 * @see [KList.length]
	 */
	val length: UInt
		get() = klist.length

	/**
	 * @see [KList.copy]
	 */
	fun copy() =
		klist.copy().wrap()

	/**
	 * @see [KList.copyDeep]
	 */
	fun copyDeep(func: (T) -> T) =
		klist.copyDeep {
			func(it.wrapPointer()).getPointer()
		}.wrap()

	/**
	 * @see [KList.reverse]
	 */
	fun reverse() =
		klist.reverse().wrap()

	/**
	 * @see [KList.insertSortedWithData]
	 */
	fun insertSorted(data: T, compare: (T?, T?) -> Int) =
		klist.insertSortedWithData(data.getPointer()) { a, b ->
			compare(a?.let(wrapPointer), b?.let(wrapPointer))
		}.wrap()

	/**
	 * @see [KList.sortWithData]
	 */
	fun sort(compare: (T?, T?) -> Int) =
		klist.sortWithData { a, b ->
			compare(a?.let(wrapPointer), b?.let(wrapPointer))
		}

	/**
	 * @see [KList.concat]
	 */
	fun concat(list: KWrappedList<T>) =
		klist.concat(list.klist).wrap()

	/**
	 * @see [KList.forEach]
	 */
	fun forEach(action: (T) -> Unit) {
		klist.forEach {
			action(it.wrapPointer())
		}
	}

	/**
	 * @see [KList.first]
	 */
	fun first() =
		klist.first().wrap()

	/**
	 * @see [KList.last]
	 */
	fun last() =
		klist.last().wrap()

	/**
	 * @see [KList.get]
	 */
	fun get(index: UInt) =
		klist.get(index).wrap()

	/**
	 * @see [KList.getData]
	 */
	fun getData(index: UInt) =
		klist.getData(index)?.let(wrapPointer)

	/**
	 * @see [KList.getPrev]
	 */
	fun getPrev(index: UInt) =
		klist.getPrev(index).wrap()

	/**
	 * @see [KList.find]
	 */
	fun find(data: T) =
		klist.find(data.getPointer()).wrap()

	/**
	 * @see [KList.getPosition]
	 */
	fun getPosition(link: KWrappedList<T>) =
		klist.getPosition(link.klist)

	/**
	 * @see [KList.getIndex]
	 */
	fun getIndex(data: T) =
		klist.getIndex(data.getPointer())

	/**
	 * Simple pass through wrap
	 */
	private fun KList.wrap(): KWrappedList<T> =
		wrap(wrapPointer, getPointer)

	/**
	 * Simple pass through wrap
	 */
	private fun KList?.wrap(): KWrappedList<T>? =
		this?.wrap()

	companion object {
		inline fun <T> KList.wrap(
			noinline wrapPointer: VoidPointer.() -> T,
			noinline getPointer: T.() -> VoidPointer
		): KWrappedList<T> = KWrappedList(this, wrapPointer, getPointer)
	}
}