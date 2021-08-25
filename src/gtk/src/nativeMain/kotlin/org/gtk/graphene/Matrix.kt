package org.gtk.graphene

import gtk.graphene_matrix_t
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 06 / 08 / 2021
 *
 * @see <a href=""></a>
 */
class Matrix(val matrixPointer: CPointer<graphene_matrix_t>) {

	companion object {
		inline fun CPointer<graphene_matrix_t>?.wrap() =
			this?.wrap()

		inline fun CPointer<graphene_matrix_t>.wrap() =
			Matrix(this)
	}
}