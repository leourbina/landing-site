(ns customer-ui.runner
    (:require [doo.runner :refer-macros [doo-tests]]
              [customer-ui.core-test]))

(doo-tests 'customer-ui.core-test)
