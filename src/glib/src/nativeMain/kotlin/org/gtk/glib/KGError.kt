package org.gtk.glib

import glib.GError
import glib.g_error_free
import kotlinx.cinterop.*

/**
 * kotlinx-gtk
 * 13 / 04 / 2021
 */
class KGError(
	val pointer: CPointer<GError>
) : Exception(
	pointer.pointed.message?.toKString()
) {
	val code: Int by lazy {
		pointer.pointed.code
	}


	val domain: UInt by lazy {
		pointer.pointed.domain
	}

	fun free() {
		g_error_free(pointer)
	}
}

/**
 * kotlinx-gtk
 * 08 / 04 / 2021
 */
fun CPointer<CPointerVar<GError>>.unwrap(throwException: Boolean = true): Exception? {
	val err = pointed.pointed ?: return null
	val exception = when (err.domain) {
		else -> KGError(err.ptr)
	}

	if (throwException)
		throw exception

	return exception
}
