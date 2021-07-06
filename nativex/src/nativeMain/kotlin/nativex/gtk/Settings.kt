package nativex.gtk

import gtk.GtkSettings
import gtk.gtk_settings_get_default
import gtk.gtk_settings_get_for_screen
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gdk.Screen
import nativex.gobject.KObject

class Settings(
	 val settingsPointer: CPointer<GtkSettings>
) : KObject(settingsPointer.reinterpret()) {
	companion object {
		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSettings.html#gtk-settings-get-default">
		 *     gtk_settings_get_default</a>
		 */
		val default: Settings?
			get() = gtk_settings_get_default().wrap()

		/**
		 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkSettings.html#gtk-settings-get-for-screen">
		 *     gtk_settings_get_for_screen</a>
		 */
		fun getForScreen(screen: Screen): Settings =
			gtk_settings_get_for_screen(screen.screenPointer)!!.wrap()

		 inline fun CPointer<GtkSettings>?.wrap() =
			this?.let { Settings(it) }

		 inline fun CPointer<GtkSettings>.wrap() =
			Settings(this)
	}
}