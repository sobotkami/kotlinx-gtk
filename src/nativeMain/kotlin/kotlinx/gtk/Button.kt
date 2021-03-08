package kotlinx.gtk

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.async.launchUI
import kotlin.gtk.Button
import kotlin.gtk.Container

@GtkDsl
inline fun Container.button(
	label: String,
	buttonBuilder: Button.() -> Unit = {}
) = add(Button(label).apply(buttonBuilder))

@GtkDsl
inline fun Button.onClicked(crossinline onClicked: suspend () -> Unit) {
	println("Launching")
	GlobalScope.launch(context = Dispatchers.Unconfined) {
		println("Collecting")
		clickedSignal.collectLatest {
			println("Tock")
			launchUI {
				onClicked()
			}
		}
		println("Done")
	}

}