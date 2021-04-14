package nativex.gtk.common.ext

import gtk.GDK_PIXBUF_ERROR
import gtk.GError
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CPointerVar
import kotlinx.cinterop.pointed
import kotlinx.cinterop.toKString
import nativex.glib.KGError

/**
 * kotlinx-gtk
 * 08 / 04 / 2021
 */
internal fun CPointer<CPointerVar<GError>>.unwrap(throwException: Boolean = true): Exception? {
	val err = pointed.pointed ?: return null
	val exception = when (err.domain) {
		GDK_PIXBUF_ERROR -> {
			//PixbufException.fromCode(err.code, err.message?.toKString())
			TODO("PixbufException")
		}
		else -> KGError(this)
	}

	if (throwException)
		throw exception

	return exception
}
