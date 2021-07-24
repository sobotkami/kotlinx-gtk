package nativex.gmodule

import glib.GList
import kotlinx.cinterop.CPointer
import nativex.glib.VoidPointer
import nativex.gmodule.KList.Companion.use
import nativex.gmodule.KList.Companion.wrap
import nativex.gmodule.KWrappedList.Companion.wrap
import nativex.gmodule.WrappedList.Companion.asMutableList

/**
 * kotlinx-gtk
 *
 * 10 / 07 / 2021
 *
 * Provides a kotlin implementation around a [KWrappedList]. Allowing it to be turned into a full kotlin list
 * @see KWrappedList
 */
class WrappedList<T>(val kWrappedList: KWrappedList<T>) : AbstractMutableList<T>() {
	override val size: Int
		get() = kWrappedList.length.toInt()

	override fun get(index: Int): T =
		kWrappedList.getData(index.toUInt()) ?: throw IndexOutOfBoundsException()

	override fun add(index: Int, element: T) {
		kWrappedList.insert(index, element)
	}

	override fun removeAt(index: Int): T {
		val data = kWrappedList.getData(index.toUInt()) ?: throw IndexOutOfBoundsException()
		kWrappedList.remove(data)
		return data
	}

	override fun set(index: Int, element: T): T {
		val oldData = get(index)
		removeAt(index)
		add(index, element)
		return oldData
	}

	companion object {
		inline fun <T> KWrappedList<T>.asMutableList(): WrappedList<T> = WrappedList(this)

		inline fun <T> CPointer<GList>.asMutableList(
			noinline wrapPointer: VoidPointer.() -> T,
			noinline getPointer: T.() -> VoidPointer
		): WrappedList<T> = wrap().wrap(wrapPointer, getPointer).asMutableList()

		inline fun <T> CPointer<GList>.toList(
			noinline wrapPointer: VoidPointer.() -> T,
			noinline getPointer: T.() -> VoidPointer
		): List<T> = wrap().use { kList ->
			kList.wrap(wrapPointer, getPointer).asMutableList().toList()
		}
	}
}