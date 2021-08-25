package org.gtk.graphene

import gtk.graphene_rect_t
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 06 / 08 / 2021
 *
 * @see <a href=""></a>
 */
class Rect(val rectPointer: CPointer<graphene_rect_t>) {

	companion object {
		inline fun CPointer<graphene_rect_t>?.wrap() =
			this?.wrap()

		inline fun CPointer<graphene_rect_t>.wrap() =
			Rect(this)
	}
}