import org.gtk.dsl.gio.*
import org.gtk.dsl.gtk.application
import org.gtk.dsl.gtk.applicationWindow
import org.gtk.dsl.gtk.button
import org.gtk.dsl.gtk.frame
import org.gtk.gio.ActionMap
import org.gtk.gio.Application

val appEntries: List<ActionMap.Entry> = listOf(
	actionEntry("about"),
	actionEntry("shortcuts"),
	actionEntry("quit"),
	actionEntry("inspector"),
	stringActionEntry("main", state = "steak"),
	booleanActionEntry("wine", state = false),
	booleanActionEntry("beer", state = false),
	booleanActionEntry("water", state = true),
	stringActionEntry("dessert", state = "bars"),
	stringActionEntry("pay"),
	actionEntry("share"),
	actionEntry("labels"),
	actionEntry("new"),
	actionEntry("open"),
	actionEntry("open-in"),
	actionEntry("open-tab"),
	actionEntry("open-window"),
	actionEntry("save"),
	actionEntry("save-as"),
	actionEntry("cut"),
	actionEntry("copy"),
	actionEntry("paste"),
	booleanActionEntry("pin", state = true),
	stringActionEntry("size", state = "medium"),
	booleanActionEntry("berk", state = true),
	booleanActionEntry("broni", state = true),
	booleanActionEntry("drutt", state = true),
	booleanActionEntry("upstairs", state = true),
	actionEntry("option-a"),
	actionEntry("option-b"),
	actionEntry("option-c"),
	actionEntry("option-d"),
	booleanActionEntry("check-on", state = true),
	booleanActionEntry("check-off", state = false),
	stringActionEntry("radio-x", state = "x"),
	booleanActionEntry("check-on-disabled", state = true),
	booleanActionEntry("check-off-disabled", state = false),
	stringActionEntry("radio-x-disabled", state = "x"),
)

fun main() {
	application("org.gtk.WidgetFactory4", Application.Flags.NONE) {
		//addActionEntries(appEntries, this)
		lookUpSimpleAction("wine") {
			disable()
		}

		lookUpSimpleAction("wine") {
			enable()
		}
		lookUpSimpleAction("check-on-disabled") {
			disable()
		}
		lookUpSimpleAction("check-off-disabled") {
			disable()
		}
		lookUpSimpleAction("radio-x--disabled") {
			disable()
		}
		addMainOption("version", 'v', "Show program version")

		onCreateUI {
			applicationWindow {
				frame {
					button("Test")
				}
			}
		}
		//TODO ENV AUTO QUIT
	}
}