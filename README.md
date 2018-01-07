# Leo's landing site

## Development Mode

### Compile scss:

Compile css file once.

```
lein scss once :dev
```

Automatically recompile css file on change.

```
lein scss auto :dev
```

### Run application:

```
lein clean
lein figwheel dev
```

Figwheel will automatically push cljs changes to the browser.

Wait a bit, then browse to [http://localhost:3449](http://localhost:3449).

### Run tests:

```
lein clean
lein doo phantom test once
```

The above command assumes that you have [phantomjs](https://www.npmjs.com/package/phantomjs) installed. However, please note that [doo](https://github.com/bensu/doo) can be configured to run cljs.test in many other JS environments (chrome, ie, safari, opera, slimer, node, rhino, or nashorn).

## Production Build


To compile clojurescript to javascript:

```
lein do clean, scss once :prod, cljsbuild once prod
```

The build will be under the `resources/public` directory.
