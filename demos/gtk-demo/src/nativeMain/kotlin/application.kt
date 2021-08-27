import gobject.g_object_get_data
import gobject.g_object_ref
import gobject.g_object_set_data_full
import kotlinx.cinterop.reinterpret
import org.gtk.dsl.gio.onCreateUI
import org.gtk.dsl.gtk.*
import org.gtk.gio.MenuModel
import org.gtk.gio.SimpleAction
import org.gtk.glib.KGError
import org.gtk.glib.Variant
import org.gtk.gobject.KGObject.Companion.staticUnrefFunction
import org.gtk.gtk.*
import org.gtk.gtk.widgets.Widget
import org.gtk.gtk.widgets.box.InfoBar
import org.gtk.gtk.widgets.misc.label.Label
import org.gtk.gtk.widgets.windows.ApplicationWindow
import org.gtk.gtk.widgets.windows.dialog.Dialog
import org.gtk.gtk.widgets.windows.dialog.MessageDialog

/*
 * kotlinx-gtk
 *
 * 08 / 07 / 2021
 *
 * @see <a href="https://gitlab.gnome.org/GNOME/gtk/-/blob/gtk-3-24/demos/gtk-demo/application.c">application.c</a>
 */
fun createWindow(app: Application, contents: String?) {
	TODO("Finish")
}

fun showActionDialog(action: SimpleAction) {
	val name = action.name
	val dialog = MessageDialog(
		parent = null,
		flags = Dialog.Flags.DESTROY_WITH_PARENT,
		messageType = MessageDialog.MessageType.INFO,
		buttonsType = MessageDialog.ButtonsType.CLOSE,
		"You activated action: \"${name}\"",
		withMarkup = false
	)
	dialog.addOnResponseCallback {
		dialog.close()
	}
	dialog.show()
}

class DemoApplicationWindow(
	val parentInstance: ApplicationWindow,
	val message: Label,
	val infoBar: InfoBar,
	val status: Widget,
	val menutool: Widget,
	val toolmenu: MenuModel,
	val buffer: TextBuffer,
	val width: InfoBar,
	val height: InfoBar,
	val maximized: Boolean,
	val fullscreen: Boolean
)

fun showActionInfoBar(window: DemoApplicationWindow, action: SimpleAction, paramater: Variant) {
	val name = action.simpleActionPointer
	val value = paramater.string
	window.message.text = "You activated radio action: \"%${name}\".\nCurrentValue: %${value}"
	window.infoBar.show()
}

fun activateAction(action: SimpleAction) {
	showActionDialog(action)
}

fun activateNew(application: Application) {
	createWindow(application, null)
}

fun openResponse(dialog: NativeDialog, responseId: Int, fileChooser: FileChooserNative) {

	val app = Application(g_object_get_data(fileChooser.pointer, "app")!!.reinterpret())

	var error: KGError? = null

	if (Dialog.ResponseType.ACCEPT.isId(responseId)) {
		val file = fileChooser.file
		val contents =
			try {
				file.loadContents()
			} catch (e: KGError) {
				error = e
				null
			}
		if (contents != null) {
			createWindow(app, contents)
		} else {
			val messageDialog = MessageDialog(
				null,
				Dialog.Flags.DESTROY_WITH_PARENT,
				MessageDialog.MessageType.ERROR,
				MessageDialog.ButtonsType.CLOSE,
				"Error loading file: \"$error"
			)
			messageDialog.addOnResponseCallback { messageDialog.close() }
			messageDialog.show()
			error?.free()
		}
	}
	dialog.destroy()
	dialog.unref()
}

fun activateOpen(app: Application) {
	val native = FileChooserNative(
		"Open File",
		null,
		FileChooser.Action.ACTION_OPEN,
		"_Open",
		"_Cancel"
	)
	g_object_set_data_full(native.pointer, "app", g_object_ref(app.pointer), staticUnrefFunction)

	// TODO Continue from here
}


fun main() {
	val result = application("org.gtk.Demo2", org.gtk.gio.Application.Flags.HANDLES_OPEN) {
		onCreateUI {
			applicationWindow {
				frame {
					button("Press") {
						onClicked {
							showActionDialog(SimpleAction("I am an action"))
						}
					}
				}

			}.show()
		}
	}
	println("Result: $result")
}