package nativex.gtk

import gtk.*
import kotlinx.cinterop.*
import nativex.*
import nativex.gtk.widgets.Widget

typealias WidgetPointer = CPointer<GtkWidget>

internal typealias VoidPointer = COpaquePointer


@Deprecated("Bulky", ReplaceWith("gtk", "nativex.gtk.gtk"))
internal inline val Boolean.gtkValue: gboolean
	get() = if (this) 1 else 0

internal inline val Boolean.gtk: gboolean
	get() = if (this) 1 else 0

@Deprecated("Bulky", ReplaceWith("gtkValue.bool", "nativex.gtk.bool"))
internal inline infix fun Boolean.Companion.from(gtkValue: gboolean): Boolean =
	gtkValue == 1

internal inline val gboolean.bool
	get() = this == 1


/**Shorthand for C's representation of a string.*/
internal typealias CString = CPointer<ByteVar>

/**Shorthand for C's representation of a list of strings (a pointer to a list of char pointers).*/
internal typealias CStringList = CPointer<CPointerVar<ByteVar>>

fun Array<String>.toNullTermCStringArray(): CPointer<CPointerVar<ByteVar>> =
	memScoped {
		allocArrayOf(this@toNullTermCStringArray.map { it.cstr.getPointer(this) } + null)
	}

fun List<String>.toNullTermCStringArray(): CPointer<CPointerVar<ByteVar>> =
	memScoped {
		allocArrayOf(this@toNullTermCStringArray.map { it.cstr.getPointer(this) } + null)
	}


internal inline fun CStringList?.asStringList(): List<String> =
	this.asSequence()
		.toList()
		.map { it.toKString() }


/**
 * Null termination accepting sequence
 */
internal inline fun <reified T : CPointed> CPointer<CPointerVar<T>>?.asSequence(): Sequence<CPointer<T>> {
	this ?: return emptySequence()

	return object : Sequence<CPointer<T>> {
		private val iterator: Iterator<CPointer<T>> by lazy {
			object : Iterator<CPointer<T>> {
				var index = 0

				override fun hasNext(): Boolean =
					get(index + 1) != null

				override fun next(): CPointer<T> = get(index++)!!
			}
		}

		override fun iterator(): Iterator<CPointer<T>> = iterator
	}
}

internal fun CStringList?.toStringList(length: Int): List<String?> {
	this ?: return emptyList()
	return List(length) { index ->
		get(index)?.toKString()
	}
}

/**
 * Will throw an exception on a nullable
 */
internal fun CStringList?.toStringListNoNulls(length: Int): List<String> {
	this ?: return emptyList()
	return List(length) { index ->
		get(index)!!.toKString()
	}
}

/**
 * Will simply replace null with ["null"]
 */
internal fun CStringList?.toStringListFillNulls(length: Int): List<String> {
	this ?: return emptyList()
	return List(length) { index ->
		get(index)?.toKString() ?: "null"
	}
}

/**
 * Will filter out nulls
 */
internal fun CStringList?.toStringListFilterNulls(length: Int): List<String> {
	this ?: return emptyList()
	return List(length) { index ->
		get(index)?.toKString()
	}.filterNotNull()
}

internal fun CPointer<GList>?.free() {
	g_list_free(this)
}

internal fun CPointer<GSList>?.free() {
	g_slist_free(this)
}

internal fun <T : CPointed> CPointer<GSList>?.asSequence(): Sequence<CPointer<T>> {
	val length = g_slist_length(this).toInt()
	return sequence {
		repeat(length) { index ->
			val element = g_slist_nth_data(this@asSequence, index.convert())
			yield(element!!.reinterpret<T>().also { g_free(it) })
		}
		free()
	}
}

internal fun <T : CPointed> CPointer<GList>?.asSequence(): Sequence<CPointer<T>> {
	val length = g_list_length(this).toInt()
	return sequence {
		repeat(length) { index ->
			val element = g_list_nth_data(this@asSequence, index.convert())
			yield(element!!.reinterpret<T>())
		}
	}
}

internal inline fun WidgetPointer?.asWidgetOrNull() = this?.let { Widget(it) }

internal inline fun WidgetPointer?.asWidget() = Widget(this!!)
