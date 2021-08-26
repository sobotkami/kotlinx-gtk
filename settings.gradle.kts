rootProject.name = "gtk-kt"

// Core libraries
include("src:gtk")
include("src:gio")
include("src:gobject")
include("src:glib")
include("src:cairo")
include("src:pango")

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
