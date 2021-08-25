package org.gtk.gobject

import gobject.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.toKString
import org.gtk.gobject.KGObject.Companion.wrap

/**
 * kotlinx-gtk
 * 03 / 06 / 2021
 *
 * @see <a href="https://docs.gtk.org/gobject/class.Binding.html">
 *     GBinding</a>
 */
class Binding(
	val pointer: CPointer<GBinding>
) {

	/**
	 * @see <a href="https://docs.gtk.org/gobject/method.Binding.get_flags.html">
	 *     g_binding_get_flags</a>
	 */
	val flags: Flags
		get() = Flags.valueOf(g_binding_get_flags(pointer))!!

	/**
	 * @see <a href="https://docs.gtk.org/gobject/method.Binding.get_source.html">
	 *     g_binding_get_source</a>
	 */
	val source: KGObject
		get() = g_binding_get_source(pointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gobject/method.Binding.get_source_property.html">
	 *     g_binding_get_source_property</a>
	 */
	val sourceProperty: String
		get() = g_binding_get_source_property(pointer)!!.toKString()

	/**
	 * @see <a href="https://docs.gtk.org/gobject/method.Binding.get_target.html">
	 *     g_binding_get_target</a>
	 */
	val target: KGObject
		get() = g_binding_get_target(pointer)!!.wrap()

	/**
	 * @see <a href="https://docs.gtk.org/gobject/method.Binding.get_target_property.html">
	 *     g_binding_get_target_property</a>
	 */
	val targetProperty: String
		get() = g_binding_get_target_property(pointer)!!.toKString()

	/**
	 * @see <a href="https://docs.gtk.org/gobject/method.Binding.unbind.html">
	 *     g_binding_unbind</a>
	 */
	fun unbind() {
		g_binding_unbind(pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gobject/flags.BindingFlags.html">
	 *     GBindingFlags</a>
	 */
	enum class Flags(val glib: GBindingFlags) {
		DEFAULT(G_BINDING_DEFAULT),
		BIDIRECTIONAL(G_BINDING_BIDIRECTIONAL),
		SYNC_CREATE(G_BINDING_SYNC_CREATE),
		INVERT_BOOLEAN(G_BINDING_INVERT_BOOLEAN);

		companion object {
			fun valueOf(glib: GBindingFlags) = values().find { it.glib == glib }
		}
	}

	companion object {
		fun CPointer<GBinding>?.wrap() =
			this?.wrap()

		fun CPointer<GBinding>.wrap() =
			Binding(this)
	}

}