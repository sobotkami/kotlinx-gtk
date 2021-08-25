package org.gtk

import glib.g_strfreev
import kotlinx.cinterop.toKString
import org.gtk.glib.CStringPointer
import org.gtk.glib.CStringList
import org.gtk.glib.asIterable

/**
 * kotlinx-gtk
 * 14 / 04 / 2021
 *
 * For sequences that must be closed
 */
interface ClosableSequence<T> : Sequence<T>, Closeable

 inline fun CStringList.asCloseableKSequence(): ClosableSequence<String> =
	asCloseableSequence().asKSequence()

 inline fun ClosableSequence<CStringPointer>.asKSequence(): ClosableSequence<String> =
	object : Sequence<String>, ClosableSequence<String> {

		override fun iterator(): Iterator<String> = object : Iterator<String> {
			val i = this@asKSequence.iterator()
			override fun hasNext(): Boolean = i.hasNext()
			override fun next(): String = i.next().toKString()
		}

		override fun close() {
			this@asKSequence.close()
		}
	}

 inline fun CStringList.asCloseableSequence() =
	object : ClosableSequence<CStringPointer> {
		var isClosed = false

		override fun iterator(): Iterator<CStringPointer> {
			if (isClosed) throw ClosedException("Sequence closed")

			return object : Iterator<CStringPointer> {
				val i = this@asCloseableSequence.asIterable()
				override fun hasNext(): Boolean = i.hasNext()
				override fun next(): CStringPointer = i.next()
			}
		}

		override fun close() {
			isClosed = true
			g_strfreev(this@asCloseableSequence)
		}

	}