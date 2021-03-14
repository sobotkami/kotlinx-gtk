package kotlin.gtk.container.bin.windows.dialog

import gtk.*
import gtk.GtkLicense.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import kotlin.gtk.asStringList
import kotlin.gtk.from
import kotlin.gtk.gtkValue
import kotlin.gtk.toCStringArray

/**
 * kotlinx-gtk
 * 08 / 03 / 2021
 */
class AboutDialog internal constructor(
	@Suppress("MemberVisibilityCanBePrivate")
	internal val aboutDialogPointer: CPointer<GtkAboutDialog>
) : Dialog(aboutDialogPointer.reinterpret()) {

	var programName: String?
		get() =
			gtk_about_dialog_get_program_name(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_program_name(aboutDialogPointer, value)

	var version: String?
		get() =
			gtk_about_dialog_get_version(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_version(aboutDialogPointer, value)

	var copyright: String?
		get() =
			gtk_about_dialog_get_copyright(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_copyright(aboutDialogPointer, value)

	var comments: String?
		get() =
			gtk_about_dialog_get_comments(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_comments(aboutDialogPointer, value)

	var license: String?
		get() =
			gtk_about_dialog_get_license(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_license(aboutDialogPointer, value)

	var wrapLicense: Boolean
		get() =
			Boolean.from(gtk_about_dialog_get_wrap_license(aboutDialogPointer))
		set(value) =
			gtk_about_dialog_set_wrap_license(
				aboutDialogPointer,
				value.gtkValue
			)

	var licenseType: License
		get() =
			License.valueOf(gtk_about_dialog_get_license_type(aboutDialogPointer))!!
		set(value) =
			gtk_about_dialog_set_license_type(aboutDialogPointer, value.gtk)

	var website: String?
		get() =
			gtk_about_dialog_get_website(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_website(aboutDialogPointer, value)

	var websiteLabel: String?
		get() =
			gtk_about_dialog_get_website_label(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_website_label(aboutDialogPointer, value)

	var authors: List<String>
		get() =
			gtk_about_dialog_get_authors(aboutDialogPointer).asStringList()
		set(value) {
			gtk_about_dialog_set_authors(
				aboutDialogPointer,
				value.toCStringArray()
			)
		}

	var artists: List<String>
		get() =
			gtk_about_dialog_get_artists(aboutDialogPointer).asStringList()
		set(value) =
			gtk_about_dialog_set_artists(
				aboutDialogPointer,
				value.toCStringArray()
			)

	var documenters: List<String>
		get() = gtk_about_dialog_get_documenters(aboutDialogPointer).asStringList()
		set(value) = gtk_about_dialog_set_documenters(
			aboutDialogPointer,
			value.toCStringArray()
		)

	var translatorCredits: String?
		get() =
			gtk_about_dialog_get_translator_credits(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_translator_credits(aboutDialogPointer, value)

	var logo: Any
		get() = TODO("gtk_about_dialog_get_logo")
		set(value) = TODO("gtk_about_dialog_set_logo")

	var logoIconName: String?
		get() =
			gtk_about_dialog_get_logo_icon_name(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_logo_icon_name(aboutDialogPointer, value)

	constructor() : this(gtk_about_dialog_new()!!.reinterpret())

	fun addCreditSection(sectionName: String, people: Array<String>) {
		TODO("gtk_about_dialog_add_credit_section")

		/*
		gtk_about_dialog_add_credit_section(
			aboutDialogPointer,
			sectionName,
			people.
		)
		*/
	}

	fun dialog() {

	}

	enum class License(val key: Int, internal val gtk: GtkLicense) {
		UNKNOWN(0, GTK_LICENSE_UNKNOWN),

		CUSTOM(1, GTK_LICENSE_CUSTOM),

		GPL_2_0(2, GTK_LICENSE_GPL_2_0),

		GPL_3_0(3, GTK_LICENSE_GPL_3_0),

		LGPL_2_1(4, GTK_LICENSE_LGPL_2_1),

		LGPL_3_0(5, GTK_LICENSE_LGPL_3_0),

		BSD(6, GTK_LICENSE_BSD),

		MIT_X11(7, GTK_LICENSE_MIT_X11),

		ARTISTIC(8, GTK_LICENSE_ARTISTIC),

		GPL_2_0_ONLY(9, GTK_LICENSE_GPL_2_0_ONLY),

		GPL_3_0_ONLY(10, GTK_LICENSE_GPL_3_0_ONLY),

		LGPL_2_1_ONLY(11, GTK_LICENSE_LGPL_2_1_ONLY),

		LGPL_3_0_ONLY(12, GTK_LICENSE_LGPL_3_0_ONLY),

		AGPL_3_0(13, GTK_LICENSE_AGPL_3_0),

		AGPL_3_0_ONLY(14, GTK_LICENSE_AGPL_3_0_ONLY),

		BSD_3(15, GTK_LICENSE_BSD_3),

		APACHE_2_0(16, GTK_LICENSE_APACHE_2_0),

		MPL_2_0(17, GTK_LICENSE_MPL_2_0);

		companion object {
			fun valueOf(key: Int) = values().find { it.key == key }

			internal fun valueOf(gtk: GtkLicense) =
				values().find { it.gtk == gtk }
		}

	}
}