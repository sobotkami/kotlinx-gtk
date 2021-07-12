import nativex.gio.dsl.onCreateUI
import nativex.gtk.dsl.application
import nativex.gtk.dsl.applicationWindow
import nativex.gtk.dsl.comboBoxText
import nativex.gtk.dsl.grid

fun main() {
	application("com.github.doomsdayrs.test") {
		onCreateUI {
			applicationWindow {
				grid {
					comboBoxText(
						0,
						0,
						1,
						1
					) {
						appendText("A")
						appendText("B")
						appendText("C")
						appendText("D")
					}
				}
			}.showAll()
		}
	}
}