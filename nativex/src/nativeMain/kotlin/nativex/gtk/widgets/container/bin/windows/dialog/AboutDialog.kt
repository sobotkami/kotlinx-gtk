package nativex.gtk.widgets.container.bin.windows.dialog

import gtk.*
import gtk.GtkLicense.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.async.ActivateLinkFunction
import nativex.async.activateLinkSignalManager
import nativex.gdk.Pixbuf
import nativex.gdk.Pixbuf.Companion.wrap
import nativex.glib.asSequence
import nativex.glib.bool
import nativex.glib.gtk
import nativex.glib.toNullTermCStringArray
import nativex.gobject.SignalManager
import nativex.gtk.widgets.container.bin.windows.Window

/**
 * kotlinx-gtk
 *
 * 08 / 03 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html">GtkAboutDialog</a>
 */
class AboutDialog(val aboutDialogPointer: CPointer<GtkAboutDialog>) : Dialog(aboutDialogPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-program-name">
	 *     gtk_about_dialog_get_program_name</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-program-name">
	 *     gtk_about_dialog_set_program_name</a>
	 */
	var programName: String?
		get() =
			gtk_about_dialog_get_program_name(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_program_name(aboutDialogPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-version">
	 *     gtk_about_dialog_get_version</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-version">
	 *     gtk_about_dialog_set_version</a>
	 */
	var version: String?
		get() =
			gtk_about_dialog_get_version(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_version(aboutDialogPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-copyright">
	 *     gtk_about_dialog_get_copyright</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-copyright">
	 *     gtk_about_dialog_set_copyright</a>
	 */
	var copyright: String?
		get() =
			gtk_about_dialog_get_copyright(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_copyright(aboutDialogPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-comments">
	 *     gtk_about_dialog_get_comments</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-comments">
	 *     gtk_about_dialog_set_comments</a>
	 */
	var comments: String?
		get() =
			gtk_about_dialog_get_comments(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_comments(aboutDialogPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-license">
	 *     gtk_about_dialog_get_license</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-license">
	 *     gtk_about_dialog_set_license</a>
	 */
	var license: String?
		get() =
			gtk_about_dialog_get_license(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_license(aboutDialogPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-wrap-license">
	 *     gtk_about_dialog_get_wrap_license</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-wrap-license">
	 *     gtk_about_dialog_set_wrap_license</a>
	 */
	var wrapLicense: Boolean
		get() =
			gtk_about_dialog_get_wrap_license(aboutDialogPointer).bool
		set(value) =
			gtk_about_dialog_set_wrap_license(
				aboutDialogPointer,
				value.gtk
			)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-license-type">
	 *     gtk_about_dialog_get_license_type</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-license-type">
	 *     gtk_about_dialog_set_license_type</a>
	 */
	var licenseType: License
		get() =
			License.valueOf(gtk_about_dialog_get_license_type(aboutDialogPointer))!!
		set(value) =
			gtk_about_dialog_set_license_type(aboutDialogPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-website">
	 *     gtk_about_dialog_get_website</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-website">
	 *     gtk_about_dialog_set_website</a>
	 */
	var website: String?
		get() =
			gtk_about_dialog_get_website(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_website(aboutDialogPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-website-label">
	 *     gtk_about_dialog_get_website_label</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-website-label">
	 *     gtk_about_dialog_set_website_label</a>
	 */
	var websiteLabel: String?
		get() =
			gtk_about_dialog_get_website_label(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_website_label(aboutDialogPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-authors">
	 *     gtk_about_dialog_get_authors</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-authors">
	 *     gtk_about_dialog_set_authors</a>
	 */
	var authors: List<String>
		get() =
			gtk_about_dialog_get_authors(aboutDialogPointer).asSequence().toList().map { it.toKString() }
		set(value) {
			gtk_about_dialog_set_authors(
				aboutDialogPointer,
				value.toNullTermCStringArray()
			)
		}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-artists">
	 *     gtk_about_dialog_get_artists</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-artists">
	 *     gtk_about_dialog_set_artists</a>
	 */
	var artists: List<String>
		get() =
			gtk_about_dialog_get_artists(aboutDialogPointer).asSequence().toList().map { it.toKString() }
		set(value) =
			gtk_about_dialog_set_artists(
				aboutDialogPointer,
				value.toNullTermCStringArray()
			)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-documenters">
	 *     gtk_about_dialog_get_documenters</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-documenters">
	 *     gtk_about_dialog_set_documenters</a>
	 */
	var documenters: List<String>
		get() = gtk_about_dialog_get_documenters(aboutDialogPointer).asSequence().toList().map { it.toKString() }
		set(value) = gtk_about_dialog_set_documenters(
			aboutDialogPointer,
			value.toNullTermCStringArray()
		)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-translator-credits">
	 *     gtk_about_dialog_get_translator_credits</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-translator-credits">
	 *     gtk_about_dialog_set_translator_credits</a>
	 */
	var translatorCredits: String?
		get() =
			gtk_about_dialog_get_translator_credits(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_translator_credits(aboutDialogPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-logo">
	 *     gtk_about_dialog_get_logo</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-logo">
	 *     gtk_about_dialog_set_logo</a>
	 */
	var logo: Pixbuf
		get() = gtk_about_dialog_get_logo(aboutDialogPointer)!!.wrap()
		set(value) = gtk_about_dialog_set_logo(aboutDialogPointer, value.pixbufPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-get-logo-icon-name">
	 *     gtk_about_dialog_get_logo_icon_name</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-set-logo-icon-name">
	 *     gtk_about_dialog_set_logo_icon_name</a>
	 */
	var logoIconName: String?
		get() =
			gtk_about_dialog_get_logo_icon_name(aboutDialogPointer)?.toKString()
		set(value) =
			gtk_about_dialog_set_logo_icon_name(aboutDialogPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-new">
	 *     gtk_about_dialog_new</a>
	 */
	constructor() : this(gtk_about_dialog_new()!!.reinterpret())

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#gtk-about-dialog-add-credit-section">
	 *     gtk_about_dialog_add_credit_section</a>
	 */
	fun addCreditSection(sectionName: String, vararg people: String) {
		gtk_about_dialog_add_credit_section(
			aboutDialogPointer,
			sectionName,
			people.toNullTermCStringArray()
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#GtkAboutDialog-activate-link">
	 *     activate-link</a>
	 */
	fun addOnActivateLinkCallback(action: ActivateLinkFunction): SignalManager =
		activateLinkSignalManager(aboutDialogPointer, action)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkAboutDialog.html#GtkLicense">GtkLicense</a>
	 */
	enum class License(val gtk: GtkLicense) {
		/**
		 * No license specified
		 */
		UNKNOWN(GTK_LICENSE_UNKNOWN),

		/**
		 * A license text is going to be specified by the developer
		 */
		CUSTOM(GTK_LICENSE_CUSTOM),

		/**
		 * The GNU General Public License, version 2.0 or later
		 */
		GPL_2_0(GTK_LICENSE_GPL_2_0),

		/**
		 * The GNU General Public License, version 3.0 or later
		 */
		GPL_3_0(GTK_LICENSE_GPL_3_0),

		/**
		 * The GNU Lesser General Public License, version 2.1 or later
		 */
		LGPL_2_1(GTK_LICENSE_LGPL_2_1),

		/**
		 * The GNU Lesser General Public License, version 3.0 or later
		 */
		LGPL_3_0(GTK_LICENSE_LGPL_3_0),

		/**
		 * The BSD standard license
		 */
		BSD(GTK_LICENSE_BSD),

		/**
		 * The MIT/X11 standard license
		 */
		MIT_X11(GTK_LICENSE_MIT_X11),

		/**
		 * The Artistic License, version 2.0
		 */
		ARTISTIC(GTK_LICENSE_ARTISTIC),

		/**
		 * The GNU General Public License, version 2.0 only.
		 *
		 * @since 3.12
		 */
		GPL_2_0_ONLY(GTK_LICENSE_GPL_2_0_ONLY),

		/**
		 * The GNU General Public License, version 3.0 only.
		 *
		 * @since 3.12
		 */
		GPL_3_0_ONLY(GTK_LICENSE_GPL_3_0_ONLY),

		/**
		 * The GNU Lesser General Public License, version 2.1 only.
		 *
		 * @since 3.12
		 */
		LGPL_2_1_ONLY(GTK_LICENSE_LGPL_2_1_ONLY),

		/**
		 * The GNU Lesser General Public License, version 3.0 only.
		 *
		 * @since 3.12
		 */
		LGPL_3_0_ONLY(GTK_LICENSE_LGPL_3_0_ONLY),

		/**
		 * The GNU Affero General Public License, version 3.0 or later.
		 *
		 * @since 3.22
		 */
		AGPL_3_0(GTK_LICENSE_AGPL_3_0),

		/**
		 * The GNU Affero General Public License, version 3.0 only.
		 *
		 * @since 3.22.27
		 */
		AGPL_3_0_ONLY(GTK_LICENSE_AGPL_3_0_ONLY),

		/**
		 * The 3-clause BSD licence.
		 * @since 3.24.20
		 */
		BSD_3(GTK_LICENSE_BSD_3),

		/**
		 * The Apache License, version 2.0.
		 *
		 * @since 3.24.20
		 */
		APACHE_2_0(GTK_LICENSE_APACHE_2_0),

		/**
		 * The Mozilla Public License, version 2.0.
		 *
		 * @since 3.24.20
		 */
		MPL_2_0(GTK_LICENSE_MPL_2_0);

		companion object {
			fun valueOf(gtk: GtkLicense) =
				values().find { it.gtk == gtk }
		}
	}

	companion object {
		@Deprecated("No VA", level = DeprecationLevel.HIDDEN)
		fun Window.showAboutDialog() {
		}
	}
}

