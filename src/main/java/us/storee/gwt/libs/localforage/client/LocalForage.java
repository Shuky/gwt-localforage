package us.storee.gwt.libs.localforage.client;

import com.google.gwt.core.client.ScriptInjector;

public class LocalForage {

	static {
		if (!isLoaded()) {
			load();
			//configStoreName("STOREE");
		}
	}

	
	
	public static void fireCallback(LocalForageCallback callback, Boolean err, String value) {
		if (callback == null) {
			return;
		}
		if (err == null) err = false;
		callback.onComplete(err, value);
	}

	public static void fireCallback(LocalForageCallback callback) {
		fireCallback(callback, null, null);
	}

	public native void setItemAsync(String key, String value, LocalForageCallback callback) /*-{
		var callbackFn = $entry(function(err, val) {
			@us.storee.gwt.libs.localforage.client.LocalForage::fireCallback(Lus/storee/gwt/libs/localforage/client/LocalForageCallback;Ljava/lang/Boolean;Ljava/lang/String;)(callback, err, val);
		});
		$wnd.localforage.setItem(key, value, callbackFn);
	}-*/;

	public native void setItemAsync(String key, String value) /*-{
		$wnd.localforage.setItem(key, value);
	}-*/;

	public native void getItemAsync(String key, LocalForageCallback callback) /*-{
		var callbackFn = $entry(function(err, val) {
			@us.storee.gwt.libs.localforage.client.LocalForage::fireCallback(Lus/storee/gwt/libs/localforage/client/LocalForageCallback;Ljava/lang/Boolean;Ljava/lang/String;)(callback, err, val)
		});
		$wnd.localforage.getItem(key, callbackFn);
	}-*/;

	public native void removeItemAsync(String key, LocalForageCallback callback) /*-{
		var callbackFn = $entry(function(err, val) {
			@us.storee.gwt.libs.localforage.client.LocalForage::fireCallback(Lus/storee/gwt/libs/localforage/client/LocalForageCallback;Ljava/lang/Boolean;Ljava/lang/String;)(callback, err, val)
		});
		$wnd.localforage.removeItem(key, callbackFn);
	}-*/;

	public native void removeItemAsync(String key) /*-{
		$wnd.localforage.removeItem(key);
	}-*/;

	public native void clearAsync(LocalForageCallback callback) /*-{
		var callbackFn = $entry(function(val) {
			@us.storee.gwt.libs.localforage.client.LocalForage::fireCallback(Lus/storee/gwt/libs/localforage/client/LocalForageCallback;)(callback);
		});
		$wnd.localforage.clear(callbackFn);
	}-*/;

	public native void clearAsync() /*-{
		$wnd.localforage.clear();
	}-*/;

	public native boolean isSupported() /*-{
		return !($wnd.localforage === null || $wnd.localforage === undefined);
	}-*/;
	

	/**
	 * Loads the offline library. You normally never have to do this manually
	 */
	public static void load() {
		if (!isLoaded()) {
			ScriptInjector.fromString(LocalForageResources.INSTANCE.js().getText()).setWindow(ScriptInjector.TOP_WINDOW).inject();
		}
	}
	
	public static native boolean isLoaded()/*-{
	if (typeof $wnd.localforage === "undefined" || $wnd.localforage === null) {
		return false;
	}
	return true;
}-*/;



}
