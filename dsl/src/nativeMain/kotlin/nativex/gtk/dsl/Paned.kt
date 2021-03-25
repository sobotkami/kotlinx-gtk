package nativex.gtk.dsl

import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.Paned

/**
 * Interface that abstracts packing to make DSL easier
 */
interface NumeralPane {
	fun add(widget: Widget)
	fun pack(widget: Widget, resize: Boolean, shrink: Boolean)
}

class FirstPane internal constructor(
	paned: Paned
) : Paned(paned), NumeralPane {
	override fun add(widget: Widget) =
		add1(widget)

	override fun pack(widget: Widget, resize: Boolean, shrink: Boolean) =
		pack1(widget, resize, shrink)
}

class SecondPane internal constructor(
	paned: Paned
) : Paned(paned), NumeralPane {
	override fun add(widget: Widget) =
		add2(widget)

	override fun pack(widget: Widget, resize: Boolean, shrink: Boolean) =
		pack2(widget, resize, shrink)
}