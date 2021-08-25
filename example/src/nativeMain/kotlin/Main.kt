
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import nativex.async.launchDefault
import nativex.gio.dsl.*
import nativex.glib.Variant
import nativex.gtk.dsl.*
import org.gtk.dsl.gtk.application
import org.gtk.dsl.gtk.x
import kotlin.system.measureTimeMillis

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
@DelicateCoroutinesApi
class ViewModel {
	private val _flow: MutableStateFlow<List<String>> = MutableStateFlow(
		listOf()
	)

	val flow: Flow<List<String>> = _flow
	private var num = 0

	fun incrementFlow() {
		num++
		_flow.value = ArrayList(_flow.value).apply {
			add("$num\n")
		}
	}

	var running = 0

	fun stopSharedDataTest() {
		running = 0
	}

	fun startSharedDataTest() {
		if (running == 1) return
		running = 1
		launchDefault {
			while (running == 1) {
				println("MT")
				delay(1000)
			}
			println("Stopped")
		}
	}

	var job: Job? = null

	fun startJobTest() {
		if (job != null) return
		job = launchDefault {
			while (true) {
				println("MT")
				delay(1000)
			}
		}
	}

	fun stopJobTest() {
		job?.cancel("Finished")
		job = null
	}
}

@DelicateCoroutinesApi
val viewModel = ViewModel()


const val ACTION_QUIT = "actionquit"

@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
fun main() {
	application("com.github.doomsdayrs.lib.kotlinx-gtk.test") {
		onQueryEnd {
			println("Query ended")
		}

		onWindowAdded {
			println("Added new window ${it.title}")
		}

		onWindowRemoved {
			println("Removed window ${it.title}")
		}




		onCreateUI {
			menuBar {
				submenu("File", menu {
					item("Open")
					submenu("Recent", menu {
						item("A")
						item("B")
						item("C")
					})

					item("Quit") {
						setActionAndTargetValue(
							ACTION_QUIT,
							Variant.BooleanVariant(true)
						)
					}
				})

				item("Edit")
				item("View")
				item("Help")
			}

			lookUpSimpleAction(ACTION_QUIT) {
				enable()
			}

			measureTimeMillis {
				applicationWindow {
					title = "Kotlin/Native Gtk Test"
					defaultSize = 600 x 200
					mainKotlinTestBox(this@onCreateUI)
					//entryPurposeTest()
					//entryHintTest()
					//entryCallbackTest()
				}.showAll()
			}.also {
				println("Created UI in $it ms")
			}
		}
	}.let { finalStatus ->
		println("Status: $finalStatus")
	}
}