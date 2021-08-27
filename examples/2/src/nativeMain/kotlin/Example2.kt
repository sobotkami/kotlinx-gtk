import org.gtk.dsl.gio.onCreateUI
import org.gtk.dsl.gtk.*

/*
<a href="https://docs.gtk.org/gtk4/getting_started.html#packing">Packing</a>
 */
fun printHello() {
	println("Hello World")
}

fun main() {
	application("org.gtk.example") {
		onCreateUI {
			/* create a new window, and set its title */
			applicationWindow {
				title = "Window"
				/* Here we construct the container that is going pack our buttons */
				/* Pack the container in the window */
				grid {

					/*
					Place the first button in the grid cell (0, 0), and make it fill
                    just 1 cell horizontally and vertically (ie no spanning)
                    */
					button("Button 1", 0, 0, 1, 1) {
						onClicked(::printHello)
					}

					/*
					Place the second button in the grid cell (1, 0), and make it fill
					just 1 cell horizontally and vertically (ie no spanning)
					*/
					button("Button 2", 1, 0, 1, 1) {
						onClicked(::printHello)
					}

					/*
					Place the Quit button in the grid cell (0, 1), and make it
					span 2 columns.
					*/
					button("Quit", 0, 1, 2, 1) {
						onClicked(this@applicationWindow::destroy)
					}
				}
			}.show()
		}
	}
}