One of the early things that had to be accomplished in the library was wrapping the GTK signals that made sense with
kotlin flows.

A simple solution is Kotlin's `callbackFlow`. This allows me to register the producer and how to dispose of it after
used.

One issue is that I realized later on that not all signals can be translated as Flows. This is because they require a
return.

Instead, These signals have been translated as a Callback. One can add a callback, and receive a `SignalManager` in
return. The `SignalManager` allows the developer to disconnect the signal once complete