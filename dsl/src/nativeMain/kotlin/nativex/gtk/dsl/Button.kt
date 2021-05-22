package nativex.gtk.dsl

import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import nativex.GtkDsl
import nativex.async.launchUnconfined
import nativex.gtk.widgets.container.Container
import nativex.gtk.widgets.container.bin.button.Button

@GtkDsl
inline fun Container.button(
	label: String,
	buttonBuilder: Button.() -> Unit = {}
) = Button(label).apply(buttonBuilder).also { add(it) }


@ExperimentalCoroutinesApi
@GtkDsl
inline fun Button.onClicked(crossinline onClicked: suspend () -> Unit) {
	launchUnconfined {
		clickedSignal.collectLatest {
			onClicked()
		}
	}

}