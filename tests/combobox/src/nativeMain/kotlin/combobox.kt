import nativex.gio.dsl.onCreateUI
import org.gtk.dsl.gtk.application
import org.gtk.dsl.gtk.applicationWindow
import org.gtk.dsl.gtk.comboBoxText
import org.gtk.dsl.gtk.grid

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