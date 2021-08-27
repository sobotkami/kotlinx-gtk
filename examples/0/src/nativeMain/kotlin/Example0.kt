import org.gtk.dsl.gio.onCreateUI
import org.gtk.dsl.gtk.application
import org.gtk.dsl.gtk.applicationWindow
import org.gtk.dsl.gtk.x

/*
<a href="https://docs.gtk.org/gtk4/getting_started.html#basics">Basics</a>
 */

fun main() {
	application("org.gtk.example") {
		onCreateUI {
			applicationWindow {
				title = "Window"
				defaultSize = 200 x 200
			}.show()
		}
	}
}