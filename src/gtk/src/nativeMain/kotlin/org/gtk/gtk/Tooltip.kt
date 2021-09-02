package org.gtk.gtk

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gdk.Paintable
import org.gtk.gdk.Rectangle
import org.gtk.gio.Icon
import org.gtk.gobject.KGObject
import org.gtk.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 09 / 06 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/class.Tooltip.html">
 *     GtkTooltip</a>
 */
class Tooltip(
	val tooltipPointer: CPointer<GtkTooltip>
) : KGObject(tooltipPointer.reinterpret()) {

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Tooltip.set_custom.html">
	 *     gtk_tooltip_set_custom</a>
	 */
	fun setCustom(customWidget: Widget) {
		gtk_tooltip_set_custom(tooltipPointer, customWidget.widgetPointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Tooltip.set_icon.html">
	 *     gtk_tooltip_set_icon</a>
	 */
	fun setIcon(paintable: Paintable) {
		gtk_tooltip_set_icon(tooltipPointer, paintable.paintablePointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Tooltip.set_icon_from_gicon.html">
	 *     gtk_tooltip_set_icon_from_gicon</a>
	 */
	fun setIcon(icon: Icon) {
		gtk_tooltip_set_icon_from_gicon(tooltipPointer, icon.pointer)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Tooltip.set_icon_from_icon_name.html">
	 *     gtk_tooltip_set_icon_from_icon_name</a>
	 */
	fun setIcon(iconName: String) {
		gtk_tooltip_set_icon_from_icon_name(tooltipPointer, iconName)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Tooltip.set_markup.html">
	 *     gtk_tooltip_set_markup</a>
	 */
	fun setMarkup(markup: String) {
		gtk_tooltip_set_markup(tooltipPointer, markup)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Tooltip.set_text.html">
	 *     gtk_tooltip_set_text</a>
	 */
	fun setText(text: String) {
		gtk_tooltip_set_text(tooltipPointer, text)
	}

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/method.Tooltip.set_tip_area.html">
	 *     gtk_tooltip_set_tip_area</a>
	 */
	fun setTipArea(rect: Rectangle) {
		gtk_tooltip_set_tip_area(tooltipPointer, rect.rectanglePointer)
	}

	companion object {
		inline fun CPointer<GtkTooltip>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkTooltip>.wrap() =
			Tooltip(this)
	}
}