package kotlin.gtk.windows.dialog

import gtk.GtkRecentChooserDialog
import gtk.gtk_recent_chooser_dialog_new
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlin.gtk.windows.Window

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 *
 * TODO GtkRecentManager
 */
class RecentChooserDialog internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val aboutDialogPointer: CPointer<GtkRecentChooserDialog>
) : Dialog(aboutDialogPointer.reinterpret()) {

	/**
	 * TODO vararg pass through
	 */
	constructor(
		title: String? = null,
		parent: Window? = null,
		firstButtonText: String? = null,
		vararg arguments: Any?
	) : this(
		gtk_recent_chooser_dialog_new(
			title = title,
			parent = parent?.windowPointer,
			first_button_text = firstButtonText,
		)!!.reinterpret()
	)
}