
package com.app.util;

import java.util.Collection;
import java.util.Map;

public final class NullUtil {

	public static boolean isEmpty(Collection<?> collection) {
		if (collection == null || collection.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Map<?, ?> map) {
		if (map == null || map.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object object) {
		if (object == null) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(Object[] array) {
		if (array == null || array.length == 0) {
			return true;
		}
		return false;
	}

	public static boolean isEmpty(String string) {
		if (string == null || string.trim().length() == 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Collection<?> collection) {
		if (collection != null && !collection.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Map<?, ?> map) {
		if (map != null && !map.isEmpty()) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Object object) {
		if (object != null) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(Object[] array) {
		if (array != null && array.length != 0) {
			return true;
		}
		return false;
	}

	public static boolean isNotEmpty(String string) {
		if (string != null && string.trim().length() != 0) {
			return true;
		}
		return false;
	}

}
