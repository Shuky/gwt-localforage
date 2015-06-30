package us.storee.gwt.libs.localforage.client;

public interface LocalForageCallback<T> {
	void onComplete(boolean error, T value);
}
