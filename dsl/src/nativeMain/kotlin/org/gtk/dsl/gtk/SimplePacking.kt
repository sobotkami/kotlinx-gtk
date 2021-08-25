package org.gtk.dsl.gtk

import org.gtk.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 24 / 03 / 2021
 * A class for gtk widgets that have a single parameter pack
 *
 * Packs should either be start or end
 */
interface SimplePacking {
	fun pack(child: Widget)
}