package nativex.gtk

import gtk.*
import kotlinx.cinterop.*
import nativex.gtk.widgets.Widget

typealias WidgetPointer = CPointer<GtkWidget>

 typealias VoidPointer = COpaquePointer

 inline val Boolean?.gtk: gboolean
	get() = this?.gtk ?: 0

 inline val Boolean.gtk: gboolean
	get() = if (this) 1 else 0

 inline val gboolean.bool
	get() = this == 1


/**Shorthand for C's representation of a string.*/
 typealias CString = CPointer<ByteVar>

/**Shorthand for C's representation of a list of strings (a pointer to a list of char pointers).*/
 typealias CStringList = CPointer<CPointerVar<ByteVar>>


fun Array<Int>.toCArray(scope: MemScope): CPointer<CPointerVar<IntVar>> =
	with(scope) {
		allocArrayOf(this@toCArray.map { value ->
			cValue<IntVar>().apply {
				this.ptr.pointed.value = value
			}.getPointer(this)
		})
	}

fun Array<String>.toNullTermCStringArray(): CStringList =
	memScoped {
		allocArrayOf(this@toNullTermCStringArray.map { it.cstr.getPointer(this) } + null)
	}

fun Array<out String>.toNullTermCStringArray(): CStringList =
	memScoped {
		allocArrayOf(this@toNullTermCStringArray.map { it.cstr.getPointer(this) } + null)
	}

fun List<String>.toNullTermCStringArray(): CStringList =
	memScoped {
		allocArrayOf(this@toNullTermCStringArray.map { it.cstr.getPointer(this) } + null)
	}


/**
 * For null terminated C arrays
 */
 inline fun <reified T : CPointed> CPointer<CPointerVar<T>>.asIterable(): Iterator<CPointer<T>> =
	object : Iterator<CPointer<T>> {
		var index = 0

		override fun hasNext(): Boolean =
			get(index + 1) != null

		override fun next(): CPointer<T> = get(index++)!!
	}

/**
 * For null terminated C arrays
 * @see asIterable
 */
 inline fun <reified T : CPointed> CPointer<CPointerVar<T>>?.asSequence(): Sequence<CPointer<T>> {
	this ?: return emptySequence()
	return object : Sequence<CPointer<T>> {
		override fun iterator(): Iterator<CPointer<T>> =
			this@asSequence.asIterable()
	}
}

/**
 * Null termination accepting sequence
 */
 inline fun CStringList?.asKSequence(): Sequence<String> {
	this ?: return emptySequence()

	return object : Sequence<String> {
		override fun iterator(): Iterator<String> = object : Iterator<String> {
			private val iterator: Iterator<CString> by lazy {
				this@asKSequence.asIterable()
			}

			override fun hasNext(): Boolean = iterator.hasNext()
			override fun next(): String = iterator.next().toKString()
		}
	}
}

@Deprecated("Replace with sequences")
 fun CStringList?.toStringList(length: Int): List<String?> {
	this ?: return emptyList()
	return List(length) { index ->
		get(index)?.toKString()
	}
}

/**
 * Will throw an exception on a nullable
 */
@Deprecated("Replace with sequences")
 fun CStringList?.toStringListNoNulls(length: Int): List<String> {
	this ?: return emptyList()
	return List(length) { index ->
		get(index)!!.toKString()
	}
}

/**
 * Will simply replace null with ["null"]
 */
@Deprecated("Replace with sequences")
 fun CStringList?.toStringListFillNulls(length: Int): List<String> {
	this ?: return emptyList()
	return List(length) { index ->
		get(index)?.toKString() ?: "null"
	}
}

/**
 * Will filter out nulls
 */
 fun CStringList?.toStringListFilterNulls(length: Int): List<String> {
	this ?: return emptyList()
	return List(length) { index ->
		get(index)?.toKString()
	}.filterNotNull()
}

 fun CPointer<GList>?.free() {
	g_list_free(this)
}

 fun CPointer<GSList>?.free() {
	g_slist_free(this)
}

 inline fun <I : CPointed, O> CPointer<GSList>?.asKSequence(
	crossinline wrap: (CPointer<I>) -> O
): Sequence<O> =
	object : Sequence<O> {
		private val gListIterator: Iterator<CPointer<I>> =
			this@asKSequence.asSequence<I>().iterator()

		private val iterator: Iterator<O> by lazy {
			object : Iterator<O> {
				override fun hasNext(): Boolean =
					gListIterator.hasNext().also {
						if (!it)
							this@asKSequence.free()
					}

				override fun next(): O =
					wrap(gListIterator.next())

			}
		}

		override fun iterator(): Iterator<O> =
			iterator
	}

 inline fun <I : CPointed, O> CPointer<GList>?.asKSequence(
	crossinline wrap: (CPointer<I>) -> O
): Sequence<O> =
	object : Sequence<O> {
		private val gListIterator: Iterator<CPointer<I>> =
			this@asKSequence.asSequence<I>().iterator()

		private val iterator: Iterator<O> by lazy {
			object : Iterator<O> {
				override fun hasNext(): Boolean =
					gListIterator.hasNext().also {
						if (!it)
							this@asKSequence.free()
					}

				override fun next(): O =
					wrap(gListIterator.next())

			}
		}

		override fun iterator(): Iterator<O> =
			iterator
	}

 fun CPointer<gintVar>?.asSequence(length: Int): Sequence<Int> {
	this ?: return emptySequence()
	return object : Sequence<Int> {
		override fun iterator(): Iterator<Int> = object : Iterator<Int> {
			private var index = 0;

			override fun hasNext(): Boolean = index < length

			override fun next(): Int = this@asSequence[index]
		}
	}
}

 fun <T : CPointed> CPointer<GSList>?.asSequence(): Sequence<CPointer<T>> {
	val length = g_slist_length(this).toInt()
	return sequence {
		repeat(length) { index ->
			val element = g_slist_nth_data(this@asSequence, index.convert())
			yield(element!!.reinterpret<T>().also { g_free(it) })
		}
		free()
	}
}

 fun <T : CPointed> CPointer<GList>?.asSequence(): Sequence<CPointer<T>> {
	val length = g_list_length(this).toInt()
	return sequence {
		repeat(length) { index ->
			val element = g_list_nth_data(this@asSequence, index.convert())
			yield(element!!.reinterpret<T>())
		}
	}
}

 inline fun WidgetPointer?.asWidgetOrNull() = this?.let { Widget(it) }

 inline fun WidgetPointer?.asWidget() = Widget(this!!)


