import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.gtk.*
import kotlin.test.Test

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
class Main {

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	@Test
	fun main() {
		val finalStatus =
			application("com.github.doomsdayrs.lib.kotlinx-gtk.test") {
				onCreateUI {
					applicationWindow {
						title = "Window"

						defaultSize = 200 x 200

						horizontalButtonBox {
							button("Hello World") {
								onClicked {
									println("Clicked!")
								}
							}
							button("Second button") {
								onClicked {
									println("Second clicked!")
								}
							}
						}
					}
				}
			}
		println("Status: $finalStatus")
	}
}