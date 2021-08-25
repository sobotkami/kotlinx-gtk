package org.gtk.gsk

import gtk.GskRenderer
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gobject.KGObject

/**
 * kotlinx-gtk
 *
 * 25 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gsk4/stable/GskRenderer.html">Renderer</a>
 */
class Renderer(val rendererPointer: CPointer<GskRenderer>) : KGObject(rendererPointer.reinterpret()) {

	companion object {
		inline fun CPointer<GskRenderer>?.wrap() =
			this?.wrap()

		inline fun CPointer<GskRenderer>.wrap() =
			Renderer(this)
	}
}