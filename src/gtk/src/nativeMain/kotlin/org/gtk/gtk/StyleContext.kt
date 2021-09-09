package org.gtk.gtk

import gtk.*
import kotlinx.cinterop.*
import org.gtk.gdk.Display
import org.gtk.gdk.Display.Companion.wrap
import org.gtk.gdk.RGBA
import org.gtk.gdk.RGBA.Companion.heavyWrap
import org.gtk.gdk.RGBA.Companion.wrap
import org.gtk.glib.bool
import org.gtk.gtk.Border.Companion.wrap
import org.gtk.gtk.common.enums.StateFlags

/**
 * kotlinx-gtk
 *
 * 16 / 03 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.StyleContext.html">GtkStyleContext</a>
 */
class StyleContext(
	val styleContextPointer: CPointer<GtkStyleContext>
) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.add_class.html">gtk_style_context_add_class</a>
	 */
	fun addClass(className: String) {
		gtk_style_context_add_class(styleContextPointer, className)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.add_provider.html">gtk_style_context_add_provider</a>
	 */
	fun addProvider(provider: StyleProvider, priority: UInt) {
		gtk_style_context_add_provider(styleContextPointer, provider.styleProviderPointer, priority)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.get_border.html">gtk_style_context_get_border</a>
	 */
	val border: Border
		get() = ::gtk_style_context_get_border.heavyWrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.get_color.html">gtk_style_context_get_color</a>
	 */
	val color: RGBA
		get() = ::gtk_style_context_get_color.heavyWrap(styleContextPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.get_display.html">gtk_style_context_get_display</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.set_display.html">gtk_style_context_set_display</a>
	 */
	var display: Display
		get() = gtk_style_context_get_display(styleContextPointer)!!.wrap()
		set(value) = gtk_style_context_set_display(styleContextPointer, value.displayPointer)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.get_margin.html">gtk_style_context_get_margin</a>
	 */
	val margin: Border
		get() = ::gtk_style_context_get_margin.heavyWrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.get_padding.html">gtk_style_context_get_padding</a>
	 */
	val padding: Border
		get() = ::gtk_style_context_get_padding.heavyWrap()

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.get_scale.html">gtk_style_context_get_scale</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.set_scale.html">gtk_style_context_set_scale</a>
	 */
	var scale: Int
		get() = gtk_style_context_get_scale(styleContextPointer)
		set(value) = gtk_style_context_set_scale(styleContextPointer, value)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.get_state.html">gtk_style_context_get_state</a>
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.set_state.html">gtk_style_context_set_state</a>
	 */
	var state: StateFlags
		get() = StateFlags.valueOf(gtk_style_context_get_state(styleContextPointer))!!
		set(value) = gtk_style_context_set_state(styleContextPointer, value.gtk)

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.has_class.html">gtk_style_context_has_class</a>
	 */
	fun hasClass(className: String): Boolean =
		gtk_style_context_has_class(styleContextPointer, className).bool

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.lookup_color.html">gtk_style_context_lookup_color</a>
	 */
	fun lookupColor(colorName: String): RGBA? = memScoped {
		val color = cValue<GdkRGBA>()
		if (gtk_style_context_lookup_color(styleContextPointer, colorName, color).bool)
			color.ptr.wrap()
		else null
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.remove_class.html">gtk_style_context_remove_class</a>
	 */
	fun removeClass(className: String) {
		gtk_style_context_remove_class(styleContextPointer, className)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.remove_provider.html">gtk_style_context_remove_provider</a>
	 */
	fun removeProvider(provider: StyleProvider) {
		gtk_style_context_remove_provider(styleContextPointer, provider.styleProviderPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.restore.html">gtk_style_context_restore</a>
	 */
	fun restore() {
		gtk_style_context_restore(styleContextPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.save.html">gtk_style_context_save</a>
	 */
	fun save() {
		gtk_style_context_save(styleContextPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.StyleContext.to_string.html">gtk_style_context_to_string</a>
	 */
	fun toString(flags: StyleContentPrintFlags) =
		gtk_style_context_to_string(styleContextPointer, flags)!!.toKString()

	/**
	 * Using reflection, wraps the specific function that is reused often in this class
	 */
	private inline fun ((CValuesRef<GtkStyleContext>?, CValuesRef<GtkBorder>?) -> Unit).heavyWrap(): Border =
		memScoped {
			val border = cValue<GtkBorder>()
			this@heavyWrap(styleContextPointer, border)
			border.ptr.wrap()
		}

	companion object {
		const val PRINT_FLAGS_NONE: StyleContentPrintFlags = GTK_STYLE_CONTEXT_PRINT_NONE
		const val PRINT_FLAGS_RECURSE: StyleContentPrintFlags = GTK_STYLE_CONTEXT_PRINT_RECURSE
		const val PRINT_FLAGS_SHOW_STYLE: StyleContentPrintFlags = GTK_STYLE_CONTEXT_PRINT_SHOW_STYLE
		const val PRINT_FLAGS_SHOW_CHANGE: StyleContentPrintFlags = GTK_STYLE_CONTEXT_PRINT_SHOW_CHANGE

		inline fun CPointer<GtkStyleContext>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkStyleContext>.wrap() =
			StyleContext(this)


	}
}

/**
 * @see <a href="https://docs.gtk.org/gtk4/flags.StyleContextPrintFlags.html">GtkStyleContextPrintFlags</a>
 */
typealias StyleContentPrintFlags = GtkStyleContextPrintFlags