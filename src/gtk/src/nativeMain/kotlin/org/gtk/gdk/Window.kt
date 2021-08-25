package org.gtk.gdk

import gtk.*
import gtk.GdkFullscreenMode.GDK_FULLSCREEN_ON_ALL_MONITORS
import gtk.GdkFullscreenMode.GDK_FULLSCREEN_ON_CURRENT_MONITOR
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gobject.KGObject

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class Window(
	val windowPointer: CPointer<GdkSurface>
) : KGObject(windowPointer.reinterpret()) {

	enum class Gravity(val key: Int, val gdk: GdkGravity) {
		NORTH_WEST(0, GDK_GRAVITY_NORTH_WEST),
		NORTH(1, GDK_GRAVITY_NORTH),
		NORTH_EAST(2, GDK_GRAVITY_NORTH_EAST),
		WEST(3, GDK_GRAVITY_WEST),
		CENTER(4, GDK_GRAVITY_CENTER),
		EAST(5, GDK_GRAVITY_EAST),
		SOUTH_WEST(6, GDK_GRAVITY_SOUTH_WEST),
		SOUTH(7, GDK_GRAVITY_SOUTH),
		SOUTH_EAST(8, GDK_GRAVITY_SOUTH_EAST),
		STATIC(9, GDK_GRAVITY_STATIC);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			fun valueOf(gdk: GdkGravity) = values().find { it.gdk == gdk }
		}
	}

	enum class AnchorHints(val key: Int, val gdk: GdkAnchorHints) {
		FLIP_X(0, GDK_ANCHOR_FLIP_X),
		FLIP_Y(1, GDK_ANCHOR_FLIP_Y),
		SLIDE_X(2, GDK_ANCHOR_SLIDE_X),
		SLIDE_Y(3, GDK_ANCHOR_SLIDE_Y),
		RESIZE_X(4, GDK_ANCHOR_RESIZE_X),
		RESIZE_Y(5, GDK_ANCHOR_RESIZE_Y),
		FLIP(6, GDK_ANCHOR_FLIP),
		SLIDE(7, GDK_ANCHOR_SLIDE),
		RESIZE(8, GDK_ANCHOR_RESIZE);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			fun valueOf(gdk: GdkAnchorHints) = values().find { it.gdk == gdk }
		}
	}

	enum class FullscreenMode(val key: Int, val gdk: GdkFullscreenMode) {
		ON_CURRENT_MONITOR(0, GDK_FULLSCREEN_ON_CURRENT_MONITOR),
		ON_ALL_MONITORS(1, GDK_FULLSCREEN_ON_ALL_MONITORS);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }
			fun valueOf(gdk: GdkFullscreenMode) = values().find { it.gdk == gdk }
		}
	}

	companion object {
		inline fun CPointer<GdkSurface>?.wrap() =
			this?.let { Window(it) }

		inline fun CPointer<GdkSurface>.wrap() =
			Window(this)
	}
}