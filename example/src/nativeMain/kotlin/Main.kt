import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import nativex.async.launchDefault
import nativex.gio.Menu
import nativex.gtk.common.enums.Orientation
import nativex.gtk.dsl.*
import nativex.gtk.widgets.container.bin.windows.Window
import kotlin.system.measureTimeMillis
import kotlin.test.Test

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
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

val viewModel = ViewModel()

@ExperimentalCoroutinesApi
@ExperimentalUnsignedTypes
@Test
fun main() {
	application("com.github.doomsdayrs.lib.kotlinx-gtk.test") {

		onWindowAdded {
			println("Added new window ${it.title}")
		}

		onWindowRemoved {
			println("Removed window ${it.title}")
		}
		onCreateUI {
			menuBar = Menu().apply {
				appendItem(Menu.Item("File"))
			}
			measureTimeMillis {
				applicationWindow {
					title = "Kotlin/Native Gtk Test"
					defaultSize = 600 x 200
					add(mainKotlinTestBox)


				}.showAll()
			}.also {
				println("Created UI in $it ms")
			}
		}
	}.let { finalStatus ->
		println("Status: $finalStatus")
	}
}