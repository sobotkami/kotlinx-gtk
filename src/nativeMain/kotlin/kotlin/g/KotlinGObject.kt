package kotlin.g

import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
open class KotlinGObject internal constructor(
	internal val pointer: CPointer<gtk.GObject>
) {
}