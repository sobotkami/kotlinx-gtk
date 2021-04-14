package nativex.gio

import gtk.GFile
import kotlinx.cinterop.*

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class File internal constructor(
	internal val pointer: CPointer<GFile>
) {

	companion object {

		internal fun Array<File>.toCArray(): CPointer<CPointerVar<GFile>> =
			memScoped {
				allocArrayOf(this@toCArray.map { it.pointer })
			}
	}
}