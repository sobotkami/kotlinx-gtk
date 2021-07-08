package nativex.gtk.widgets.misc

import gio.GIcon
import gtk.*
import gtk.GtkImageType.*
import kotlinx.cinterop.*
import nativex.cairo.CairoSurfaceT
import nativex.gdk.Pixbuf
import nativex.gdk.Pixbuf.Companion.wrap
import nativex.gdk.PixbufAnimation
import nativex.gio.Icon
import nativex.gio.ImplIcon.Companion.wrap
import nativex.gtk.IconSize

/**
 * kotlinx-gtk
 *
 * 26 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html">GtkImage</a>
 */
class Image(
	val imagePointer: CPointer<GtkImage>
) : Misc(imagePointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-get-animation">
	 *     gtk_image_get_animation</a>
	 */
	val animation: PixbufAnimation?
		get() = gtk_image_get_animation(imagePointer)?.let { PixbufAnimation(it) }

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-get-pixbuf">
	 *     gtk_image_get_pixbuf</a>
	 */
	val pixbuf: Pixbuf?
		get() = gtk_image_get_pixbuf(imagePointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-get-icon-name">
	 *     gtk_image_get_icon_name</a>
	 */
	val iconName: Pair<String?, IconSize?>
		get() = memScoped {
			val s = cValue<CPointerVar<ByteVarOf<Byte>>>()
			val size = cValue<GtkIconSize.Var>()
			gtk_image_get_icon_name(imagePointer, s, size)
			s.ptr.pointed.pointed?.ptr?.toKString() to IconSize.valueOf(size.ptr.pointed.value)
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-get-gicon">gtk_image_get_gicon</a>
	 */
	val gIcon: Pair<Icon?, IconSize?>
		get() = memScoped {
			val i = cValue<CPointerVar<GIcon>>()
			val s = cValue<GtkIconSize.Var>()
			gtk_image_get_gicon(imagePointer, i, s)
			i.ptr.pointed.value.wrap() to IconSize.valueOf(s.ptr.pointed.value)
		}

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
	constructor(icon: Icon, iconSize: IconSize) : this(
		gtk_image_new_from_gicon(
			icon.pointer,
			iconSize.gtk
		)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-new-from-surface">
	 *     gtk_image_new_from_surface</a>
	 */
	constructor(surfaceT: CairoSurfaceT) : this(gtk_image_new_from_surface(surfaceT.cPointer)!!.reinterpret())

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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-new-from-animation">
	 *     gtk_image_new_from_animation</a>
	 */
	constructor(animation: PixbufAnimation) : this(
		gtk_image_new_from_animation(
			animation.pixBufAnimationPointer
		)!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-new-from-icon-name">
	 *     gtk_image_new_from_icon_name</a>
	 */
	constructor(iconName: String?, iconSize: IconSize) : this(
		gtk_image_new_from_icon_name(iconName, iconSize.gtk)!!.reinterpret()
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
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-set-from-animation">
	 *     gtk_image_set_from_animation</a>
	 */
	fun setImage(animation: PixbufAnimation) {
		gtk_image_set_from_animation(
			imagePointer,
			animation.pixBufAnimationPointer
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-set-from-icon-name">
	 *     gtk_image_set_from_icon_name</a>
	 */
	fun setImage(iconName: String, iconSize: IconSize) {
		gtk_image_set_from_icon_name(imagePointer, iconName, iconSize.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-set-from-gicon">
	 *     gtk_image_set_from_gicon</a>
	 */
	fun setImage(icon: Icon, size: IconSize) {
		gtk_image_set_from_gicon(imagePointer, icon.pointer, size.gtk)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkImage.html#gtk-image-set-from-surface">
	 *     gtk_image_set_from_surface</a>
	 */
	fun setImage(surfaceT: CairoSurfaceT) {
		gtk_image_set_from_surface(imagePointer, surfaceT.cPointer)
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
		 * The widget contains a GdkPixbuf
		 */
		PIXBUF(GTK_IMAGE_PIXBUF),

		/**
		 * The widget contains a stock item name
		 */
		STOCK(GTK_IMAGE_STOCK),

		/**
		 * The widget contains a GtkIconSet
		 */
		ICON_SET(GTK_IMAGE_ICON_SET),

		/**
		 * The widget contains a GdkPixbufAnimation
		 */
		ANIMATION(GTK_IMAGE_ANIMATION),

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
		GICON(GTK_IMAGE_GICON),

		/**
		 * The widget contains a cairo_surface_t.
		 *
		 * @since 3.10
		 */
		SURFACE(GTK_IMAGE_SURFACE);

		companion object {
			fun valueOf(gtk: GtkImageType) =
				values().find { it.gtk == gtk }
		}
	}
}