package nativex.gtk.widgets.container.bin.windows.dialog
/*
import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 *
 * TODO gtkunixprint.h
 */
class PrintUnixDialog internal constructor(
	internal val aboutDialogPointer: CPointer<GtkPrintUnixDialog>
) : Dialog(aboutDialogPointer.reinterpret()) {

	enum class PrintCapabilities(val key: Int, internal val gtk: GtkPrintCapabilities) {
		PAGE_SET(0, GTK_PRINT_CAPABILITY_PAGE_SET),

		COPIES(1, GTK_PRINT_CAPABILITY_COPIES),

		COLLATE(2, GTK_PRINT_CAPABILITY_COLLATE),

		REVERSE(3, GTK_PRINT_CAPABILITY_REVERSE),

		SCALE(4, GTK_PRINT_CAPABILITY_SCALE),

		GENERATE_PDF(5, GTK_PRINT_CAPABILITY_GENERATE_PDF),

		GENERATE_PS(6, GTK_PRINT_CAPABILITY_GENERATE_PS),

		PREVIEW(7, GTK_PRINT_CAPABILITY_PREVIEW),

		NUMBER_UP(8, GTK_PRINT_CAPABILITY_NUMBER_UP),

		NUMBER_UP_LAYOUT(9, GTK_PRINT_CAPABILITY_NUMBER_UP_LAYOUT);

		companion object{
			fun valueOf(key: Int) =
				values().find { it.key == key }

			internal fun valueOf(gtk: GtkPrintCapabilities) =
				values().find { it.gtk == gtk }

		}
	}
}

 */