package nativex.gtk.widgets.misc.label

import gtk.*
import kotlinx.cinterop.*
import nativex.glib.bool
import nativex.gobject.Closure
import nativex.gtk.asWidgetOrNull
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 26 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAccelLabel.html">GtkAccelLabel</a>
 */
class AccelLabel(
	val accelLabelPointer: CPointer<GtkAccelLabel>
) : Label(accelLabelPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAccelLabel.html#gtk-accel-label-new">
	 *     gtk_accel_label_new</a>
	 */
	constructor(label: String) : this(gtk_accel_label_new(label)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAccelLabel.html#gtk-accel-label-set-accel-closure">
	 *     gtk_accel_label_set_accel_closure</a>
	 */
	fun setAccelClosure(closure: Closure) {
		gtk_accel_label_set_accel_closure(accelLabelPointer, closure.closurePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAccelLabel.html#gtk-accel-label-get-accel-widget">
	 *     gtk_accel_label_get_accel_widget</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAccelLabel.html#gtk-accel-label-set-accel-widget">
	 *     gtk_accel_label_set_accel_widget</a>
	 */
	var accelWidget: Widget?
		get() = gtk_accel_label_get_accel_widget(accelLabelPointer).asWidgetOrNull()
		set(value) = gtk_accel_label_set_accel_widget(
			accelLabelPointer,
			value?.widgetPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAccelLabel.html#gtk-accel-label-get-accel-width">
	 *     gtk_accel_label_get_accel_width</a>
	 */
	val accelWidth: UInt
		get() = gtk_accel_label_get_accel_width(accelLabelPointer)

	data class Accelerator constructor(
		val key: UInt,
		val modifierType: UInt
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAccelLabel.html#gtk-accel-label-get-accel">
	 *     gtk_accel_label_get_accel</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAccelLabel.html#gtk-accel-label-set-accel">
	 *     gtk_accel_label_set_accel</a>
	 */
	var accel: Accelerator
		get() = memScoped {
			val k = cValue<UIntVar>()
			val m = cValue<UIntVar>()
			gtk_accel_label_get_accel(accelLabelPointer, k, m)
			Accelerator(k.ptr.pointed.value, m.ptr.pointed.value)
		}
		set(accelerator) {
			gtk_accel_label_set_accel(
				accelLabelPointer,
				accelerator.key,
				accelerator.modifierType
			)
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAccelLabel.html#gtk-accel-label-refetch">
	 *     gtk_accel_label_refetch</a>
	 */
	fun refetch(): Boolean =
		gtk_accel_label_refetch(accelLabelPointer).bool
}