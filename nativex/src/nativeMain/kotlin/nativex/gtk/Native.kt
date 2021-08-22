package nativex.gtk

import gtk.*
import kotlinx.cinterop.*
import nativex.gdk.Surface
import nativex.gdk.Surface.Companion.wrap
import nativex.gsk.Renderer
import nativex.gsk.Renderer.Companion.wrap
import nativex.gtk.ImplNative.Companion.wrap

/**
 * kotlinx-gtk
 *
 * 25 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkNative.html#GtkNative-struct">GtkNative</a>
 */
interface Native {
	val nativePointer: CPointer<GtkNative>

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkNative.html#gtk-native-get-surface">
	 *     gtk_native_get_surface</a>
	 */
	val surface: Surface
		get() = gtk_native_get_surface(nativePointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkNative.html#gtk-native-get-renderer">
	 *     gtk_native_get_renderer</a>
	 */
	val renderer: Renderer
		get() = gtk_native_get_renderer(nativePointer)!!.wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkNative.html#gtk-native-get-surface-transform">
	 *     gtk_native_get_surface_transform</a>
	 */
	val surfaceTransform: Pair<Double, Double>
		get() = memScoped {
			val x = cValue<DoubleVar>()
			val y = cValue<DoubleVar>()

			gtk_native_get_surface_transform(nativePointer, x, y)

			x.ptr.pointed.value to y.ptr.pointed.value
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkNative.html#gtk-native-realize">
	 *     gtk_native_realize</a>
	 */
	fun realize() {
		gtk_native_realize(nativePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkNative.html#gtk-native-unrealize">
	 *     gtk_native_unrealize</a>
	 */
	fun unrealize(){
		gtk_native_unrealize(nativePointer)
	}

	companion object {
		/**
		 * @see <a href="https://developer.gnome.org/gtk4/stable/GtkNative.html#gtk-native-get-for-surface">
		 *     gtk_native_get_for_surface</a>
		 */
		fun getForSurface(native: Surface): Native =
			gtk_native_get_for_surface(native.surfacePointer)!!.wrap()
	}

}

class ImplNative(override val nativePointer: CPointer<GtkNative>) : Native {
	companion object {
		inline fun CPointer<GtkNative>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkNative>.wrap() =
			ImplNative(this)
	}
}