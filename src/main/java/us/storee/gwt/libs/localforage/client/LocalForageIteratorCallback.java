package us.storee.gwt.libs.localforage.client;

/**
 * function(value, key, iterationNumber)
 */
public interface LocalForageIteratorCallback<T> {
    /**
     * iteratorCallback is called once for each pair, with the following arguments:
     * iterate supports early exit by returning non undefined value inside iteratorCallback callback. Resulting value will be passed to successCallback as the result of iteration.
     * @param value
     * @param key
     * @param iterationNumber  - one-based number
     */
    void iteratorCallback(T value, String key, Integer iterationNumber);
}
