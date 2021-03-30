package nativex.glib

import gtk.GVariant
import gtk.g_variant_new_boolean
import kotlinx.cinterop.CPointer
import nativex.gtk.gtk

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
open class Variant internal constructor(
	internal val variantPointer: CPointer<GVariant>
) {


	class BooleanVariant(value: Boolean) :
		Variant(g_variant_new_boolean(value.gtk)!!)
}