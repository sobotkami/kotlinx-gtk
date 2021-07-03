package nativex.gio

import gtk.GFile
import kotlinx.cinterop.*

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class File(
	 val pointer: CPointer<GFile>
) {

	companion object {

		 fun Array<File>.toCArray(): CPointer<CPointerVar<GFile>> =
			memScoped {
				allocArrayOf(this@toCArray.map { it.pointer })
			}
	}
}