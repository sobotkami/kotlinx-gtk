package nativex.gio

import gtk.GAction
import kotlinx.cinterop.CPointer
import nativex.PointerHolder

internal class ImplAction internal constructor(
	internal val pointer: CPointer<GAction>
) : Action {
	override val actionPointer: PointerHolder<GAction>
		get() = PointerHolder(pointer)

}