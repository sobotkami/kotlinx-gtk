package nativex.gtk.widgets.container.bin.button

import gtk.GtkButtonRole
import gtk.GtkModelButton
import gtk.gtk_model_button_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.pointed
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 16 / 03 / 2021
 */
class ModelButton internal constructor(
	toggleButtonPointer: CPointer<GtkModelButton>
) : Button(toggleButtonPointer.reinterpret()) {

	constructor() : this(gtk_model_button_new()!!.reinterpret())

	enum class ButtonRole(val key: Int, internal val gtk: GtkButtonRole) {
		NORMAL(0, GtkButtonRole.GTK_BUTTON_ROLE_NORMAL),
		CHECK(1, GtkButtonRole.GTK_BUTTON_ROLE_CHECK),
		RADIO(2, GtkButtonRole.GTK_BUTTON_ROLE_RADIO);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }

			internal fun valueOf(gtk: GtkButtonRole) =
				values().find { it.gtk == gtk }
		}
	}
}