package nativex.glib

import gtk.GVariant
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
class Variant internal constructor(
	internal val variantPointer: CPointer<GVariant>
) {

}