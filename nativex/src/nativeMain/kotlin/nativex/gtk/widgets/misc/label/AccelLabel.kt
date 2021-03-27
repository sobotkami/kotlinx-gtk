package nativex.gtk.widgets.misc.label

import gtk.*
import kotlinx.cinterop.*
import nativex.gtk.asWidgetOrNull
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class AccelLabel internal constructor(
	internal val accelLabelPointer: CPointer<GtkAccelLabel>
) : Label(accelLabelPointer.reinterpret()) {

	constructor(label: String) : this(gtk_accel_label_new(label)!!.reinterpret())


	// TODO GClosure : fun setAccelClosure() : gtk_accel_label_set_accel_closure

	var accelWidget: Widget?
		get() = gtk_accel_label_get_accel_widget(accelLabelPointer).asWidgetOrNull()
		set(value) = gtk_accel_label_set_accel_widget(
			accelLabelPointer,
			value?.widgetPointer
		)

	@ExperimentalUnsignedTypes
	val accelWidth: UInt
		get() = gtk_accel_label_get_accel_width(accelLabelPointer)


	data class Accelerator @ExperimentalUnsignedTypes constructor(
		val key: UInt,
		val modifierType: UInt
	)

	fun setAccel(accelerator: Accelerator) {
		gtk_accel_label_set_accel(
			accelLabelPointer,
			accelerator.key,
			accelerator.modifierType
		)
	}

	@ExperimentalUnsignedTypes
	fun getAccel(): Accelerator = memScoped {
		val k = cValue<UIntVar>()
		val m = cValue<UIntVar>()
		Accelerator(k.ptr.pointed.value, m.ptr.pointed.value)
	}

}