package nativex.glib

import glib.GList
import kotlinx.cinterop.CPointer
import nativex.glib.KList.Companion.wrap
import nativex.glib.WrappedKList.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 10 / 07 / 2021
 *
 * Provides a kotlin implementation around a [WrappedKList]. Allowing it to be turned into a full kotlin list
 * @see WrappedKList
 */
class MutableWrappedKList<T>(val wrappedKList: WrappedKList<T>) : AbstractMutableList<T>() {
	override val size: Int
		get() = wrappedKList.length.toInt()

	override fun get(index: Int): T =
		wrappedKList.getData(index.toUInt()) ?: throw IndexOutOfBoundsException()

	override fun add(index: Int, element: T) {
		wrappedKList.insert(index, element)
	}

	override fun removeAt(index: Int): T {
		val data = wrappedKList.getData(index.toUInt()) ?: throw IndexOutOfBoundsException()
		wrappedKList.remove(data)
		return data
	}

	override fun set(index: Int, element: T): T {
		val oldData = get(index)
		removeAt(index)
		add(index, element)
		return oldData
	}

	companion object {
		inline fun <T> WrappedKList<T>.asMutableList(): MutableWrappedKList<T> = MutableWrappedKList(this)

		inline fun <T> CPointer<GList>.asMutableList(
			noinline wrapPointer: VoidPointer.() -> T,
			noinline getPointer: T.() -> VoidPointer
		): MutableWrappedKList<T> = wrap().wrap(wrapPointer, getPointer).asMutableList()

		inline fun <T> CPointer<GList>.toList(
			noinline wrapPointer: VoidPointer.() -> T,
			noinline getPointer: T.() -> VoidPointer
		): List<T> = wrap().use { kList ->
			kList.wrap(wrapPointer, getPointer).asMutableList().toList()
		}
	}
}