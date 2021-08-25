package org.gtk.gtk.widgets.misc

import gtk.*
import gtk.GtkImageType.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import org.gtk.gdk.Pixbuf
import org.gtk.gio.Icon
import org.gtk.gio.ImplIcon.Companion.wrap
import org.gtk.gtk.widgets.Widget

/**
 * kotlinx-gtk
 *
 * 26 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html">GtkImage</a>
 */
class Image(
	val imagePointer: CPointer<GtkImage>
) : Widget(imagePointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-get-icon-name">
	 *     gtk_image_get_icon_name</a>
	 */
	val iconName: String?
		get() = gtk_image_get_icon_name(imagePointer)?.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-get-gicon">gtk_image_get_gicon</a>
	 */
	val gIcon: Icon?
		get() = gtk_image_get_gicon(imagePointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-get-storage-type">
	 *     gtk_image_get_storage_type</a>
	 */
	val storageType: Type
		get() = Type.valueOf(gtk_image_get_storage_type(imagePointer))!!

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-get-pixel-size">
	 *     gtk_image_get_pixel_size</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-set-pixel-size">
	 *     gtk_image_set_pixel_size</a>
	 */
	var pixelSize: Int
		set(value) = gtk_image_set_pixel_size(imagePointer, value)
		get() = gtk_image_get_pixel_size(imagePointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-new-from-gicon">
	 *     gtk_image_new_from_gicon</a>
	 */
	constructor(icon: Icon) : this(
		gtk_image_new_from_gicon(
			icon.pointer,
		)!!.reinterpret()
	)


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-new">gtk_image_new</a>
	 */
	constructor() : this(gtk_image_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-new-from-file">
	 *     gtk_image_new_from_resource</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-new-from-resource">
	 *     gtk_image_new_from_file</a>
	 */
	constructor(path: String, isResource: Boolean = false) : this(
		(if (isResource)
			gtk_image_new_from_resource(path)
		else gtk_image_new_from_file(path))!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-new-from-pixbuf">
	 *     gtk_image_new_from_pixbuf</a>
	 */
	constructor(pixbuf: Pixbuf) : this(gtk_image_new_from_pixbuf(pixbuf.pixbufPointer)!!.reinterpret())


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-new-from-icon-name">
	 *     gtk_image_new_from_icon_name</a>
	 */
	constructor(iconName: String?) : this(
		gtk_image_new_from_icon_name(iconName)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-set-from-resource">
	 *     gtk_image_set_from_resource</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-set-from-file">
	 *     gtk_image_set_from_file</a>
	 */
	fun setImage(path: String, isResource: Boolean = false) {
		if (isResource)
			gtk_image_set_from_resource(imagePointer, path)
		else gtk_image_set_from_file(imagePointer, path)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-set-from-pixbuf">
	 *     gtk_image_set_from_pixbuf</a>
	 */
	fun setImage(pixbuf: Pixbuf) {
		gtk_image_set_from_pixbuf(imagePointer, pixbuf.pixbufPointer)
	}


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-set-from-icon-name">
	 *     gtk_image_set_from_icon_name</a>
	 */
	fun setImage(iconName: String) {
		gtk_image_set_from_icon_name(imagePointer, iconName)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-set-from-gicon">
	 *     gtk_image_set_from_gicon</a>
	 */
	fun setImage(icon: Icon) {
		gtk_image_set_from_gicon(imagePointer, icon.pointer)
	}


	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-clear">gtk_image_clear</a>
	 */
	fun clear() {
		gtk_image_clear(imagePointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#GtkImageType">GtkImageType</a>
	 */
	enum class Type(val gtk: GtkImageType) {
		/**
		 * There is no image displayed by the widget
		 */
		EMPTY(GTK_IMAGE_EMPTY),


		/**
		 * The widget contains a named icon.
		 *
		 * @since 2.6
		 */
		ICON_NAME(GTK_IMAGE_ICON_NAME),

		/**
		 * The widget contains a GIcon.
		 *
		 * @since 2.14
		 */
		GICON(GTK_IMAGE_GICON);

		companion object {
			fun valueOf(gtk: GtkImageType) =
				values().find { it.gtk == gtk }
		}
	}
}