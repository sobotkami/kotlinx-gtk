package nativex.glib

import gtk.GVariantType
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.pointed

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
class VariantType internal constructor(
	internal val variantTypePointer: CPointer<GVariantType>
) {
	init {

	}

}