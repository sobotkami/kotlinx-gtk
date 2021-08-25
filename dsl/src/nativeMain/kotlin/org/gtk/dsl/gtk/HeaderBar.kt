package org.gtk.dsl.gtk

import org.gtk.dsl.GtkDsl
import org.gtk.gtk.widgets.HeaderBar
import org.gtk.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 24 / 03 / 2021
 */
class HeaderBarPackStart internal constructor(headerBar: HeaderBar) :
	HeaderBar(headerBar), SimplePacking {
	override fun pack(child: Widget) =
		packStart(child)
}

class HeaderBarPackEnd internal constructor(headerBar: HeaderBar) :
	HeaderBar(headerBar), SimplePacking {
	override fun pack(child: Widget) =
		packEnd(child)
}

@GtkDsl
fun HeaderBar.start(builder: HeaderBarPackStart.() -> Unit): HeaderBarPackStart =
	HeaderBarPackStart(this).apply(builder)

@GtkDsl
fun HeaderBar.end(builder: HeaderBarPackEnd.() -> Unit): HeaderBarPackEnd =
	HeaderBarPackEnd(this).apply(builder)
