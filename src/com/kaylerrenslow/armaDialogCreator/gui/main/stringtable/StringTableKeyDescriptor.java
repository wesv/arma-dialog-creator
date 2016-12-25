package com.kaylerrenslow.armaDialogCreator.gui.main.stringtable;

import com.kaylerrenslow.armaDialogCreator.arma.stringtable.Language;
import com.kaylerrenslow.armaDialogCreator.arma.stringtable.StringTableKey;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

/**
 @author Kayler
 @since 12/24/2016 */
class StringTableKeyDescriptor {
	private static final String FORMAT = "%-40s (%20s / %-20s) %1s %s";

	private StringTableKey key;
	private final String noPackageName;
	private final String noContainerName;
	private Language language;

	public StringTableKeyDescriptor(@NotNull StringTableKey key, @NotNull String noPackageName, @NotNull String noContainerName) {
		this.key = key;
		this.noPackageName = noPackageName;
		this.noContainerName = noContainerName;
		if (!key.getValue().getLanguageTokenMap().keySet().isEmpty()) {
			language = key.getValue().getLanguageTokenMap().keySet().iterator().next();
		}
	}

	public void setPreviewLanguage(@Nullable Language language) {
		this.language = language;
	}

	@Override
	public String toString() {
		String text = "";
		boolean defaultMatch = true;
		if (language != null) {
			text = key.getValue().getLanguageTokenMap().get(language);
			if (text == null) {
				defaultMatch = false;
				Map.Entry<Language, String> entry = key.getValue().getFirstLanguageTokenEntry();
				if (entry == null || entry.getValue() == null) {
					text = "";
				} else {
					text = entry.getValue();
				}
			}
			if (text == null) {
				text = "";
			} else {
				text = text.replaceAll("[\r\n\t]", "");
			}
		}
		return String.format(
				FORMAT,
				key.getId(),
				key.getPackageName() != null ? key.getPackageName() : noPackageName,
				key.getContainerName() != null ? key.getContainerName() : noContainerName,
				defaultMatch ? "" : "!",
				"\"" + text + "\""
		);
	}

	@NotNull
	public StringTableKey getKey() {
		return key;
	}
}
