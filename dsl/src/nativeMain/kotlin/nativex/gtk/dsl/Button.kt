package nativex.gtk.dsl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import nativex.async.launchUnconfined
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.bin.button.Button

@GtkDsl
inline fun Container.button(
	label: String,
	buttonBuilder: Button.() -> Unit = {}
) = add(Button(label).apply(buttonBuilder))

@ExperimentalUnsignedTypes
@ExperimentalCoroutinesApi
@GtkDsl
inline fun Button.onClicked(crossinline onClicked: suspend () -> Unit) {
	launchUnconfined {
		clickedSignal.collectLatest {
			onClicked()
		}
	}

}