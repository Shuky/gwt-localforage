package us.storee.gwt.libs.localforage.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.junit.client.GWTTestCase;

/**
 * GWT JUnit <b>integration</b> tests must extend GWTTestCase.
 * Using <code>"GwtTest*"</code> naming pattern exclude them from running with
 * surefire during the test phase.
 * 
 * If you run the tests using the Maven command line, you will have to 
 * navigate with your browser to a specific url given by Maven. 
 * See http://mojo.codehaus.org/gwt-maven-plugin/user-guide/testing.html 
 * for details.
 */
public class GwtTestLocalForage extends GWTTestCase {

    public static final int TIMEOUT_MILLIS = 10000;

    /**
   * Must refer to a valid module that sources this class.
   */
  public String getModuleName() {
    return "us.storee.gwt.libs.localforage.LocalForageJUnit";
  }

  /**
   * Tests the localForage.
   */
  public void testLocalForage() {
	  final LocalForage localForage = GWT.create(LocalForage.class);
      assertTrue(localForage.isSupported());
      assertTrue(localForage.isLoaded());
	  localForage.setItem("key1", "value1", new LocalForageCallback() {

		@Override
		public void onComplete(boolean error, String value) {
            assertEquals(value, "value1");
            finishTest();
        }
	});
    delayTestFinish(TIMEOUT_MILLIS);
  }


}
