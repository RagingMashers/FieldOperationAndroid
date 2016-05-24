package nl.ragingmashers.cimsfieldoperations;

import android.app.Application;
import android.test.ApplicationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

import nl.ragingmashers.cimsfieldoperations.fiop.ApiManager;

/**
 * <a href="http://d.android.com/tools/testing/testing_android.html">Testing Fundamentals</a>
 */
public class ApplicationTest extends ApplicationTestCase<Application> {
    public ApplicationTest() {
        super(Application.class);
    }
    @SmallTest
    public void testMethod(){
        assertFalse(ApiManager.getInstance().isLoggedIn());
        assertTrue(ApiManager.getInstance().login("testUser","testPass"));
        assertTrue(ApiManager.getInstance().isLoggedIn());
        assertTrue(ApiManager.getInstance().logout());
        assertFalse(ApiManager.getInstance().isLoggedIn());
    }
}