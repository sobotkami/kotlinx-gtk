import kotlinx.coroutines.newSingleThreadContext
import kotlinx.gtk.*
import kotlin.test.Test

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
class Main {

	@Test
	fun main() {
		val finalStatus =
			application("com.github.doomsdayrs.lib.kotlinx-gtk.test") {
				println("Creating application")
				onCreateUI {
					println("Creating UI")
					applicationWindow {
						println("Setting Window Title")
						title = "Window"

						println("Setting Window Default Size")
						defaultSize = 200 to 200

						println("Adding H Button Box")
						horizontalButtonBox {
							println("Adding Button")
							button("Hello World") {
								onClicked {
									println("Clicked!")
								}
							}
						}
					}
				}
			}
		println("Status: $finalStatus")
	}
}