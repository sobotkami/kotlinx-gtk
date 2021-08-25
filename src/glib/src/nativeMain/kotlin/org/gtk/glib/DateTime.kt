package org.gtk.glib

import glib.GDateTime
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 21 / 08 / 2021
 *
 * @see <a href=""></a>
 */
class DateTime(val pointer: CPointer<GDateTime>) {
	companion object{

		inline fun CPointer<GDateTime>?.wrap() =
			this?.wrap()

		inline fun CPointer<GDateTime>.wrap() =
			DateTime(this)

	}
}