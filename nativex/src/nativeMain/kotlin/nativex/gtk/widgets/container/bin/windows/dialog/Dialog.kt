package nativex.gtk.widgets.container.bin.windows.dialog

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.widgets.container.bin.windows.Window

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
open class Dialog internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val dialogPointer: CPointer<GtkDialog>
) : Window(dialogPointer.reinterpret()) {
	constructor() : this(gtk_dialog_new()!!.reinterpret())


	fun run(): Int =
		gtk_dialog_run(dialogPointer)


	enum class Flags(val key: Int, internal val gtk: GtkDialogFlags) {
		MODAL(0, GTK_DIALOG_MODAL),
		DESTROY_WITH_PARENT(1, GTK_DIALOG_DESTROY_WITH_PARENT),
		USE_HEADER_BAR(2, GTK_DIALOG_USE_HEADER_BAR);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }

			
			internal fun valueOf(gtk: GtkDialogFlags) =
				values().find { it.gtk == gtk }
		}
	}

	enum class ResponseType(val key: Int, internal val gtk: GtkResponseType) {
		NONE(0, GTK_RESPONSE_NONE),

		REJECT(1, GTK_RESPONSE_REJECT),

		ACCEPT(2, GTK_RESPONSE_ACCEPT),

		DELETE_EVENT(3, GTK_RESPONSE_DELETE_EVENT),

		OK(4, GTK_RESPONSE_OK),

		CANCEL(5, GTK_RESPONSE_CANCEL),

		CLOSE(6, GTK_RESPONSE_CLOSE),

		YES(7, GTK_RESPONSE_YES),

		NO(8, GTK_RESPONSE_NO),

		APPLY(9, GTK_RESPONSE_APPLY),

		HELP(10, GTK_RESPONSE_HELP);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }

			
			internal fun valueOfGtk(gtk: GtkResponseType) =
				values().find { it.gtk == gtk }
		}
	}
}