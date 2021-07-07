package nativex.gtk

import glib.gpointer
import gtk.*
import kotlinx.cinterop.*
import nativex.glib.bool
import nativex.glib.gtk
import nativex.glib.use
import nativex.gobject.staticDestroyStableRefFunction
import nativex.pango.FontDescription
import nativex.pango.FontDescription.Companion.wrap
import nativex.pango.FontFace
import nativex.pango.FontFace.Companion.wrap
import nativex.pango.FontFamily
import nativex.pango.FontFamily.Companion.wrap
import nativex.pango.FontMap
import nativex.pango.FontMap.Companion.wrap
import pango.PangoFontFace
import pango.PangoFontFamily

/**
 * kotlinx-gtk
 *
 * 07 / 07 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html">GtkFontChooser</a>
 */
interface FontChooser {
	val fontChooserPointer: CPointer<GtkFontChooser>

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-get-font-family">gtk_font_chooser_get_font_family</a>
	 */
	val fontFamily: FontFamily?
		get() = gtk_font_chooser_get_font_family(fontChooserPointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-get-font-face">gtk_font_chooser_get_font_face</a>
	 */
	val fontFace: FontFace?
		get() = gtk_font_chooser_get_font_face(fontChooserPointer).wrap()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-get-font-size">gtk_font_chooser_get_font_size</a>
	 */
	val fontSize: Int
		get() = gtk_font_chooser_get_font_size(fontChooserPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-get-font">gtk_font_chooser_get_font</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-set-font">gtk_font_chooser_set_font</a>
	 */
	var font: String?
		get() = gtk_font_chooser_get_font(fontChooserPointer)?.use { it.toKString() }
		set(value) = gtk_font_chooser_set_font(fontChooserPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-get-font-desc">gtk_font_chooser_get_font_desc</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-set-font-desc">gtk_font_chooser_set_font_desc</a>
	 */
	var fontDescription: FontDescription?
		get() = gtk_font_chooser_get_font_desc(fontChooserPointer).wrap()
		set(value) = gtk_font_chooser_set_font_desc(fontChooserPointer, value?.fontDescriptionPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-get-preview-text">gtk_font_chooser_get_preview_text</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-set-preview-text">gtk_font_chooser_set_preview_text</a>
	 */
	var previewText: String
		get() = gtk_font_chooser_get_preview_text(fontChooserPointer)!!.toKString()
		set(value) = gtk_font_chooser_set_preview_text(fontChooserPointer, value)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-get-show-preview-entry">gtk_font_chooser_get_show_preview_entry</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-set-show-preview-entry">gtk_font_chooser_set_show_preview_entry</a>
	 */
	var showPreviewEntry: Boolean
		get() = gtk_font_chooser_get_show_preview_entry(fontChooserPointer).bool
		set(value) = gtk_font_chooser_set_show_preview_entry(fontChooserPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-set-filter-func">gtk_font_chooser_set_filter_func</a>
	 */
	fun setFilterFunction(action: FontFilterFunction) {
		gtk_font_chooser_set_filter_func(
			fontChooserPointer,
			staticFontFilterFunction,
			StableRef.create(action).asCPointer(),
			staticDestroyStableRefFunction
		)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-get-font-map">gtk_font_chooser_get_font_map</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-set-font-map">gtk_font_chooser_set_font_map</a>
	 */
	var fontChooserFontMap: FontMap?
		get() = gtk_font_chooser_get_font_map(fontChooserPointer).wrap()
		set(value) = gtk_font_chooser_set_font_map(fontChooserPointer, value?.fontMapPointer)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-get-level">gtk_font_chooser_get_level</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-set-level">gtk_font_chooser_set_level</a>
	 */
	var level: Level
		get() = Level.valueOf(gtk_font_chooser_get_level(fontChooserPointer))!!
		set(value) = gtk_font_chooser_set_level(fontChooserPointer, value.gtk)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-get-font-features">gtk_font_chooser_get_font_features</a>
	 */
	val fontFeatures: String
		get() = gtk_font_chooser_get_font_features(fontChooserPointer)!!.toKString()

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-get-language">gtk_font_chooser_get_language</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#gtk-font-chooser-set-language">gtk_font_chooser_set_language</a>
	 */
	var language: String
		get() = gtk_font_chooser_get_language(fontChooserPointer)!!.toKString()
		set(value) = gtk_font_chooser_set_language(fontChooserPointer, value)

	/**
	 * Warning while using this class. The enum ordinals do not match the C version.
	 *
	 * There was no documentation on [GtkFontChooserLevel], This is made on a guess.
	 */
	enum class Level(val gtk: GtkFontChooserLevel) {
		FAMILY(GTK_FONT_CHOOSER_LEVEL_FAMILY),
		FEATURES(GTK_FONT_CHOOSER_LEVEL_FEATURES),
		SIZE(GTK_FONT_CHOOSER_LEVEL_SIZE),
		STYLE(GTK_FONT_CHOOSER_LEVEL_STYLE),
		VARIATIONS(GTK_FONT_CHOOSER_LEVEL_VARIATIONS);

		companion object {
			fun valueOf(gtk: GtkFontChooserLevel) = values().find { it.gtk == gtk }
		}
	}

	companion object {
		private val staticFontFilterFunction: GtkFontFilterFunc =
			staticCFunction { family: CPointer<PangoFontFamily>?, face: CPointer<PangoFontFace>?, data: gpointer? ->
				data?.asStableRef<FontFilterFunction>()?.get()?.invoke(family!!.wrap(), face!!.wrap()).gtk
			}
	}
}

/**
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkFontChooser.html#GtkFontFilterFunc">GtkFontFilterFunc</a>
 */
typealias FontFilterFunction = (family: FontFamily, face: FontFace) -> Boolean