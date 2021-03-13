import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.first
import kotlinx.gtk.*
import kotlin.async.launchDefault
import kotlin.async.launchIO
import kotlin.async.launchUI
import kotlin.gtk.windows.dialog.Dialog
import kotlin.gtk.windows.dialog.MessageDialog
import kotlin.test.Test

/**
 * kotlinx-gtk
 * 08 / 02 / 2021
 */
class Main {


	@ExperimentalCoroutinesApi
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

	@ExperimentalCoroutinesApi
	@ExperimentalUnsignedTypes
	@Test
	fun main() {
		val finalStatus =
			application("com.github.doomsdayrs.lib.kotlinx-gtk.test") {
				val viewModel = ViewModel()
				onCreateUI {
					applicationWindow {
						title = "Window"

						defaultSize = 600 x 200

						fixed {
							verticalButtonBox(0, 0) {
								button("Start Shared Test") {
									onClicked {
										viewModel.startSharedDataTest()
									}
								}
								button("Stop Shared Test") {
									onClicked {
										viewModel.stopSharedDataTest()
									}
								}
							}

							verticalButtonBox(200, 0) {
								button("Start Job Test") {
									onClicked {
										viewModel.startJobTest()
									}
								}
								button("Stop Job Test") {
									onClicked {
										viewModel.stopJobTest()
									}
								}
							}

							verticalButtonBox(400, 0) {
								button("Increment flow value") {
									onClicked {
										viewModel.incrementFlow()
									}
								}
								button("Collect flow value") {
									onClicked {
										println("Value: ${viewModel.flow.first()}")
									}
								}
							}

							verticalButtonBox(600, 0) {
								button("Dialog test (Default)") {
									onClicked {
										launchDefault {
											messageDialog(
												Dialog.Flags.DESTROY_WITH_PARENT,
												MessageDialog.MessageType.INFO,
												MessageDialog.ButtonsType.OK,
												messageFormat = "Test Default"
											).show()
										}
									}
								}

								button("Dialog test (Main)") {
									onClicked {
										GlobalScope.launch(context = Dispatchers.Main) {
											messageDialog(
												Dialog.Flags.DESTROY_WITH_PARENT,
												MessageDialog.MessageType.INFO,
												MessageDialog.ButtonsType.OK,
												messageFormat = "Test Main"
											).show()
										}
									}
								}

								button("Dialog test (Unconfined)") {
									onClicked {
										messageDialog(
											Dialog.Flags.DESTROY_WITH_PARENT,
											MessageDialog.MessageType.INFO,
											MessageDialog.ButtonsType.OK,
											messageFormat = "Test UNCONFINED"
										).show()
									}
								}

								button("Dialog test (Unconfinedx2)") {
									onClicked {
										GlobalScope.launch(context = Dispatchers.Unconfined) {
											messageDialog(
												Dialog.Flags.DESTROY_WITH_PARENT,
												MessageDialog.MessageType.INFO,
												MessageDialog.ButtonsType.OK,
												messageFormat = "Test UNCONFINED"
											).show()
										}
									}
								}

								button("Dialog test (Unconfined parent)") {
									onClicked {
										supervisorScope {
											messageDialog(
												Dialog.Flags.DESTROY_WITH_PARENT,
												MessageDialog.MessageType.INFO,
												MessageDialog.ButtonsType.OK,
												messageFormat = "Test UNCONFINED"
											).show()
										}
									}
								}

								button("Dialog test (IO)") {
									onClicked {
										launchIO {
											messageDialog(
												Dialog.Flags.DESTROY_WITH_PARENT,
												MessageDialog.MessageType.INFO,
												MessageDialog.ButtonsType.OK,
												messageFormat = "Test IO"
											).show()
										}
									}
								}

								button("Dialog test (UI)") {
									onClicked {
										launchUI {
											messageDialog(
												Dialog.Flags.DESTROY_WITH_PARENT,
												MessageDialog.MessageType.INFO,
												MessageDialog.ButtonsType.OK,
												messageFormat = "Test UI"
											).show()
										}
									}
								}

								button("Dialog test (Empty)") {
									onClicked {
										GlobalScope.launch {
											messageDialog(
												Dialog.Flags.DESTROY_WITH_PARENT,
												MessageDialog.MessageType.INFO,
												MessageDialog.ButtonsType.OK,
												messageFormat = "Test Empty"
											).show()
										}
									}
								}


							}
						}
					}
				}
			}
		println("Status: $finalStatus")
	}
}