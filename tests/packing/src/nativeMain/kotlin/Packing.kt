import org.gtk.dsl.gio.onCreateUI
import org.gtk.dsl.gtk.*

/**
 * this correlates to the GTK3 packing tutorial
 */
fun main() {
	val printHello = {
		println("Hello World!")
	}
	application("org.gtk.example") {
		onCreateUI {
			applicationWindow {
				title = "Window"
				grid {
					button("Button 1", 0, 0, 1, 1) {
						onClicked { printHello() }
					}
					button("Button 2", 1, 0, 1, 1) {
						onClicked { printHello() }
					}
					button("Quit", 0, 1, 2, 1) {
						onClicked {
							// Be careful of the context when you invoke destroy
							this@applicationWindow.close()
						}
					}
				}
			}.show()
		}
	}
}