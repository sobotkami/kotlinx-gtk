package nativex.gtk.widgets.container.bin.combobox

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.cinterop.toKString
import nativex.glib.use

/**
 * kotlin-gtk
 *
 * 03 / 06 / 2021
 *
 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBoxText.html">GtkComboBoxText</a>
 */
class ComboBoxText(
	val comboBoxTextPointer: CPointer<GtkComboBoxText>
) : ComboBox(comboBoxTextPointer.reinterpret()) {

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBoxText.html#gtk-combo-box-text-new">
	 *     gtk_combo_box_text_new</a>
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBoxText.html#gtk-combo-box-text-new-with-entry">
	 *     gtk_combo_box_text_new_with_entry</a>
	 */
	constructor(withEntry: Boolean = false) : this(
		if (withEntry) {
			gtk_combo_box_text_new_with_entry()
		} else {
			gtk_combo_box_text_new()
		}!!.reinterpret()
	)

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBoxText.html#gtk-combo-box-text-append">
	 *     gtk_combo_box_text_append</a>
	 */
	fun append(id: String?, text: String) {
		gtk_combo_box_text_append(comboBoxTextPointer, id, text)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBoxText.html#gtk-combo-box-text-prepend">
	 *     gtk_combo_box_text_prepend</a>
	 */
	fun prepend(id: String?, text: String) {
		gtk_combo_box_text_prepend(comboBoxTextPointer, id, text)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBoxText.html#gtk-combo-box-text-insert">
	 *     gtk_combo_box_text_insert</a>
	 */
	fun insert(position: Int, id: String?, text: String) {
		gtk_combo_box_text_insert(comboBoxTextPointer, position, id, text)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBoxText.html#gtk-combo-box-text-append-text">
	 *     gtk_combo_box_text_append_text</a>
	 */
	fun appendText(text: String) {
		gtk_combo_box_text_append_text(comboBoxTextPointer, text)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBoxText.html#gtk-combo-box-text-prepend-text">
	 *     gtk_combo_box_text_prepend_text</a>
	 */
	fun prependText(text: String) {
		gtk_combo_box_text_prepend_text(comboBoxTextPointer, text)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBoxText.html#gtk-combo-box-text-insert-text">
	 *     gtk_combo_box_text_insert_text</a>
	 */
	fun insertText(position: Int, text: String) {
		gtk_combo_box_text_insert_text(comboBoxTextPointer, position, text)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBoxText.html#gtk-combo-box-text-remove">
	 *     gtk_combo_box_text_remove</a>
	 */
	fun remove(position: Int) {
		gtk_combo_box_text_remove(comboBoxTextPointer, position)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBoxText.html#gtk-combo-box-text-remove-all">
	 *     gtk_combo_box_text_remove_all</a>
	 */
	fun removeAll() {
		gtk_combo_box_text_remove_all(comboBoxTextPointer)
	}

	/**
	 * @see <a href="https://developer.gnome.org/gtk3/stable/GtkComboBoxText.html#gtk-combo-box-text-get-active-text">
	 *     gtk_combo_box_text_get_active_text</a>
	 */
	val activeText: String?
		get() = gtk_combo_box_text_get_active_text(comboBoxTextPointer)?.use { it.toKString() }
}