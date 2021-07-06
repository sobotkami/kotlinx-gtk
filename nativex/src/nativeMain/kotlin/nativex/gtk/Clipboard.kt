package nativex.gtk

import gtk.GtkClipboard
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gobject.KObject

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/gtk3-Clipboards.html#GtkClipboard">
 *     GtkClipboard</a>
 */
class Clipboard(
	 val clipboardPointer: CPointer<GtkClipboard>
) : KObject(clipboardPointer.reinterpret())