package com.trevorism.predict.webapi

import org.junit.Test

/**
 * @author tbrooks
 */
class RootControllerTest {

    @Test
    void testPing() {
        RootController rc = new RootController()
        assert rc.ping() == "pong"
    }

    @Test
    void testDisplayHelpLink() {
        RootController rc = new RootController()
        assert rc.displayHelpLink().contains("/api/help")
    }

    @Test
    void testHelp() {
        RootController rc = new RootController()
        assert rc.help()
    }
}
