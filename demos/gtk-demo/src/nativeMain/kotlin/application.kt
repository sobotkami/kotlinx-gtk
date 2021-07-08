import nativex.gio.Application
import nativex.gio.SimpleAction
import nativex.gio.dsl.onCreateUI
import nativex.gtk.dsl.application
import nativex.gtk.dsl.applicationWindow
import nativex.gtk.dsl.button
import nativex.gtk.dsl.onClicked
import nativex.gtk.widgets.container.bin.windows.dialog.Dialog
import nativex.gtk.widgets.container.bin.windows.dialog.MessageDialog

/*
 * kotlinx-gtk
 *
 * 08 / 07 / 2021
 *
 * @see <a href="https://gitlab.gnome.org/GNOME/gtk/-/blob/gtk-3-24/demos/gtk-demo/application.c">application.c</a>
 */
fun createWindow(app: Application, contents: String) {

}

fun showActionDialog(action: SimpleAction) {
	val name = action.name
	val dialog = MessageDialog(
		parent = null,
		flags = Dialog.Flags.DESTROY_WITH_PARENT,
		messageType = MessageDialog.MessageType.INFO,
		buttonsType = MessageDialog.ButtonsType.CLOSE,
		withMarkup = false,
		"You activated action: \"${name}\""
	)
	dialog.addOnResponseCallback {
		dialog.destroy()
	}
	dialog.show()
}


fun main() {
	val result = application("org.gtk.Demo2", Application.Flags.HANDLES_OPEN) {
		onCreateUI {
			applicationWindow {
				button("Press") {
					onClicked {
						showActionDialog(SimpleAction("I am an action"))
					}
				}
			}.showAll()
		}
	}
	println("Result: $result")
}