package nativex.gtk

import gtk.GtkPrinter
import kotlinx.cinterop.CPointer

/**
 * Stud class
 */
class Printer internal constructor(
	internal val printerPointer: CPointer<GtkPrinter>
) {
}