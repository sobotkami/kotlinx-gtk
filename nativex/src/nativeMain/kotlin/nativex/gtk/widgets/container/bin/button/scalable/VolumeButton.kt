package nativex.gtk.widgets.container.bin.button.scalable

import gtk.GtkOrientable
import gtk.GtkVolumeButton
import gtk.gtk_volume_button_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.Orientable

/**
 * kotlinx-gtk
 *
 * 07 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkVolumeButton.html">GtkVolumeButton</a>
 */
class VolumeButton(val volumeButtonPointer: CPointer<GtkVolumeButton>) : ScaleButton(volumeButtonPointer.reinterpret()),
	Orientable {
	override val orientablePointer: CPointer<GtkOrientable> by lazy { volumeButtonPointer.reinterpret() }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkVolumeButton.html#gtk-volume-button-new">
	 *     gtk_volume_button_new</a>
	 */
	constructor() : this(gtk_volume_button_new()!!.reinterpret())
}