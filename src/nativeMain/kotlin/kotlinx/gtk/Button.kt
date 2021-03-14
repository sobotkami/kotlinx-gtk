package kotlinx.gtk

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlin.gtk.container.bin.button.Button
import kotlin.gtk.container.Container

@GtkDsl
inline fun Container.button(
	label: String,
	buttonBuilder: Button.() -> Unit = {}
) = add(Button(label).apply(buttonBuilder))

@ExperimentalUnsignedTypes
@ExperimentalCoroutinesApi
@GtkDsl
inline fun Button.onClicked(crossinline onClicked: suspend () -> Unit) {
	GlobalScope.launch(context = Dispatchers.Unconfined) {
		clickedSignal.collectLatest {
				onClicked()
		}
	}

}