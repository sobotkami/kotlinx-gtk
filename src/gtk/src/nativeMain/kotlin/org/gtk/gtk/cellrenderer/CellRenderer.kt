package org.gtk.gtk.cellrenderer

import gtk.*
import gtk.GtkCellRendererAccelMode.GTK_CELL_RENDERER_ACCEL_MODE_GTK
import gtk.GtkCellRendererAccelMode.GTK_CELL_RENDERER_ACCEL_MODE_OTHER
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import org.gtk.async.staticCStringCallback
import org.gtk.glib.bool
import org.gtk.glib.gtk
import org.gtk.gobject.KGObject
import org.gtk.gobject.Signals
import org.gtk.gobject.addSignalCallback
import org.gtk.gtk.TreeModel

open class CellRenderer(
	val cellRendererPointer: CPointer<GtkCellRenderer>
) : KGObject(cellRendererPointer.reinterpret()) {


	companion object {
		inline fun CPointer<GtkCellRenderer>?.wrap() =
			this?.wrap()

		inline fun CPointer<GtkCellRenderer>.wrap() =
			CellRenderer(this)
	}


	class Accel : CellRenderer(gtk_cell_renderer_accel_new()!!) {
		enum class Mode(val gtk: GtkCellRendererAccelMode) {
			GTK(GTK_CELL_RENDERER_ACCEL_MODE_GTK),
			OTHER(GTK_CELL_RENDERER_ACCEL_MODE_OTHER);

			companion object {
				fun valueOf(gtk: GtkCellRendererAccelMode) = values().find { it.gtk == gtk }
			}
		}

		fun addOnAccelClearedCallback() {
			TODO("accel-cleared")
		}


		data class AccelEditedData(
			val path: String,
			val key: UInt,
			val hardwareKeycode: UInt
		)

		fun addOnAccelEditedCallback() {
			TODO("accel-edited")
		}
	}

	class Combo : CellRenderer(gtk_cell_renderer_combo_new()!!) {
		data class ChangedData(
			val path: String,
			val newIter: TreeModel.TreeIter
		)

		fun addOnChangedCallback() {
			TODO("changed")
		}
	}

	class Pixbuf : CellRenderer(gtk_cell_renderer_pixbuf_new()!!)

	class Progress : CellRenderer(gtk_cell_renderer_progress_new()!!)

	class Spin : CellRenderer(gtk_cell_renderer_spin_new()!!)

	class Text : CellRenderer(gtk_cell_renderer_text_new()!!) {
		fun setFixedHeightFromFont(numberOfRows: Int) {
			gtk_cell_renderer_text_set_fixed_height_from_font(cellRendererPointer.reinterpret(), numberOfRows)
		}

		data class EditedData(
			val path: String,
			val newText: String
		)

		fun addOnEditedCallback() {
			TODO("edited")
		}
	}

	class Toggle : CellRenderer(gtk_cell_renderer_toggle_new()!!) {
		val cellRendererTogglePointer: CPointer<GtkCellRendererToggle> by lazy {
			cellRendererPointer.reinterpret()
		}

		var radio: Boolean
			get() = gtk_cell_renderer_toggle_get_radio(cellRendererTogglePointer).bool
			set(value) = gtk_cell_renderer_toggle_set_radio(cellRendererTogglePointer, value.gtk)


		var active: Boolean
			get() = gtk_cell_renderer_toggle_get_active(cellRendererTogglePointer).bool
			set(value) = gtk_cell_renderer_toggle_set_active(cellRendererTogglePointer, value.gtk)

		var activatable: Boolean
			get() = gtk_cell_renderer_toggle_get_activatable(cellRendererTogglePointer).bool
			set(value) = gtk_cell_renderer_toggle_set_activatable(cellRendererTogglePointer, value.gtk)


		fun addOnToggledCallback(action: (String) -> Unit) =
			addSignalCallback(Signals.TOGGLED, action, staticCStringCallback)
	}

	class Spinner : CellRenderer(gtk_cell_renderer_spinner_new()!!)

}