## Spacemacs figwheel cljs repl setup

* Open a Clojure file to trigger clojure mode
* Start Clojure REPL `SPC m s i`
* Open up the CIDER REPL buffer with SPC b b - choose cider-repl. This is a CLJ/JVM one.
* In the cider-repl buffer, call the following 3 functions:
  * `(use 'figwheel-sidecar.repl-api)`
  * `(start-figwheel!)`
  * `(cljs-repl)`
* Use :cljs/quit to quit out of cljs/JS to return to CLJ/JVM repl
* To kill cider: `SPC m s q`
