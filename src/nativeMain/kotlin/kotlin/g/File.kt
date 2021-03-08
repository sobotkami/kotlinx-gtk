package kotlin.g

import gtk.GFile
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class File internal constructor(
	internal val pointer: CPointer<GFile>
) {
}