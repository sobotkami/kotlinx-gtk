package nativex.gtk

import gtk.GtkPageSetup
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 09 / 06 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkPageSetup.html">GtkPageSetup</a>
 */
class PageSetup(
  val pageSetupPointer:CPointer<GtkPageSetup>
){

 companion object{
   inline fun CPointer<GtkPageSetup>?.wrap() =
   this?.wrap()

   inline fun CPointer<GtkPageSetup>.wrap() =
   PageSetup(this)
 }

}