package nativex.glib

import glib.GError
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CPointerVar
import kotlinx.cinterop.pointed
import kotlinx.cinterop.toKString

/**
 * kotlinx-gtk
 * 13 / 04 / 2021
 */
class KGError(
	 val pointer: CPointer<CPointerVar<GError>>
) : Exception(
	pointer.pointed.pointed?.message?.toKString()
) {
	val code: Int by lazy {
		pointer.pointed.pointed?.code ?: Int.MIN_VALUE
	}

	
	val domain: UInt by lazy {
		pointer.pointed.pointed?.domain ?: UInt.MIN_VALUE
	}
}

/**
 * kotlinx-gtk
 * 08 / 04 / 2021
 */
fun CPointer<CPointerVar<GError>>.unwrap(throwException: Boolean = true): Exception? {
	val err = pointed.pointed ?: return null
	val exception = when (err.domain) {
		else -> KGError(this)
	}

	if (throwException)
		throw exception

	return exception
}
