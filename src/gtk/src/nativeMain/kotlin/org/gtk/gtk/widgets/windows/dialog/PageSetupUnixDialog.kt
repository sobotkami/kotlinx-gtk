package org.gtk.gtk.widgets.windows.dialog

import gtk.GtkDialog
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 *
 * TODO gtkunixprint.h
 */
class PageSetupUnixDialog(
	@Suppress("MemberVisibilityCanBePrivate")
	 val aboutDialogPointer: CPointer<GtkDialog>
) : Dialog(aboutDialogPointer.reinterpret()) {

	/*
	constructor(
		parent: Window? = null,
		title: String? = null
	) : this(
		gtk_page_setup_unix_dialog_new(title, parent?.windowPointer)
	)
	*/
}