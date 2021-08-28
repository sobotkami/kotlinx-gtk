import org.gtk.gobject.KGType
import org.gtk.gobject.KGValue
import org.gtk.gtk.CellLayout
import org.gtk.gtk.ListStore
import org.gtk.gtk.TreeModel
import org.gtk.gtk.TreeModel.TreeIter
import org.gtk.gtk.TreeStore
import org.gtk.gtk.cellrenderer.CellRenderer
import org.gtk.gtk.widgets.Widget
import org.gtk.gtk.widgets.combobox.ComboBoxText
import org.gtk.gtk.widgets.windows.Window

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
	val path: TreeModel.TreePath = treeModel.getPath(iter)
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

	return store
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

private var window: Window? = null

fun doCombobox(doWidget: Widget): Widget {
	return window!!
}