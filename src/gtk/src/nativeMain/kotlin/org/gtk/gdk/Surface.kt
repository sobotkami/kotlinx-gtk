package org.gtk.gdk

import cairo.cairo_content_t
import gtk.GdkSurface
import gtk.gdk_surface_create_similar_surface
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.cairo.Surface.Companion.wrap
import org.gtk.gobject.KGObject

/**
 * kotlinx-gtk
 *
 * 25 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gdk4/stable/GdkSurface.html">GdkSurface</a>
 */
class Surface(val surfacePointer: CPointer<GdkSurface>) : KGObject(surfacePointer.reinterpret()) {


	fun createSimilarSurface(content: cairo_content_t, width: Int, height: Int): org.gtk.cairo.Surface =
		gdk_surface_create_similar_surface(surfacePointer, content, width, height)!!.wrap()

	companion object {
		inline fun CPointer<GdkSurface>?.wrap() =
			this?.wrap()

		inline fun CPointer<GdkSurface>.wrap() =
			Surface(this)
	}
}