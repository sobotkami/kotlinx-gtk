package nativex.gtk.common.events

import gtk.GCallback
import gtk.GtkMovementStep
import gtk.gboolean
import gtk.gpointer
import kotlinx.cinterop.asStableRef
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.staticCFunction
import nativex.gtk.bool
import nativex.gtk.common.enums.MovementStep

data class ExtenedMoveCursorEvent(
	val step: MovementStep,
	val count: Int,
	val extendSelection: Boolean
) {
	companion object {
		val staticMoveCursorCallback: GCallback =
			staticCFunction { _: gpointer?,
			                  step: GtkMovementStep,
			                  count: Int,
			                  extendSelection: gboolean,
			                  data: gpointer? ->
				data?.asStableRef<(ExtenedMoveCursorEvent) -> Unit>()?.get()
					?.invoke(
						ExtenedMoveCursorEvent(
							MovementStep.valueOf(step)!!,
							count,
							extendSelection.bool
						)
					)
				Unit
			}.reinterpret()
	}
}

typealias ExtendedMoveCursorFunction = (
	MovementStep,
	@ParameterName("count") Int,
	@ParameterName("extendSelection") Boolean
) -> Unit

val staticExtendedMoveCursorFunction: GCallback =
	staticCFunction { _: gpointer?,
	                  step: GtkMovementStep,
	                  count: Int,
	                  extendSelection: gboolean,
	                  data: gpointer? ->
		data?.asStableRef<ExtendedMoveCursorFunction>()?.get()
			?.invoke(
				MovementStep.valueOf(step)!!,
				count,
				extendSelection.bool
			)
		Unit
	}.reinterpret()