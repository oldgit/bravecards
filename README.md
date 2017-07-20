# Bravecards is [devcards](https://github.com/bhauman/devcards) for [Clojure for the Brave and True](http://www.braveclojure.com/introduction)

Using Bruce Hauman's great [devcards](https://github.com/bhauman/devcards) to work through Daniel Higginbotham's enjoyable book: [Clojure for the Brave and True](http://www.braveclojure.com/introduction).

**Why?** - well more the question: **Why not?**

[ClojureScript](https://clojurescript.org/) is Lisp elegance with immutable, persistent data structures for the web...

'Most Excellent' as [Ted](http://www.imdb.com/title/tt0096928/?ref_=ttpl_pl_tt) would say ;-)

Amusing [xkcd](https://xkcd.com/)'s take on it:

![alt lisp cycles comic](https://imgs.xkcd.com/comics/lisp_cycles.png 'Lisp xkcd')

For those vimsters out there, take a look at [figwheel-vim.md](./figwheel-vim.md) and [figwheel-spacemacs.md](./figwheel-spacemacs.md) to integrate with Bruce's invaluable [figwheel](https://github.com/bhauman/lein-figwheel).
 
## Let's get figwheelin'

If you just want to quickly clone and run this project:

* In your favourite terminal and dev space:
  * `git clone https://github.com/oldgit/bravecards.git`
  * `cd bravecards`
  * `lein figwheel`
  * A browser window at: http://localhost:3449/cards.html should pop up
  * Click on **bravecards.core**
  * Congratulations - you're figwheelin'!
 
 I prefer to hook up my neovim editor to figwheel by following the instructions in [figwheel-vim.md](./figwheel-vim.md).
