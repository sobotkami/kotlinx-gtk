package nativex.glib

import gtk.GError
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CPointerVar
import kotlinx.cinterop.pointed
import kotlinx.cinterop.toKString

/**
 * kotlinx-gtk
 * 13 / 04 / 2021
 */
class KGError internal constructor(
	internal val pointer: CPointer<CPointerVar<GError>>
) : Exception(
	pointer.pointed.pointed?.message?.toKString()
) {
	val code: Int by lazy {
		pointer.pointed.pointed?.code ?: Int.MIN_VALUE
	}

	@ExperimentalUnsignedTypes
	val domain: UInt by lazy {
		pointer.pointed.pointed?.domain ?: UInt.MIN_VALUE
	}
}