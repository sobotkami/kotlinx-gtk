import nativex.gio.dsl.onCreateUI
import nativex.glib.KGType
import nativex.glib.KGValue
import nativex.gtk.ListStore
import nativex.gtk.TreeModel
import nativex.gtk.dsl.application
import nativex.gtk.dsl.applicationWindow
import nativex.gtk.dsl.comboBox
import nativex.gtk.dsl.grid

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


	val iter = TreeModel.TreeIter()
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

fun main() {
	application("com.github.doomsdayrs.test") {
		onCreateUI {
			applicationWindow {
				grid {
					comboBox(
						createIconStore(),
						0,
						0,
						1,
						1
					) {
					}
				}
			}.showAll()
		}
	}
}