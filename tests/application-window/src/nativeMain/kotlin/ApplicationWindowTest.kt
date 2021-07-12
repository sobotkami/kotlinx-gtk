import nativex.gio.dsl.*
import nativex.gtk.common.enums.Orientation
import nativex.gtk.dsl.*

/**
 * this correlates to the GTK3 packing tutorial
 */
fun main() {
	val result = application("org.gtk.example") {
		onCreateUI {
			menuBar {
				submenu("File", menu {
					item("Open")
					submenu("Recent", menu {
						item("A")
						item("B")
						item("C")
					})

					item("Quit")
				})

				item("Edit")
				item("View")
				item("Help")
			}

			applicationWindow {
				title = "Kotlin/Native Gtk Application-Window test"
				defaultSize = 600 x 200

				addOnActivateDefaultCallback {
					println("Activated default.")
				}
				addOnActivateFocusCallback {
					println("Activated focus.")
				}
				addOnEnableDebuggingCallback {
					println("New debugging state: $it")
				}
				addOnKeysChangedCallback {
					println("Keys changed.")
				}
				addOnSetFocusCallback {
					println("Focusing onto: $it(${it?.name})")
				}


				box(Orientation.HORIZONTAL, 16) {
					start {
						box(Orientation.VERTICAL, 8) {
							button("Toggle menu bar") {
								this@applicationWindow.showMenuBar = !this@applicationWindow.showMenuBar
							}
						}
					}

					end {
						box(Orientation.VERTICAL, 8) {

						}
					}
				}
			}.showAll()
		}
	}
	println("Final status: $result")
}