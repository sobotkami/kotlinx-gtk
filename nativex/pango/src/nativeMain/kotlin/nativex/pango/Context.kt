package nativex.pango

import pango.PangoContext
import kotlinx.cinterop.CPointer

/**
 * kotlinx-gtk
 * 22 / 05 / 2021
 */
class Context( val pointer: CPointer<PangoContext>){
	companion object{
		inline fun CPointer<PangoContext>?.wrap() =
			this?.wrap()

		inline fun CPointer<PangoContext>.wrap() =
			Context(this)
	}
}