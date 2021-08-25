package org.gtk.gdk

import gtk.GdkContentProvider
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.gobject.KGObject

/**
 * kotlinx-gtk
 *
 * 24 / 08 / 2021
 *
 * @see <a href=""></a>
 */
class ContentProvider(val contentProviderPointer: CPointer<GdkContentProvider>) :
	KGObject(contentProviderPointer.reinterpret()) {

}