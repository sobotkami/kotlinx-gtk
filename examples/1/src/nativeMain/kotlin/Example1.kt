import org.gtk.dsl.gio.onCreateUI
import org.gtk.dsl.gtk.*
import org.gtk.gtk.widgets.Widget

/*
<a href="https://docs.gtk.org/gtk4/getting_started.html#hello-world-in-c">Hello World in C</a>
 */

fun main() {
	application("org.gtk.example") {
		onCreateUI {
			applicationWindow {
				title = "Window"
				defaultSize = 200 x 200
				button("Hello World") {
					horizontalAlign = Widget.Align.CENTER
					verticalAlign = Widget.Align.CENTER
					onClicked {
						println("Hello World")
					}
					onClicked {
						this@applicationWindow.destroy()
					}
				}
			}.show()
		}
	}
}