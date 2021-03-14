package kotlin.gtk.container.bin

import gtk.*
import kotlinx.cinterop.*
import kotlin.gtk.common.enums.ShadowType
import kotlin.gtk.widgets.Widget

/**
 * kotlinx-gtk
 * 13 / 03 / 2021
 */
class Frame(
	internal val framePointer: CPointer<GtkFrame>
) : Bin(framePointer.reinterpret()) {

	constructor(
		label: String? = null
	) : this(gtk_frame_new(label)!!.reinterpret())

	var label: String?
		get() = gtk_frame_get_label(framePointer)?.toKString()
		set(value) {}

	var labelAlign: Pair<Float, Float>
		get() {
			val x = cValue<FloatVar>()
			val y = cValue<FloatVar>()
			gtk_frame_get_label_align(framePointer, x, y)
			return memScoped {
				x.ptr.pointed.value to y.ptr.pointed.value
			}
		}
		set(value) =
			gtk_frame_set_label_align(framePointer, value.first, value.second)

	var labelWidget: Widget?
		get() = gtk_frame_get_label_widget(framePointer)?.let { Widget(it) }
		set(value) = gtk_frame_set_label_widget(
			framePointer,
			value?.widgetPointer
		)

	var shadowType: ShadowType
		get() = ShadowType.valueOf(
			gtk_frame_get_shadow_type(
				framePointer
			)
		)!!
		set(value) = gtk_frame_set_shadow_type(framePointer, value.gtk)

}