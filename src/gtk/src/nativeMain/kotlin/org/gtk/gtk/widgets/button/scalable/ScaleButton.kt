package org.gtk.gtk.widgets.button.scalable

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.StableRef
import kotlinx.cinterop.reinterpret
import org.gtk.async.popdownSignalManager
import org.gtk.async.popupSignalManager
import org.gtk.async.staticDoubleCallback
import org.gtk.glib.toNullTermCStringArray
import org.gtk.gobject.SignalManager
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback
import org.gtk.gtk.Adjustment
import org.gtk.gtk.widgets.Widget
import org.gtk.gtk.widgets.button.Button

/**
 * kotlinx-gtk
 *
 * 16 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScaleButton.html">GtkScaleButton</a>
 */
open class ScaleButton(
	val scaleButtonPointer: CPointer<GtkScaleButton>
) : Button(scaleButtonPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScaleButton.html#gtk-scale-button-new">
	 *     gtk_scale_button_new</a>
	 */
	constructor(
		min: Double = 0.0,
		max: Double = 100.0,
		step: Double = 2.0,
		icons: List<String>? = null
	) : this(
		gtk_scale_button_new(
			min,
			max,
			step,
			icons?.toNullTermCStringArray()
		)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScaleButton.html#gtk-scale-button-get-adjustment">
	 *     gtk_scale_button_get_adjustment</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScaleButton.html#gtk-scale-button-set-adjustment">
	 *     gtk_scale_button_set_adjustment</a>
	 */
	var adjustment: Adjustment?
		get() = gtk_scale_button_get_adjustment(scaleButtonPointer)?.let {
			Adjustment(
				it
			)
		}
		set(value) = gtk_scale_button_set_adjustment(
			scaleButtonPointer,
			value?.adjustmentPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScaleButton.html#gtk-scale-button-set-icons">
	 *     gtk_scale_button_set_icons</a>
	 */
	fun setIcons(icons: List<String>) {
		gtk_scale_button_set_icons(
			scaleButtonPointer,
			icons.toNullTermCStringArray()
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScaleButton.html#gtk-scale-button-get-value">
	 *     gtk_scale_button_get_value</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScaleButton.html#gtk-scale-button-set-value">
	 *     gtk_scale_button_set_value</a>
	 */
	var value: Double
		get() = gtk_scale_button_get_value(scaleButtonPointer)
		set(value) = gtk_scale_button_set_value(scaleButtonPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScaleButton.html#gtk-scale-button-get-popup">
	 *     gtk_scale_button_get_popup</a>
	 */
	val popup: Widget
		get() = Widget(gtk_scale_button_get_popup(scaleButtonPointer)!!)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScaleButton.html#gtk-scale-button-get-plus-button">
	 *     gtk_scale_button_get_plus_button</a>
	 */
	val plusButton: Button
		get() = gtk_scale_button_get_plus_button(scaleButtonPointer)!!.reinterpret<GtkButton>().wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScaleButton.html#gtk-scale-button-get-minus-button">
	 *     gtk_scale_button_get_minus_button</a>
	 */
	val minusButton: Button
		get() = gtk_scale_button_get_minus_button(scaleButtonPointer)!!.reinterpret<GtkButton>().wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScaleButton.html#GtkScaleButton-popdown">popdown</a>
	 */
	fun addOnPopdownCallback(action: () -> Unit): SignalManager =
		popdownSignalManager(scaleButtonPointer, action)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScaleButton.html#GtkScaleButton-popup">popup</a>
	 */
	fun addOnPopupCallback(action: () -> Unit): SignalManager =
		popupSignalManager(scaleButtonPointer, action)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkScaleButton.html#GtkScaleButton-value-changed">
	 *     value-changed</a>
	 */
	fun addOnValueChangedCallback(action: (Double) -> Unit): SignalManager =
		addSignalCallback(
			Signals.VALUE_CHANGED,
			action
		)
}