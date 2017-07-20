## Vim figwheel cljs repl setup

### Vim plugins required

Add the following vim plugins to your config, eg. for Plug:

```
" clojure syntax
Plug 'guns/vim-clojure-static'
Plug 'guns/vim-sexp'
Plug 'tpope/vim-sexp-mappings-for-regular-people'
Plug 'tpope/vim-repeat'
Plug 'tpope/vim-surround'
" clojure cider-nrepl
Plug 'tpope/vim-fireplace'
```

### Run lein repl

* In a terminal, in this dir, start lein repl: `lein repl`
* Once the repl prompt appears, call the following 3 functions:
  * `(use 'figwheel-sidecar.repl-api)`
  * `(start-figwheel!)`
  * `(cljs-repl)`
* To test the repl, try: `(js/alert "hello")` or `(.log js/console "hello")`
* To quit the cljs-repl: `:cljs/quit`
* To quit the repl: `quit`

### Vim link to lein repl

* In another terminal, use vim to edit a cljs file
* In vim, type: `:Piggieback (figwheel-sidecar.repl-api/repl-env)`
* Add a test form like: `(+ 1 2)` in the cljs file
* Place your cursor on the form and try fireplace eval by typing `cpp` in normal mode
* The value `3` will appear at the bottom - it works!

### Vim clojure references

* [sexp-cheat-sheet](https://gist.github.com/dylanmcdiarmid/a4377c93c2ef62b987cf)
* [fireplace](https://github.com/tpope/vim-fireplace/blob/master/doc/fireplace.txt)
