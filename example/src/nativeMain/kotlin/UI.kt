import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import nativex.async.launchIO
import nativex.async.launchUI
import nativex.gio.Notification
import nativex.gio.dsl.sendNotification
import nativex.gtk.Application
import nativex.gtk.FileChooser
import nativex.gtk.IconSize
import nativex.gtk.common.enums.Orientation
import nativex.gtk.dsl.*
import nativex.gtk.widgets.container.ButtonBox
import nativex.gtk.widgets.container.bin.windows.Window
import nativex.gtk.widgets.container.bin.windows.dialog.*

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
					layout =
						ButtonBox.Style.CENTER
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
					layout =
						ButtonBox.Style.CENTER
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
					layout =
						ButtonBox.Style.CENTER

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
					layout =
						ButtonBox.Style.CENTER
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
				expand = true,
				fill = true,
				padding = 10u
			) {
				verticalButtonBox {
					layout =
						ButtonBox.Style.CENTER
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
								println(dialog.run())
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
								println(dialog.run())
								dialog.close()
							}
						}
					}
					button("Color Chooser Dialog") {
						onClicked {
							launchUI {
								val dialog = colorChooserDialog("Color Chooser Dialog Test")
								println(dialog.run())
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
								println(dialog.run())
								println(dialog.filePath)
								dialog.destroy()
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
								println(dialog.run())
								dialog.destroy()
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
								println(dialog.run())
								dialog.destroy()
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
								val dialog = RecentChooserDialog(
									"Recent Chooser Dialog",
									this@mainKotlinTestBox,
									"Select",
									"Cancel"
								)
								println(dialog.run())
								dialog.destroy()
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
					layout =
						ButtonBox.Style.CENTER

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
