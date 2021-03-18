package nativex.gtk.common.data

import gtk.*
import kotlinx.cinterop.cValue
import kotlinx.cinterop.memScoped
import kotlinx.cinterop.pointed

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 */
data class Requisition(
	val width: Int,
	val height: Int
) {
	internal val gtk: GtkRequisition
		get() =
			memScoped {
				cValue<GtkRequisition>().ptr.pointed.apply {
					this.height = this@Requisition.height
					this.width = this@Requisition.width
				}
			}

}
