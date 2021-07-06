package nativex.gio

import gio.GFile
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.CPointerVar
import kotlinx.cinterop.allocArrayOf
import kotlinx.cinterop.memScoped

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