package com.kaylerrenslow.armaDialogCreator.arma.stringtable;

import com.kaylerrenslow.armaDialogCreator.util.ValueObserver;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 Created by Kayler on 05/23/2016.
 */
public interface StringTableKey {
	/** Get key id (e.g. str_tag_key) */
	@NotNull
	String getId();

	@NotNull
	StringTableValue getValue();

	@Nullable
	default String getPackageName() {
		return packageNameObserver().getValue();
	}

	@NotNull
	ValueObserver<String> packageNameObserver();

	@Nullable
	default String getContainerName() {
		return containerNameObserver().getValue();
	}

	@NotNull
	ValueObserver<String> containerNameObserver();

	default boolean equalsKey(StringTableKey key) {
		if (key == null) {
			return false;
		}
		if (key == this) {
			return true;
		}
		if (!getId().equals(key.getId())) {
			return false;
		}
		if (!getValue().equalsValue(key.getValue())) {
			return false;
		}
		if (getPackageName() == null) {
			if (key.getPackageName() != null) {
				return false;
			}
		} else {
			if (!getPackageName().equals(key.getPackageName())) {
				return false;
			}
		}
		if (getContainerName() == null) {
			if (key.getContainerName() != null) {
				return false;
			}
		} else {
			if (!getContainerName().equals(key.getContainerName())) {
				return false;
			}
		}
		return true;

	}
}