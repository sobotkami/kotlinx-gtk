import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.gnome.gtkx.coroutines.launchIO
import org.gnome.gtkx.coroutines.launchUI
import org.gtk.dsl.gio.sendNotification
import org.gtk.dsl.gtk.*
import org.gtk.gio.Notification
import org.gtk.gtk.Application
import org.gtk.gtk.FileChooser
import org.gtk.gtk.common.enums.Orientation
import org.gtk.gtk.widgets.windows.Window
import org.gtk.gtk.widgets.windows.dialog.*

/**
 * kotlinx-gtk
 * 23 / 03 / 2021
 */
@DelicateCoroutinesApi
internal fun Window.mainKotlinTestBox(application: Application) =
	box(Orientation.HORIZONTAL, 10) {
		frame(
			"Concurrent Access Test",
		) {
			box(Orientation.VERTICAL, 10) {
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
		) {
			box(Orientation.VERTICAL, 10) {
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
			box(Orientation.VERTICAL, 10) {

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


		frame(
			"Flow Test",
		) {
			box(Orientation.VERTICAL, 10) {
				button("Increment flow value") {
					onClicked {
						viewModel.incrementFlow()
					}
				}
				button("Collect flow value") {
					onClicked {
						launchIO {
							println("Value: ${viewModel.flow.first()}")
						}
					}
				}
			}
		}

		frame(
			"Dialogs",
		) {
			box(Orientation.VERTICAL, 10) {
				button("About Dialog") {
					onClicked {
						launchUI {
							val dialog = aboutDialog {
								title = "This is an about dialog"
								programName = "Gtk3 Test"
								version = "Version something"
								licenseType = AboutDialog.License.AGPL_3_0
								website = "https://github.com/Doomsdayrs/kotlinx-gtk"
								websiteLabel = "Github"
								authors("Doomsdayrs", "Me")
								artists("Doomsdayrs", "Me")
								documenters("Doomsdayrs", "Me")
								translatorCredits = "Thanks!"
								addCreditSection("Who made this", "Me", "Me", "Me")
							}
							println(dialog.show())
						}
					}
				}

				button("App Chooser Dialog") {
					onClicked {
						launchUI {
							val dialog = appChooserDialog(
								dialogFlags = Dialog.Flags.USE_HEADER_BAR,
								contentType = "txt"
							) {
								heading = "This is an app chooser"
							}
							println(dialog.show())
							dialog.close()
						}
					}
				}
				button("Color Chooser Dialog") {
					onClicked {
						launchUI {
							val dialog = colorChooserDialog("Color Chooser Dialog Test")
							println(dialog.show())
							dialog.close()
							println("RGBA: ${dialog.rGBA}")
						}
					}
				}

				button("File Chooser Dialog") {
					onClicked {
						launchUI {
								val dialog = FileChooserDialog(
									FileChooser.Action.ACTION_OPEN,
									this@mainKotlinTestBox,
									"Select",
									"Open",
									"Cancel"
								)

								dialog.addOnCloseCallback {
									println("Closed")
								}
								dialog.addOnShowCallback {
									println("Shown")
								}

								dialog.addOnResponseCallback {
									println("Received response: $it")
									when (it) {
										Dialog.ResponseType.ACCEPT -> {
											println("File selected: `${dialog.file.peekPath()}`")
											dialog.close()
										}
										Dialog.ResponseType.CANCEL -> {
											println("Cancelled")
											dialog.close()
										}
										else -> println("Unhandled response")
									}
								}

								dialog.show()
						}
					}
				}

				button("Font Chooser Dialog") {
					onClicked {
						launchUI {
							val dialog = FontChooserDialog(
								this@mainKotlinTestBox,
								"Font Chooser Dialog"
							)
							println(dialog.show())
							dialog.close()
						}
					}
				}
				button("Message Dialog") {
					onClicked {
						launchUI {
							val dialog = messageDialog(
								Dialog.Flags.DESTROY_WITH_PARENT,
								MessageDialog.MessageType.INFO,
								MessageDialog.ButtonsType.OK,
								messageFormat = "This is a message dialog"
							)
							println(dialog.show())
							dialog.close()
						}
					}
				}
				/*
				button("Page Setup Unix Dialog") {
					onClicked {
						launchUI {
						}
					}
				}
				button("Print Unix Dialog") {
					onClicked {
						launchUI {
						}
					}
				}
				 */

				button("Recent Chooser Dialog") {
					onClicked {
						launchUI {
						}
					}
				}
			}
		}

		frame(
			"ButtonTypes",
		) {
			box(Orientation.VERTICAL, 10) {

				scaleButton() {
					addOnPopupCallback {
						println("ScaleButton popup")
					}

					addOnPopdownCallback {
						println("ScaleButton popdown")
					}

					addOnValueChangedCallback {
						println("ScaleButton value changed, now: $it")
						// TODO Figure out why `TypeInfo.cpp:41: runtime assert: Unknown open method` occurs here
					}
				}

				button("Normal Button") {
					onClicked {
						println("Clicked normal button")
					}
				}
				linkButton(
					"https://github.com/Doomsdayrs/kotlinx-gtk",
				) {
					name = "Link button"
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
