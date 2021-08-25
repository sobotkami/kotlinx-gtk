package org.gtk.gsk

import gtk.GskTransform
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 *
 * 24 / 07 / 2021
 *
 * @see <a href=""></a>
 */
class Transform(
	val transformPointer: CPointer<GskTransform>
) {
}