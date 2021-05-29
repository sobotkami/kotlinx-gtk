package nativex.gtk

import gtk.GtkClipboard
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gdk.Display
import nativex.gio.KObject

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-Clipboards.html#GtkClipboard">
 *     GtkClipboard</a>
 */
class Clipboard internal constructor(
	internal val clipboardPointer: CPointer<GtkClipboard>
) : KObject(clipboardPointer.reinterpret()) {
}