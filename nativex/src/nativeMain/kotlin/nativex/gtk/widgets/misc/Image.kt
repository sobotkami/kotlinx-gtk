package nativex.gtk.widgets.misc

import gtk.*
import gtk.GtkImageType.*
import kotlinx.cinterop.*
import nativex.gdk.Pixbuf
import nativex.gdk.PixbufAnimation
import nativex.gtk.IconSize

/**
 * kotlinx-gtk
 * 26 / 03 / 2021
 */
class Image internal constructor(
	internal val imagePointer: CPointer<GtkImage>
) : Misc(imagePointer.reinterpret()) {

	//TODO GIcon for this constructor : gtk_image_new_from_gicon
	//constructor(icon: Any, iconSize: IconSize)

	//TODO cario_surface_t for another constructor : gtk_image_new_from_surface

	val animation: PixbufAnimation?
		get() = gtk_image_get_animation(imagePointer)?.let { PixbufAnimation(it) }
	val iconName: Pair<String?, IconSize?>
		get() = memScoped {
			val s = cValue<CPointerVar<ByteVarOf<Byte>>>()
			val size = cValue<GtkIconSize.Var>()

			gtk_image_get_icon_name(imagePointer, s, size)
			s.ptr.pointed.pointed?.ptr?.toKString() to IconSize.valueOf(size.ptr.pointed.value)
		}

	/**
	 * TODO GIcon
	 */
	//fun getGicon(): Pair<Any, IconSize>

	val storageType: Type
		get() = Type.valueOf(gtk_image_get_storage_type(imagePointer))!!
	var pixelSeize: Int
		set(value) = gtk_image_set_pixel_size(imagePointer, value)
		get() = gtk_image_get_pixel_size(imagePointer)

	constructor() : this(gtk_image_new()!!.reinterpret())
	constructor(path: String, isResource: Boolean = false) : this(
		(if (isResource)
			gtk_image_new_from_resource(path)
		else gtk_image_new_from_file(path))!!.reinterpret()
	)

	constructor(pixbuf: Pixbuf) : this(gtk_image_new_from_pixbuf(pixbuf.pixbufPointer)!!.reinterpret())
	constructor(animation: PixbufAnimation) : this(
		gtk_image_new_from_animation(
			animation.pixBufAnimationPointer
		)!!.reinterpret()
	)

	constructor(iconName: String?, iconSize: IconSize) : this(
		gtk_image_new_from_icon_name(iconName, iconSize.gtk)!!.reinterpret()
	)

	fun setImage(path: String, isResource: Boolean = false) {
		if (isResource)
			gtk_image_set_from_resource(imagePointer, path)
		else gtk_image_set_from_file(imagePointer, path)
	}

	fun setImage(pixbuf: Pixbuf) {
		gtk_image_set_from_pixbuf(imagePointer, pixbuf.pixbufPointer)
	}

	fun setImage(animation: PixbufAnimation) {
		gtk_image_set_from_animation(
			imagePointer,
			animation.pixBufAnimationPointer
		)
	}

	fun setImage(iconName: String, iconSize: IconSize) {
		gtk_image_set_from_icon_name(imagePointer, iconName, iconSize.gtk)
	}

	fun clearImage() {
		gtk_image_clear(imagePointer)
	}

	enum class Type(val gtk: GtkImageType) {
		EMPTY(GTK_IMAGE_EMPTY),
		PIXBUF(GTK_IMAGE_PIXBUF),
		STOCK(GTK_IMAGE_STOCK),
		ICON_SET(GTK_IMAGE_ICON_SET),
		ANIMATION(GTK_IMAGE_ANIMATION),
		ICON_NAME(GTK_IMAGE_ICON_NAME),
		GICON(GTK_IMAGE_GICON),
		SURFACE(GTK_IMAGE_SURFACE);

		companion object {
			fun valueOf(gtk: GtkImageType) =
				values().find { it.gtk == gtk }
		}
	}
}