package nativex.gtk.widgets.container.bin.combobox

import gtk.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret

class ComboBoxText(
	 val comboBoxTextPointer: CPointer<GtkComboBoxText>
) :ComboBox(comboBoxTextPointer.reinterpret()){
	constructor(withEntry: Boolean = false) : this(
		if (withEntry) {
			gtk_combo_box_text_new_with_entry()
		} else {
			gtk_combo_box_text_new()
		}!!.reinterpret()
	)

	fun append(id: String?, text: String) {
		gtk_combo_box_text_append(comboBoxTextPointer, id, text)
	}

	fun appendText(text: String){
		gtk_combo_box_text_append_text(comboBoxTextPointer, text)

	}
}