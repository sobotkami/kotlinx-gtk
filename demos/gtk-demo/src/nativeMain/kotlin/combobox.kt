import nativex.glib.KGBinding
import nativex.glib.KGType
import nativex.glib.KGValue
import nativex.gtk.CellLayout
import nativex.gtk.ListStore
import nativex.gtk.TreeModel
import nativex.gtk.TreeModel.Companion.asKObject
import nativex.gtk.TreeModel.TreeIter
import nativex.gtk.TreeModel.TreePath
import nativex.gtk.TreeStore
import nativex.gtk.cellrenderer.CellRenderer
import nativex.gtk.common.enums.Orientation
import nativex.gtk.widgets.Widget
import nativex.gtk.widgets.container.bin.combobox.ComboBox
import nativex.gtk.widgets.container.bin.combobox.ComboBoxText
import nativex.gtk.widgets.container.bin.frame.Frame
import nativex.gtk.widgets.container.bin.windows.Window
import nativex.gtk.widgets.container.box.Box
import nativex.gtk.widgets.entry.Entry
import nativex.use
import kotlin.test.assertNotNull

const val ICON_NAME_COL = 0
const val TEXT_COL = 1

fun createIconStore(): TreeModel {
	val iconNamesToLabels = arrayOf(
		"dialog-warning" to "Warning",
		"process-stop" to "Stop",
		"document-new" to "New",
		"edit-clear" to "Clear",
		null to null,
		"document-open" to "Open"
	)


	val iter = TreeIter()
	val store = ListStore(KGType.STRING, KGType.STRING)

	for ((iconName, label) in iconNamesToLabels) {
		println("doing $iconName")
		if (iconName != null) {
			store.append(iter)
			store.setValue(iter, ICON_NAME_COL, KGValue(iconName))
			store.setValue(iter, TEXT_COL, KGValue(label!!))
		} else {
			store.append(iter)
			store.setValue(iter, ICON_NAME_COL, null)
			store.setValue(iter, TEXT_COL, KGValue("separator"))
		}
	}

	return store.asTreeModel()
}

fun setSensitive(
	cellLayout: CellLayout,
	cell: CellRenderer,
	treeModel: TreeModel,
	iter: TreeIter
) {
	val path: TreePath = treeModel.getPath(iter)
	val indices: Sequence<Int> = path.indices
	val sensitive: Boolean = indices.first() != 1
	path.free()
	cell.set("sensitive", sensitive)
}

fun isSeparator(model: TreeModel, iter: TreeIter): Boolean {
	val path = model.getPath(iter)
	val result = path.indices.first() == 4
	path.free()
	return result
}

fun createCapitalStore(): TreeModel {
	val capitals = arrayOf(
		"A - B" to null,
		null to "Albany",
		null to "Annapolis",
		null to "Atlanta",
		null to "Augusta",
		null to "Austin",
		null to "Baton Rouge",
		null to "Bismarck",
		null to "Boise",
		null to "Boston",
		"C - D" to null,
		null to "Carson City",
		null to "Charleston",
		null to "Cheyenne",
		null to "Columbia",
		null to "Columbus",
		null to "Concord",
		null to "Denver",
		null to "Des Moines",
		null to "Dover",
		"E - J" to null,
		null to "Frankfort",
		null to "Harrisburg",
		null to "Hartford",
		null to "Helena",
		null to "Honolulu",
		null to "Indianapolis",
		null to "Jackson",
		null to "Jefferson City",
		null to "Juneau",
		"K - O" to null,
		null to "Lansing",
		null to "Lincoln",
		null to "Little Rock",
		null to "Madison",
		null to "Montgomery",
		null to "Montpelier",
		null to "Nashville",
		null to "Oklahoma City",
		null to "Olympia",
		"P - S" to null,
		null to "Phoenix",
		null to "Pierre",
		null to "Providence",
		null to "Raleigh",
		null to "Richmond",
		null to "Sacramento",
		null to "Salem",
		null to "Salt Lake City",
		null to "Santa Fe",
		null to "Springfield",
		null to "St. Paul",
		"T - Z" to null,
		null to "Tallahassee",
		null to "Topeka",
		null to "Trenton",
		null to null
	)

	val iter = TreeIter()
	val iter2 = TreeIter()
	val store = TreeStore(KGType.STRING)

	for (capital in capitals) {
		if (capital.first != null) {
			store.append(iter, null)
			store.setValue(iter, 0, KGValue(capital.first!!))
		} else if (capital.second != null) {
			store.append(iter, iter2)
			store.setValue(iter2, 0, KGValue(capital.second!!))
		}
	}

	return store.asTreeModel()
}

