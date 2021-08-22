package nativex.gtk.widgets.frame

import gtk.*
import kotlinx.cinterop.*
import nativex.gtk.common.enums.ShadowType
import nativex.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 13 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html">GtkFrame</a>
 */
open class Frame(
	 val framePointer: CPointer<GtkFrame>
) : Widget(framePointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-new">gtk_frame_new</a>
	 */
	constructor(
		label: String? = null
	) : this(gtk_frame_new(label)!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-get-label">gtk_frame_get_label</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-set-label">gtk_frame_set_label</a>
	 */
	var label: String?
		get() = gtk_frame_get_label(framePointer)?.toKString()
		set(value) {
			gtk_frame_set_label(framePointer, value)
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-get-label-align">gtk_frame_get_label_align</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-set-label-align">gtk_frame_set_label_align</a>
	 */
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

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-get-label-widget">gtk_frame_get_label_widget</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-set-label-widget">gtk_frame_set_label_widget</a>
	 */
	var labelWidget: Widget?
		get() = gtk_frame_get_label_widget(framePointer)?.let { Widget(it) }
		set(value) = gtk_frame_set_label_widget(
			framePointer,
			value?.widgetPointer
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-get-shadow-type">
	 *     gtk_frame_get_shadow_type</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFrame.html#gtk-frame-set-shadow-type">
	 *     gtk_frame_set_shadow_type</a>
	 */
	var shadowType: ShadowType
		get() = ShadowType.valueOf(
			gtk_frame_get_shadow_type(
				framePointer
			)
		)!!
		set(value) = gtk_frame_set_shadow_type(framePointer, value.gtk)

}