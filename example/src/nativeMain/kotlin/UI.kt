import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import nativex.async.launchUI
import nativex.gio.Notification
import nativex.gio.dsl.sendNotification
import nativex.gtk.Application
import nativex.gtk.IconSize
import nativex.gtk.common.enums.Orientation
import nativex.gtk.dsl.*
import nativex.gtk.widgets.container.ButtonBox
import nativex.gtk.widgets.container.bin.windows.Window
import nativex.gtk.widgets.container.bin.windows.dialog.Dialog
import nativex.gtk.widgets.container.bin.windows.dialog.MessageDialog

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
@DelicateCoroutinesApi
@ExperimentalCoroutinesApi
internal fun Window.mainKotlinTestBox(application: Application) =
	box(Orientation.HORIZONTAL, 10) {
		start {
			frame(
				"Concurrent Access Test",
				expand = true,
				fill = true,
				padding = 10u
			) {
				verticalButtonBox {
					buttonBoxStyle =
						ButtonBox.ButtonBoxStyle.CENTER
					button("Start") {
						onClicked {
							viewModel.startSharedDataTest()
						}
					}
					button("Stop") {
						onClicked {
							viewModel.stopSharedDataTest()
						}
					}
				}
			}

			frame(
				"Kotlin Coroutine Job Test",
				expand = true,
				fill = true,
				padding = 10u
			) {
				verticalButtonBox {
					buttonBoxStyle =
						ButtonBox.ButtonBoxStyle.CENTER
					button("Start") {
						onClicked {
							viewModel.startJobTest()
						}
					}
					button("Stop") {
						onClicked {
							viewModel.stopJobTest()
						}
					}
				}
			}

			frame("Notification Test") {
				verticalButtonBox {
					buttonBoxStyle =
						ButtonBox.ButtonBoxStyle.CENTER

					button("Send") {
						onClicked {
							println("Sending notification")
							application.sendNotification(
								id = "kotlinx",
								title = "Test",
								body = "This is a test notification",
								priority = Notification.Priority.URGENT
							)
						}
					}

					button("Withdraw") {
						onClicked {
							println("Withdrawing notification")
							application.withdrawNotification("kotlinx")
						}
					}
				}
			}

		}

		end {
			frame(
				"Flow Test",
				expand = true,
				fill = true,
				padding = 10u
			) {
				verticalButtonBox {
					buttonBoxStyle =
						ButtonBox.ButtonBoxStyle.CENTER
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
			}

			frame(
				"Dialogs",
				expand = true,
				fill = true,
				padding = 10u
			) {
				verticalButtonBox {
					buttonBoxStyle =
						ButtonBox.ButtonBoxStyle.CENTER
					button("Message Dialog") {
						onClicked {
							launchUI {
								messageDialog(
									Dialog.Flags.DESTROY_WITH_PARENT,
									MessageDialog.MessageType.INFO,
									MessageDialog.ButtonsType.OK,
									messageFormat = "This is a message dialog"
								).run()
							}
						}
					}
					button("About Dialog") {
						onClicked {
							launchUI {
								aboutDialog {
									title = "This is an about dialog"
								}.run()
							}
						}
					}
				}
			}

			frame(
				"ButtonTypes",
				expand = true,
				fill = true,
				padding = 10u
			) {
				verticalButtonBox {
					buttonBoxStyle =
						ButtonBox.ButtonBoxStyle.CENTER

					scaleButton(IconSize.SMALL_TOOLBAR) {
						onClicked {
							println("Clicked scale button")
						}
					}

					button("Normal Button") {
						onClicked {
							println("Clicked normal button")
						}
					}
					linkButton(
						"https://github.com/Doomsdayrs/kotlinx-gtk",
						"Link Button"
					) {
						onClicked {
							println("Clicked link button")
							visited()
						}
					}
					toggleButton("Toggle Button") {
						onToggle {
							println("Clicked toggle button ${this.active}")
						}
					}
				}
			}

			searchEntry {
				onSearchChanged {
					println("SearchChanged: ${this.text}")
				}
				onPreviousMatch {
					println("Previous Match: ${this.text}")
				}
				onStopSearch {
					println("Stop search: ${this.text}")
				}
			}
		}
	}
