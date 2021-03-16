package kotlin.gtk.container

import gtk.GtkStack
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class Stack internal constructor(
	internal val stackPointer: CPointer<GtkStack>
) : Container(stackPointer.reinterpret()) {

}