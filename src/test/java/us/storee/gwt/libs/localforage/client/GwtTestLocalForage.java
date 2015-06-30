package us.storee.gwt.libs.localforage.client;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JavaScriptObject;
import com.google.gwt.core.client.JsArrayString;
import com.google.gwt.json.client.JSONObject;
import com.google.gwt.junit.client.GWTTestCase;

import java.util.Arrays;
import java.util.Objects;

/**
 * GWT JUnit <b>integration</b> tests must extend GWTTestCase.
 * Using <code>"GwtTest*"</code> naming pattern exclude them from running with
 * surefire during the test phase.
 * <p/>
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
        return "us.storee.gwt.libs.localforage.LocalForageJUnitTests";
    }

    @Override
    protected void gwtSetUp() throws Exception {

    }

    /**
     * Tests the localForage.
     */
    public void testLocalForageSetItemAndLength() {
        final LocalForage localForage = GWT.create(LocalForage.class);
        assertTrue(localForage.isSupported());
        assertTrue(localForage.isLoaded());
        localForage.clear(new LocalForageCallback() {
            @Override
            public void onComplete(boolean error, Object value) {
                localForage.setItem("key1", "value1", new LocalForageCallback<String>() {
                    @Override
                    public void onComplete(boolean error, String value) {
                        assertEquals(value, "value1");

                        assertLengthEqaulsOne(localForage);

                    }
                });
            }
        });
        delayTestFinish(TIMEOUT_MILLIS);
    }

    private void assertLengthEqaulsOne(LocalForage localForage) {
        localForage.length(new LocalForageCallback<Integer>() {
            @Override
            public void onComplete(boolean error, Integer value) {
                assertFalse(error);
                assertEquals(1, (int) value);
                finishTest();

            }
        });
    }

    public void testLocalForageClearItem() {
        final LocalForage localForage = GWT.create(LocalForage.class);
        assertTrue(localForage.isSupported());
        assertTrue(localForage.isLoaded());
        localForage.setItem("key1", "value1", new LocalForageCallback<String>() {
            @Override
            public void onComplete(boolean error, String value) {
                assertEquals(value, "value1");
                localForage.clear(new LocalForageCallback() {
                    @Override
                    public void onComplete(boolean error, Object value) {
                        assertFalse(error);
                        assertEquals("", value);
                        localForage.length(new LocalForageCallback<Integer>() {
                            @Override
                            public void onComplete(boolean error, Integer value) {
                                assertFalse(error);
                                assertEquals(0, (int) value);
                                finishTest();
                            }
                        });
                    }
                });

            }
        });
        delayTestFinish(TIMEOUT_MILLIS);
    }

    public void testLocalForageKey() {
        final LocalForage localForage = GWT.create(LocalForage.class);
        assertTrue(localForage.isSupported());
        assertTrue(localForage.isLoaded());
        localForage.setItem("key1", "value1", new LocalForageCallback<String>() {
            @Override
            public void onComplete(boolean error, String value) {
                assertEquals(value, "value1");
                localForage.key(0, new LocalForageCallback<String>() {
                    @Override
                    public void onComplete(boolean error, String value) {
                        assertFalse(error);
                        assertEquals("key1", value);

                        localForage.key(1, new LocalForageCallback<String>() {
                            @Override
                            public void onComplete(boolean error, String value) {
                                assertFalse(error);
                                assertEquals(null, value);
                                finishTest();
                            }
                        });
                    }
                });

            }
        });
        delayTestFinish(TIMEOUT_MILLIS);
    }

    public void testLocalForageKeys() {
        final LocalForage localForage = GWT.create(LocalForage.class);
        assertTrue(localForage.isSupported());
        assertTrue(localForage.isLoaded());
        localForage.setItem("key1", "value1", new LocalForageCallback<String>() {
            @Override
            public void onComplete(boolean error, String value) {
                assertEquals(value, "value1");
                localForage.setItem("key2", "value2", new LocalForageCallback() {
                    @Override
                    public void onComplete(boolean error, final Object value) {
                        localForage.keys(new LocalForageCallback<String[]>() {
                            @Override
                            public void onComplete(boolean error, String[] values) {
                                assertFalse(error);
                                assertEquals(2, values.length);
                                assertEquals(Arrays.toString(new String[]{"key1", "key2"}), Arrays.toString(values));

                                localForage.length(new LocalForageCallback<Integer>() {
                                    @Override
                                    public void onComplete(boolean error, Integer value) {
                                        assertEquals(2, (int) value);
                                        localForage.clear(new LocalForageCallback() {
                                            @Override
                                            public void onComplete(boolean error, Object value) {
                                                finishTest();
                                            }
                                        });
                                    }
                                });
                            }
                        });

                    }
                });
            }
        });
        delayTestFinish(TIMEOUT_MILLIS);
    }

    public void testLocalForageIterate() {
        final LocalForage localForage = GWT.create(LocalForage.class);
        assertTrue(localForage.isSupported());
        assertTrue(localForage.isLoaded());
        final int[] countOfIteration = {0};
        localForage.clear(new LocalForageCallback() {
            @Override
            public void onComplete(boolean error, Object value) {
                assertFalse(error);
                localForage.setItem("key1", "value1", new LocalForageCallback<String>() {
                    @Override
                    public void onComplete(boolean error, String value) {
                        assertEquals(value, "value1");
                        localForage.setItem("key2", "value2", new LocalForageCallback() {
                            @Override
                            public void onComplete(boolean error, final Object value) {
                                assertFalse(error);
                                localForage.length(new LocalForageCallback<Integer>() {
                                    @Override
                                    public void onComplete(boolean error, Integer value) {
                                        assertEquals(2, (int) value);
                                        localForage.iterate(new LocalForageIteratorCallback() {
                                            @Override
                                            public JavaScriptObject iteratorCallback(String value, String key, Integer iterationNumber) {
                                                System.out.println("iterationNumber: " + iterationNumber);

                                                switch (iterationNumber) {
                                                    case 1:
                                                        assertEquals("key1", key);
                                                        System.out.println("First run");
                                                        countOfIteration[0]++;
                                                        assertEquals(1, countOfIteration[0]);
                                                        return null;
                                                    case 2:
                                                        System.out.println("Second run");
                                                        assertEquals("key2", key);
                                                        countOfIteration[0]++;
                                                        assertEquals(2, countOfIteration[0]);
                                                        finishTest();
                                                        return null;
                                                    default:
                                                        fail();
                                                        finishTest();
                                                        break;
                                                }
                                                return null;
                                            }
                                        }, new LocalForageCallback<String[]>() {
                                            @Override
                                            public void onComplete(boolean error, String[] value) {
                                                assertFalse(error);
                                                assertEquals(2, countOfIteration[0]);
                                                System.out.println(Arrays.toString(value));
                                            }
                                        });
                                    }
                                });
                            }
                        });

                    }
                });
            }
        });
        delayTestFinish(TIMEOUT_MILLIS);
    }
}
