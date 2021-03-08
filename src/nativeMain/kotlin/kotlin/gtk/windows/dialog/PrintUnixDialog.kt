package kotlin.gtk.windows.dialog

import gtk.GtkDialog
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.windows.Window

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 *
 * TODO gtkunixprint.h
 */
class PrintUnixDialog internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val aboutDialogPointer: CPointer<GtkDialog>
) : Dialog(aboutDialogPointer.reinterpret())