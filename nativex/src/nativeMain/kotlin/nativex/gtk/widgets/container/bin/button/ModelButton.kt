package nativex.gtk.widgets.container.bin.button

import gtk.GtkButtonRole
import gtk.GtkModelButton
import gtk.gtk_model_button_get_type
import gtk.gtk_model_button_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 *
 * 16 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkModelButton.html">GtkModelButton</a>
 */
class ModelButton(val toggleButtonPointer: CPointer<GtkModelButton>) : Button(toggleButtonPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkModelButton.html#gtk-model-button-new">
	 *     gtk_model_button_new</a>
	 */
	constructor() : this(gtk_model_button_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkModelButton.html#GtkButtonRole">GtkButtonRole</a>
	 */
	enum class ButtonRole(val key: Int,  val gtk: GtkButtonRole) {
		/**
		 * A plain button
		 */
		NORMAL(0, GtkButtonRole.GTK_BUTTON_ROLE_NORMAL),

		/**
		 * A check button
		 */
		CHECK(1, GtkButtonRole.GTK_BUTTON_ROLE_CHECK),

		/**
		 * A radio button
		 */
		RADIO(2, GtkButtonRole.GTK_BUTTON_ROLE_RADIO);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }

			 fun valueOf(gtk: GtkButtonRole) =
				values().find { it.gtk == gtk }
		}
	}
}