fun isCapitalSensitive(
	cellLayout: CellLayout,
	cell: CellRenderer,
	treeModel: TreeModel,
	iter: TreeIter
) {
	val sensitive = !treeModel.iterHasChild(iter)
	cell.set("sensitive", sensitive)
}

fun fillComboEntry(combo: ComboBoxText) {
	combo.appendText("One")
	combo.appendText("Two")
	combo.appendText("2\\302\\275")
	combo.appendText("Three")
}

private var window :Window? = null

fun doCombobox(doWidget: Widget): Widget {

	if (window==null){
		window = Window(Window.Type.TOP_LEVEL)

		window?.windowScreen = doWidget.screen
		window?.title = "Combo Boxes"

		// TODO Destroy signal

		window?.borderWidth = 10u

		val vbox = Box(Orientation.VERTICAL, 2)
		window?.add(vbox)

		var frame = Frame("Items with icons")
		vbox.packStart(frame, expand = false, fill = false, padding = 0u)

		var box = Box(Orientation.VERTICAL, 0)
		box.borderWidth = 5u
		frame.add(box)

		var model = createIconStore()
		var combo = ComboBox(model)
		model.asKObject()?.unref()
		box.add(combo)

		var renderer: CellRenderer = CellRenderer.Pixbuf()
		combo.apply {
			packStart(renderer, false)
			addAttribute(renderer, "icon-name", ICON_NAME_COL)
			setCellDataFunc(renderer, ::setSensitive)
		}


		renderer = CellRenderer.Text()
		combo.apply {
			packStart(renderer, true)
			addAttribute(renderer, "text", TEXT_COL)
			setCellDataFunc(renderer, ::setSensitive)
			setRowSeparatorFunc(::isSeparator)
			combo.active = 0
		}

		// A combobox demonstrating trees
		frame = Frame("Where are we ?")
		vbox.packStart(frame, false, fill = false, padding = 0u)

		box = Box(Orientation.VERTICAL, 0)
		box.borderWidth = 5u
		frame.add(box)

		model = createCapitalStore()
		combo = ComboBox(model)
		model.asKObject()?.unref()
		box.add(combo)

		renderer = CellRenderer.Text()
		combo.apply {
			packStart(renderer, true)
			addAttribute(renderer, "text", 0)
			setCellDataFunc(renderer, ::isCapitalSensitive)
		}

		TreePath(0, 8, -1).use {
			combo.setActiveIter(model.getIter(it))
		}

		// A ComboBoxEntry with validation
		frame = Frame("Editable")
		vbox.packStart(frame, expand = false, fill = false, padding = 0u)

		box = Box(Orientation.VERTICAL, 0)
		box.borderWidth = 5u
		frame.add(box)

		combo = ComboBoxText(true)
		fillComboEntry(combo)
		box.add(combo)

		// ENTRY
		// MASK ENTRY

		// REMOVE
		// ADD

		/// A combobox with string IDs
		frame = Frame("String IDs")
		vbox.packStart(frame, expand = false, fill = false, padding = 0u)

		box = Box(Orientation.VERTICAL, 0)
		box.borderWidth = 5u
		frame.add(box)

		combo = ComboBoxText().apply {
			append("never", "Not visible")
			append("when-active", "Visible when active")
			append("always", "Always visible")
			box.add(this)
		}

		val entry = Entry()
		entry.asKGBinding()?.bind("active-id", entry, "text", KGBinding.Flags.BIDIRECTIONAL)
		box.add(entry)
	}

	if (!window!!.visible) {
		window!!.showAll()
	} else window!!.destroy()

	return window!!
}