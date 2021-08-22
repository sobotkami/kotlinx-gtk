package nativex.gtk.widgets.windows.dialog

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import nativex.gtk.RecentManager
import nativex.gtk.widgets.windows.Window

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 *
 * TODO GtkRecentManager
 */
class RecentChooserDialog(
	@Suppress("MemberVisibilityCanBePrivate")
	 val aboutDialogPointer: CPointer<GtkRecentChooserDialog>
) : Dialog(aboutDialogPointer.reinterpret()) {

	constructor(
		title: String? = null,
		parent: Window? = null,
		yesString: String,
		noString: String
	) : this(
		gtk_recent_chooser_dialog_new(
			title = title,
			parent = parent?.windowPointer,
			first_button_text = noString,
			GTK_RESPONSE_CANCEL,
			yesString,
			GTK_RESPONSE_ACCEPT,
			null
		)!!.reinterpret()
	)

	constructor(
		title: String? = null,
		parent: Window? = null,
		manager: RecentManager,
		yesString: String,
		noString: String
	) : this(
		gtk_recent_chooser_dialog_new_for_manager(
			title = title,
			parent = parent?.windowPointer,
			manager = manager.managerPointer,
			first_button_text = noString,
			GTK_RESPONSE_CANCEL,
			yesString,
			GTK_RESPONSE_ACCEPT,
			null
		)!!.reinterpret()
	)
}