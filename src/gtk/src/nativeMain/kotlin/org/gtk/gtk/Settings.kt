package org.gtk.gtk

import gtk.GtkSettings
import gtk.gtk_settings_get_default
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gobject.KGObject

class Settings(
	 val settingsPointer: CPointer<GtkSettings>
) : KGObject(settingsPointer.reinterpret()) {
	companion object {
		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSettings.html#gtk-settings-get-default">
		 *     gtk_settings_get_default</a>
		 */
		val default: Settings?
			get() = gtk_settings_get_default().wrap()

		 inline fun CPointer<GtkSettings>?.wrap() =
			this?.let { Settings(it) }

		 inline fun CPointer<GtkSettings>.wrap() =
			Settings(this)
	}
}