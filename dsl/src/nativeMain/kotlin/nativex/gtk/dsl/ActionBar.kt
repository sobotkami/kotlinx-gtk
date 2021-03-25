package nativex.gtk.dsl

import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.bin.ActionBar


class ActionBarPackStart internal constructor(actionBar: ActionBar) :
	ActionBar(actionBar), SimplePacking {
	override fun pack(child: Widget) =
		packStart(child)
}

class ActionBarPackEnd internal constructor(actionBar: ActionBar) :
	ActionBar(actionBar), SimplePacking {
	override fun pack(child: Widget) =
		packEnd(child)
}


@GtkDsl
fun ActionBar.start(builder: ActionBarPackStart.() -> Unit): ActionBarPackStart =
	ActionBarPackStart(this).apply(builder)

@GtkDsl
fun ActionBar.end(builder: ActionBarPackEnd.() -> Unit): ActionBarPackEnd =
	ActionBarPackEnd(this).apply(builder)
