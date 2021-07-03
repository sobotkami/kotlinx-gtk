package nativex.gtk.common.events

import gtk.GCallback
import gtk.GtkMovementStep
import gtk.gpointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import nativex.gtk.common.enums.MovementStep

data class MoveCursorEvent(
	val step: MovementStep,
	val count: Int
) {
	companion object {
		 val staticCallback: GCallback =
			staticCFunction { _: gpointer, step: GtkMovementStep, count: Int, data: gpointer? ->
				data?.asStableRef<(MoveCursorEvent) -> Unit>()?.get()?.invoke(
					MoveCursorEvent(
						MovementStep.valueOf(step)!!,
						count
					)
				)
			}.reinterpret()
	}
}