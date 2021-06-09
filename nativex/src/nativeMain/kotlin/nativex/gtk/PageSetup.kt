package nativex.gtk

import gtk.GtkPageSetup
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 09 / 06 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPageSetup.html">GtkPageSetup</a>
 */
class PageSetup internal constructor(
 internal val pageSetupPointer:CPointer<GtkPageSetup>
){

 companion object{
  internal inline fun CPointer<GtkPageSetup>?.wrap() =
   this?.wrap()

  internal inline fun CPointer<GtkPageSetup>.wrap() =
   PageSetup(this)
 }

}