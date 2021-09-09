package org.gtk.gtk

import gtk.GtkStyleProvider
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gobject.KGObject
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback

/**
 * gtk-kt
 *
 * 08 / 09 / 2021
 *
 * @see <a href="https://docs.gtk.org/gtk4/signal.StyleProvider.gtk-private-changed.html">
 *     GtkStyleProvider</a>
 */
interface StyleProvider {
	val styleProviderPointer: CPointer<GtkStyleProvider>

	/**
	 * @see <a href="https://docs.gtk.org/gtk4/signal.StyleProvider.gtk-private-changed.html">
	 *     gtk-private-changed</a>
	 */
	fun addOnGTKPrivateChangedCallback(action: () -> Unit) =
		KGObject(styleProviderPointer.reinterpret()).addSignalCallback(Signals.GTK_PRIVATE_CHANGED, action)
}