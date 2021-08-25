package nativex.gtk.common.data

import gtk.GtkRequisition
import gtk.gtk_requisition_copy
import gtk.gtk_requisition_free
import gtk.gtk_requisition_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.pointed
import nativex.Closeable
import nativex.ClosedException

/**
 * kotlinx-gtk
 * 06 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#GtkRequisition">GtkRequisition</a>
 *
 * After being used, should be closed
 */
class Requisition(
	val requisitionPointer: CPointer<GtkRequisition>
) : Closeable {
	constructor() : this(gtk_requisition_new()!!)

	private var isClosed = false

	val width: Int
		get() {
			if (isClosed) throw ClosedException("Requisition pointer has been freed")
			return requisitionPointer.pointed.width
		}

	val height: Int
		get() {
			if (isClosed) throw ClosedException("Requisition pointer has been freed")
			return requisitionPointer.pointed.height
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-requisition-copy">gtk_requisition_copy</a>
	 */
	fun copy(): Requisition {
		if (isClosed) throw ClosedException("Requisition pointer has been freed")
		return Requisition(gtk_requisition_copy(requisitionPointer)!!)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkWidget.html#gtk-requisition-free">gtk_requisition_free</a>
	 */
	fun free() {
		if (isClosed) throw ClosedException("Requisition pointer has been freed")
		gtk_requisition_free(requisitionPointer)
		isClosed = true
	}

	override fun close() {
		free()
	}

	companion object {
		inline fun CPointer<GtkRequisition>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkRequisition>.wrap() =
			Requisition(this)

	}
}
