rootProject.name = "kotlinx-gtk"

// Core libraries
include("nativex")
include("nativex:gio")
include("nativex:gobject")
include("nativex:glib")
include("nativex:cairo")
include("nativex:pango")

// Built on-top libraries
include("dsl")
include("coroutines")
include("example")

// Demos
include("demos:widget-factory")
include("demos:gtk-demo")

// Tests
include("tests:packing")
include("tests:combobox")
include("tests:application-window")
include("tests:glib")
