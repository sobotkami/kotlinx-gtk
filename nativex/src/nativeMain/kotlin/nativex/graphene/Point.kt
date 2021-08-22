package nativex.graphene

import gtk.graphene_point_t
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 06 / 08 / 2021
 *
 * @see <a href=""></a>
 */
class Point(val pointPointer:CPointer<graphene_point_t>) {
	companion object {
		inline fun CPointer<graphene_point_t>?.wrap() =
			this?.wrap()

		inline fun CPointer<graphene_point_t>.wrap() =
			Point(this)
	}
}