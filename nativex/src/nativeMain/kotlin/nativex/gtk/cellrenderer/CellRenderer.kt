package nativex.gtk.cellrenderer

import gtk.*
import gtk.GtkCellRendererAccelMode.*
import kotlinx.cinterop.CPointer
import kotlinx.cinterop.reinterpret
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import nativex.async.callbackSignalFlow
import nativex.async.signalFlow
import nativex.async.staticCStringCallback
import nativex.gdk.Window
import nativex.gio.KObject
import nativex.gtk.Signals
import nativex.gtk.TreeModel
import nativex.gtk.bool
import nativex.gtk.gtk

open class CellRenderer(
	 val cellRendererPointer: CPointer<GtkCellRenderer>
) : KObject(cellRendererPointer.reinterpret()) {


	companion object {
		 inline fun CPointer<GtkCellRenderer>?.wrap() =
			this?.wrap()

		 inline fun CPointer<GtkCellRenderer>.wrap() =
			CellRenderer(this)
	}


	class Accel : CellRenderer(gtk_cell_renderer_accel_new()!!) {
		enum class Mode(val key: Int,  val gtk: GtkCellRendererAccelMode) {
			GTK(0, GTK_CELL_RENDERER_ACCEL_MODE_GTK),
			OTHER(1, GTK_CELL_RENDERER_ACCEL_MODE_OTHER),
			MODIFIER_TAP(2, GTK_CELL_RENDERER_ACCEL_MODE_MODIFIER_TAP);
		}

		val accelClearedSignal: Flow<String>
			get() = TODO("accel-cleared")


		data class AccelEditedData(
			val path: String,
			val key: UInt,
			val mods: Window.ModifierType,
			val hardwareKeycode: UInt
		)

		val accelEditedSignal: Flow<AccelEditedData>
			get() = TODO("accel-edited")
	}

	class Combo : CellRenderer(gtk_cell_renderer_combo_new()!!) {
		data class ChangedData(
			val path: String,
			val newIter: TreeModel.TreeIter
		)

		val changedSignal: Flow<ChangedData>
			get() = TODO("changed")
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

		val editedSignal: Flow<EditedData>
			get() = TODO("edited")
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


		@ExperimentalCoroutinesApi
		val toggledSignal: Flow<String> by signalFlow(Signals.TOGGLED, staticCStringCallback)
	}

	class Spinner : CellRenderer(gtk_cell_renderer_spinner_new()!!)

}