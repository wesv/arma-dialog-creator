package com.kaylerrenslow.armaDialogCreator.gui.main.controlPropertiesEditor;

import com.kaylerrenslow.armaDialogCreator.arma.control.ArmaControl;
import com.kaylerrenslow.armaDialogCreator.control.ControlClass;
import com.kaylerrenslow.armaDialogCreator.control.ControlProperty;
import com.kaylerrenslow.armaDialogCreator.control.ControlPropertyLookup;
import com.kaylerrenslow.armaDialogCreator.control.sv.*;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.inputfield.ExpressionChecker;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.inputfield.InputField;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.inputfield.InputFieldDataChecker;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.inputfield.StringChecker;
import com.kaylerrenslow.armaDialogCreator.main.ArmaDialogCreator;
import com.kaylerrenslow.armaDialogCreator.main.Lang;
import com.kaylerrenslow.armaDialogCreator.util.ReadOnlyValueListener;
import com.kaylerrenslow.armaDialogCreator.util.ReadOnlyValueObserver;
import com.kaylerrenslow.armaDialogCreator.util.ValueListener;
import com.kaylerrenslow.armaDialogCreator.util.ValueObserver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Node;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.FlowPane;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

/**All editors for {@link ControlPropertiesEditorPane}
 @author Kayler
 @since 01/29/2017 */
class ControlPropertyValueEditors {
	/** Used for when a set amount of options are available (uses radio button group for option selecting) */
	static class ControlPropertyOptionEditor extends FlowPane implements ControlPropertyValueEditor {
		private final ControlProperty controlProperty;
		private ToggleGroup toggleGroup;
		private List<RadioButton> radioButtons;
		private final InputField<StringChecker, String> rawInput = new InputField<>(new StringChecker());
		private final ValueListener<SerializableValue> controlPropertyListener = new ValueListener<SerializableValue>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<SerializableValue> observer, @Nullable SerializableValue oldValue, @Nullable SerializableValue newValue) {
				setEditorValue(newValue);
			}
		};

		private final ChangeListener<? super Toggle> toggleGroupListener = new ChangeListener<Toggle>() {
			@Override
			public void changed(ObservableValue<? extends Toggle> observable, Toggle oldValue, Toggle newValue) {
				if (newValue == null) {
					controlProperty.setValue((SerializableValue) null);
				} else {
					controlProperty.setValue(newValue.getUserData().toString());
				}

			}
		};

		ControlPropertyOptionEditor(@Nullable ControlClass control, @NotNull ControlProperty controlProperty) {
			super(10, 5);
			this.controlProperty = controlProperty;
			ControlPropertyValueEditor.modifyRawInput(rawInput, controlProperty);
			ControlPropertyLookup lookup = (ControlPropertyLookup) controlProperty.getPropertyLookup();
			toggleGroup = new ToggleGroup();
			RadioButton radioButton, toSelect = null;
			boolean validData = controlProperty.getValue() != null;
			if (lookup.getOptions() == null) {
				throw new IllegalStateException("options shouldn't be null");
			}
			radioButtons = new ArrayList<>(lookup.getOptions().length);
			for (com.kaylerrenslow.armaDialogCreator.control.ControlPropertyOption option : lookup.getOptions()) {
				if (option == null) {
					throw new IllegalStateException("option shouldn't be null");
				}
				radioButton = new RadioButton(option.displayName);
				radioButton.setUserData(option.value);
				radioButton.setTooltip(new Tooltip(option.description));
				radioButton.setToggleGroup(toggleGroup);
				getChildren().add(radioButton);
				radioButtons.add(radioButton);
				if (validData && controlProperty.getValue().toString().equals(option.value)) {
					toSelect = radioButton;
				}
			}

			if (toSelect != null) {
				toggleGroup.selectToggle(toSelect);
			}
			initListeners();
		}

		private void setEditorValue(@Nullable SerializableValue newValue) {
			if (newValue == null) {
				toggleGroup.selectToggle(null);
				return;
			}
			for (Toggle toggle : toggleGroup.getToggles()) {
				if (controlProperty.getValue() == null) {
					continue;
				}
				if (toggle.getUserData().equals(controlProperty.getValue().toString())) {
					toggleGroup.selectToggle(toggle);
					return;
				}
			}
		}

		@Override
		public boolean hasValidData() {
			return toggleGroup.getSelectedToggle() != null;
		}

		@NotNull
		@Override
		public ControlProperty getControlProperty() {
			return controlProperty;
		}

		@Override
		public void disableEditing(boolean disable) {
			setDisable(disable);
		}

		@Override
		public void setToMode(EditMode mode) {
			getChildren().clear();
			if (mode == EditMode.CUSTOM_DATA) {
				getChildren().add(rawInput);
			} else if (mode == EditMode.DEFAULT) {
				getChildren().addAll(radioButtons);
			}
		}

		@NotNull
		@Override
		public Node getRootNode() {
			return this;
		}

		@NotNull
		@Override
		public Class<? extends SerializableValue> getMacroClass() {
			return SVString.class;
		}

		@Override
		public void clearListeners() {
			toggleGroup.selectedToggleProperty().removeListener(toggleGroupListener);
			controlProperty.getValueObserver().removeListener(controlPropertyListener);
		}

		@Override
		public void initListeners() {
			toggleGroup.selectedToggleProperty().addListener(toggleGroupListener);
			controlProperty.getValueObserver().addListener(controlPropertyListener);
		}

		@Override
		public void refresh() {
			setEditorValue(controlProperty.getValue());
		}
	}

	static class ControlStyleEditor extends ControlStyleValueEditor implements ControlPropertyValueEditor {

		private final ControlProperty controlProperty;
		private final ReadOnlyValueListener<SVControlStyleGroup> editorValueListener = new ReadOnlyValueListener<SVControlStyleGroup>() {
			@Override
			public void valueUpdated(@NotNull ReadOnlyValueObserver<SVControlStyleGroup> observer, SVControlStyleGroup oldValue, SVControlStyleGroup newValue) {
				controlProperty.setValue(newValue);
			}
		};
		private final ValueListener<SerializableValue> controlPropertyListener = new ValueListener<SerializableValue>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<SerializableValue> observer, SerializableValue oldValue, SerializableValue newValue) {
				if (newValue == null) {
					menuButton.clearSelection();
				} else {
					setValue((SVControlStyleGroup) newValue);
				}
			}
		};

		public ControlStyleEditor(@Nullable ControlClass control, @NotNull ControlProperty controlProperty) {
			ControlPropertyValueEditor.modifyRawInput(getCustomDataTextField(), controlProperty);
			if (control instanceof ArmaControl) {
				menuButton.getItems().clear();
				menuButton.getItems().addAll(((ArmaControl) control).getAllowedStyles());
				for (com.kaylerrenslow.armaDialogCreator.control.ControlStyle style : menuButton.getItems()) {
					menuButton.bindTooltip(style, style.documentation);
				}
			}
			this.controlProperty = controlProperty;
			setValue((SVControlStyleGroup) controlProperty.getValue());
			initListeners();
		}

		@NotNull
		@Override
		public ControlProperty getControlProperty() {
			return controlProperty;
		}

		@Override
		public boolean hasValidData() {
			return menuButton.getSelectedItems().size() > 0;
		}

		@Override
		public void disableEditing(boolean disable) {
			setDisable(disable);
		}

		@Override
		public void setToMode(EditMode mode) {
			setToCustomData(mode == EditMode.CUSTOM_DATA);
		}

		@NotNull
		@Override
		public Node getRootNode() {
			return this;
		}

		@NotNull
		@Override
		public Class<? extends SerializableValue> getMacroClass() {
			return SVControlStyleGroup.class;
		}

		@Override
		public void clearListeners() {
			getReadOnlyObserver().removeListener(editorValueListener);
			controlProperty.getValueObserver().removeListener(controlPropertyListener);
		}

		@Override
		public void initListeners() {
			getReadOnlyObserver().addListener(editorValueListener);
			controlProperty.getValueObserver().addListener(controlPropertyListener);
		}

		@Override
		public void refresh() {
			setValue((SVControlStyleGroup) controlProperty.getValue());
		}
	}

	/**
	 Used for when the input is in a text field. The InputField class also allows for input verifying so that if something entered is wrong, the user will be notified.
	 Used for {@link SVDouble}, {@link SVInteger}, {@link SVString}, {@link SVExpression}
	 */
	@SuppressWarnings("unchecked")
	private static abstract class InputFieldEditor<C extends SerializableValue> extends InputFieldValueEditor<C> implements ControlPropertyValueEditor {

		private final ControlProperty controlProperty;
		private final Class<C> macroTypeClass;
		private final ReadOnlyValueListener<C> editorValueListener = new ReadOnlyValueListener<C>() {
			@Override
			public void valueUpdated(@NotNull ReadOnlyValueObserver<C> observer, C oldValue, C newValue) {
				controlProperty.setValue(newValue);
			}
		};
		private final ValueListener<SerializableValue> controlPropertyListener = new ValueListener<SerializableValue>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<SerializableValue> observer, @Nullable SerializableValue oldValue, @Nullable SerializableValue newValue) {
				if (controlProperty.getValue() != null) {
					inputField.setToButton(false);
					inputField.setValue((C) controlProperty.getValue());
				} else {
					setValue(null);
				}
			}
		};

		InputFieldEditor(@NotNull Class<C> macroTypeClass, @Nullable ControlClass control, @NotNull ControlProperty controlProperty, InputFieldDataChecker checker, @Nullable String promptText) {
			super(checker);
			this.macroTypeClass = macroTypeClass;

			this.controlProperty = controlProperty;
			ControlPropertyValueEditor.modifyRawInput(getCustomDataTextField(), controlProperty);

			inputField.setValue((C) controlProperty.getValue());
			if (promptText != null) {
				inputField.setPromptText(promptText);
			}
			initListeners();
		}

		@Override
		public boolean hasValidData() {
			return inputField.hasValidData();
		}

		@NotNull
		@Override
		public ControlProperty getControlProperty() {
			return controlProperty;
		}

		@Override
		public void disableEditing(boolean disable) {
			inputField.setDisable(disable);
		}


		@Override
		public void setToMode(EditMode mode) {
			setToCustomData(mode == EditMode.CUSTOM_DATA);
		}

		@NotNull
		@Override
		public Class<? extends SerializableValue> getMacroClass() {
			return this.macroTypeClass;
		}

		@Override
		public void clearListeners() {
			getReadOnlyObserver().removeListener(editorValueListener);
			controlProperty.getValueObserver().removeListener(controlPropertyListener);
		}

		@Override
		public void initListeners() {
			getReadOnlyObserver().addListener(editorValueListener);
			controlProperty.getValueObserver().addListener(controlPropertyListener);
		}

		@Override
		public void refresh() {
			setValue((C) controlProperty.getValue());
		}
	}

	static class StringEditor extends InputFieldEditor<SVString> {
		StringEditor(ControlClass control, ControlProperty controlProperty) {
			super(SVString.class, control, controlProperty, new SVArmaStringChecker(), Lang.LookupBundle().getString("PropertyType.string"));
		}
	}

	static class FloatEditor extends InputFieldEditor<SVExpression> {
		FloatEditor(ControlClass control, ControlProperty controlProperty) {
			super(SVExpression.class, control, controlProperty,
					new ExpressionChecker(ArmaDialogCreator.getApplicationData().getGlobalExpressionEnvironment(), ExpressionChecker.TYPE_FLOAT),
					Lang.LookupBundle().getString("PropertyType.float")
			);
		}
	}

	static class IntegerEditor extends InputFieldEditor<SVExpression> {
		IntegerEditor(ControlClass control, ControlProperty controlProperty) {
			super(SVExpression.class, control, controlProperty,
					new ExpressionChecker(ArmaDialogCreator.getApplicationData().getGlobalExpressionEnvironment(), ExpressionChecker.TYPE_INT),
					Lang.LookupBundle().getString("PropertyType.int")
			);
		}
	}

	/**
	 Used for when control property requires color input.
	 Use this only when the ControlProperty's value is of type {@link SVColor}
	 */
	static class ColorPickerEditor extends ColorValueEditor implements ControlPropertyValueEditor {

		private final ControlProperty controlProperty;
		private final ReadOnlyValueListener<SVColor> valueEditorListener = new ReadOnlyValueListener<SVColor>() {
			@Override
			public void valueUpdated(@NotNull ReadOnlyValueObserver<SVColor> observer, SVColor oldValue, SVColor newValue) {
				controlProperty.setValue(newValue);
			}
		};
		private final ValueListener<SerializableValue> controlPropertyListener = new ValueListener<SerializableValue>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<SerializableValue> observer, @Nullable SerializableValue oldValue, @Nullable SerializableValue newValue) {
				if (controlProperty.getValue() != null) { //maybe wasn't updated
					colorPicker.setValue(((SVColor) controlProperty.getValue()).toJavaFXColor());
				} else {
					colorPicker.setValue(null);
				}
			}
		};

		ColorPickerEditor(@Nullable ControlClass control, @NotNull ControlProperty controlProperty) {
			this.controlProperty = controlProperty;
			ControlPropertyValueEditor.modifyRawInput(getCustomDataTextField(), controlProperty);
			boolean validData = controlProperty.getValue() != null;
			if (validData) {
				SVColor value = (SVColor) controlProperty.getValue();
				colorPicker.setValue(value.toJavaFXColor());
			} else {
				colorPicker.setValue(null);
			}
			initListeners();
		}

		@Override
		public boolean hasValidData() {
			return colorPicker.getValue() != null;
		}

		@NotNull
		@Override
		public ControlProperty getControlProperty() {
			return controlProperty;
		}

		@Override
		public void disableEditing(boolean disable) {
			getRootNode().setDisable(disable);
		}


		@Override
		public void setToMode(EditMode mode) {
			setToCustomData(mode == EditMode.CUSTOM_DATA);
		}

		@NotNull
		@Override
		public Class<? extends SerializableValue> getMacroClass() {
			return SVColor.class;
		}

		@Override
		public void clearListeners() {
			getReadOnlyObserver().removeListener(valueEditorListener);
			controlProperty.getValueObserver().removeListener(controlPropertyListener);
		}

		@Override
		public void initListeners() {
			getReadOnlyObserver().addListener(valueEditorListener);
			controlProperty.getValueObserver().addListener(controlPropertyListener);
		}

		@Override
		public void refresh() {
			setValue((SVColor) controlProperty.getValue());
		}
	}

	/**
	 Used for boolean control properties
	 Use this editor for when the ControlProperty's value is of type {@link SVBoolean}
	 */
	static class BooleanChoiceBoxEditor extends BooleanValueEditor implements ControlPropertyValueEditor {

		private final ControlProperty controlProperty;
		private final ReadOnlyValueListener<SVBoolean> editorValueListener = new ReadOnlyValueListener<SVBoolean>() {
			@Override
			public void valueUpdated(@NotNull ReadOnlyValueObserver<SVBoolean> observer, SVBoolean oldValue, SVBoolean newValue) {
				controlProperty.setValue(newValue);
			}
		};
		private final ValueListener<SerializableValue> controlPropertyListener = new ValueListener<SerializableValue>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<SerializableValue> observer, @Nullable SerializableValue oldValue, @Nullable SerializableValue newValue) {
				choiceBox.setValue(newValue == null ? null : ((SVBoolean) newValue).isTrue());
			}
		};

		BooleanChoiceBoxEditor(@Nullable ControlClass control, @NotNull ControlProperty controlProperty) {
			this.controlProperty = controlProperty;
			ControlPropertyValueEditor.modifyRawInput(getCustomDataTextField(), controlProperty);

			boolean validData = controlProperty.getValue() != null;
			if (validData) {
				choiceBox.setValue(controlProperty.getBooleanValue());
			}
			initListeners();

		}

		@Override
		public boolean hasValidData() {
			return choiceBox.getValue() != null;
		}

		@NotNull
		@Override
		public ControlProperty getControlProperty() {
			return controlProperty;
		}

		@Override
		public void disableEditing(boolean disable) {
			getRootNode().setDisable(disable);
		}

		@Override
		public void setToMode(EditMode mode) {
			setToCustomData(mode == EditMode.CUSTOM_DATA);
		}

		@NotNull
		@Override
		public Class<? extends SerializableValue> getMacroClass() {
			return SVBoolean.class;
		}

		@Override
		public void clearListeners() {
			getReadOnlyObserver().removeListener(editorValueListener);
			controlProperty.getValueObserver().removeListener(controlPropertyListener);
		}

		@Override
		public void initListeners() {
			getReadOnlyObserver().addListener(editorValueListener);
			controlProperty.getValueObserver().addListener(controlPropertyListener);
		}

		@Override
		public void refresh() {
			setValue((SVBoolean) controlProperty.getValue());
		}
	}

	/**
	 Used for control properties that require more than one input
	 This editor will use {@link SVStringArray} as the ControlProperty's value type
	 */
	@SuppressWarnings("unchecked")
	static class ArrayEditor extends ArrayValueEditor implements ControlPropertyValueEditor {

		private final ControlProperty controlProperty;
		private final ReadOnlyValueListener<SVStringArray> editorValueListener = new ReadOnlyValueListener<SVStringArray>() {
			@Override
			public void valueUpdated(@NotNull ReadOnlyValueObserver<SVStringArray> observer, SVStringArray oldValue, SVStringArray newValue) {
				controlProperty.setValue(newValue);
			}
		};
		private final ValueListener<SerializableValue> controlPropertyListener = new ValueListener<SerializableValue>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<SerializableValue> observer, @Nullable SerializableValue oldValue, @Nullable SerializableValue newValue) {
				setValue((SVStringArray) newValue);
			}
		};

		ArrayEditor(@Nullable ControlClass control, @NotNull ControlProperty controlProperty, int defaultNumFields) {
			super(defaultNumFields);
			if (true) {
				throw new RuntimeException("review this code to check for correctness");
			}
			this.controlProperty = controlProperty;
			ControlPropertyValueEditor.modifyRawInput(getCustomDataTextField(), controlProperty);

			setValue((SVStringArray) controlProperty.getValue());
			initListeners();
		}

		@Override
		public boolean hasValidData() {
			for (InputField inputField : editors) {
				if (!inputField.hasValidData()) {
					return false;
				}
			}
			return true;
		}

		@NotNull
		@Override
		public ControlProperty getControlProperty() {
			return controlProperty;
		}

		@Override
		public void disableEditing(boolean disable) {
			getRootNode().setDisable(disable);
		}

		@Override
		public void setToMode(EditMode mode) {
			setToCustomData(mode == EditMode.CUSTOM_DATA);
		}

		@NotNull
		@Override
		public Class<? extends SerializableValue> getMacroClass() {
			return SVStringArray.class;
		}

		@Override
		public void clearListeners() {
			getReadOnlyObserver().removeListener(editorValueListener);
			controlProperty.getValueObserver().removeListener(controlPropertyListener);
		}

		@Override
		public void initListeners() {
			getReadOnlyObserver().addListener(editorValueListener);
			controlProperty.getValueObserver().addListener(controlPropertyListener);
		}

		@Override
		public void refresh() {
			setValue((SVStringArray) controlProperty.getValue());
		}
	}

	/**
	 Used for control property font picking
	 Used for ControlProperty instances where it's value is {@link SVFont}
	 */
	static class FontChoiceBoxEditor extends FontValueEditor implements ControlPropertyValueEditor {

		private final ControlProperty controlProperty;
		private final ReadOnlyValueListener<SVFont> editorValueListener = new ReadOnlyValueListener<SVFont>() {
			@Override
			public void valueUpdated(@NotNull ReadOnlyValueObserver<SVFont> observer, SVFont oldValue, SVFont newValue) {
				controlProperty.setValue(newValue);
			}
		};
		private final ValueListener<SerializableValue> controlPropertyListener = new ValueListener<SerializableValue>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<SerializableValue> observer, @Nullable SerializableValue oldValue, @Nullable SerializableValue newValue) {
				comboBox.setValue((SVFont) controlProperty.getValue());
			}
		};

		FontChoiceBoxEditor(@Nullable ControlClass control, @NotNull ControlProperty controlProperty) {
			this.controlProperty = controlProperty;
			ControlPropertyValueEditor.modifyRawInput(getCustomDataTextField(), controlProperty);

			setValue((SVFont) controlProperty.getValue());
			initListeners();
		}

		@Override
		public boolean hasValidData() {
			return !comboBox.getSelectionModel().isEmpty();
		}

		@NotNull
		@Override
		public ControlProperty getControlProperty() {
			return controlProperty;
		}

		@Override
		public void disableEditing(boolean disable) {
			getRootNode().setDisable(disable);
		}


		@Override
		public void setToMode(EditMode mode) {
			setToCustomData(mode == EditMode.CUSTOM_DATA);
		}

		@NotNull
		@Override
		public Class<? extends SerializableValue> getMacroClass() {
			return SVFont.class;
		}

		@Override
		public void clearListeners() {
			getReadOnlyObserver().removeListener(editorValueListener);
			controlProperty.getValueObserver().removeListener(controlPropertyListener);
		}

		@Override
		public void initListeners() {
			getReadOnlyObserver().addListener(editorValueListener);
			controlProperty.getValueObserver().addListener(controlPropertyListener);
		}

		@Override
		public void refresh() {
			setValue((SVFont) controlProperty.getValue());
		}
	}

	/**
	 Used for control property image editing
	 Used for ControlProperty instances where it's value is {@link SVImage}
	 */
	static class ImageEditor extends ImageValueEditor implements ControlPropertyValueEditor {

		private final ControlProperty controlProperty;
		private final ReadOnlyValueListener<SVImage> editorValueListener = new ReadOnlyValueListener<SVImage>() {
			@Override
			public void valueUpdated(@NotNull ReadOnlyValueObserver<SVImage> observer, SVImage oldValue, SVImage newValue) {
				controlProperty.setValue(newValue);
			}
		};
		private final ValueListener<SerializableValue> controlPropertyListener = new ValueListener<SerializableValue>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<SerializableValue> observer, @Nullable SerializableValue oldValue, @Nullable SerializableValue newValue) {
				ImageEditor.this.setValue((SVImage) controlProperty.getValue());
			}
		};

		ImageEditor(@Nullable ControlClass control, @NotNull ControlProperty controlProperty) {
			this.controlProperty = controlProperty;
			ControlPropertyValueEditor.modifyRawInput(getCustomDataTextField(), controlProperty);

			setValue((SVImage) controlProperty.getValue());
			initListeners();
		}

		@Override
		public boolean hasValidData() {
			return true;
		}

		@NotNull
		@Override
		public ControlProperty getControlProperty() {
			return controlProperty;
		}

		@Override
		public void disableEditing(boolean disable) {
			getRootNode().setDisable(disable);
		}


		@Override
		public void setToMode(EditMode mode) {
			setToCustomData(mode == EditMode.CUSTOM_DATA);
		}

		@NotNull
		@Override
		public Class<? extends SerializableValue> getMacroClass() {
			return SVImage.class;
		}

		@Override
		public void clearListeners() {
			getReadOnlyObserver().removeListener(editorValueListener);
			controlProperty.getValueObserver().removeListener(controlPropertyListener);
		}

		@Override
		public void initListeners() {
			getReadOnlyObserver().addListener(editorValueListener);
			controlProperty.getValueObserver().addListener(controlPropertyListener);
		}

		@Override
		public void refresh() {
			setValue((SVImage) controlProperty.getValue());
		}
	}

	/** Use this editor for {@link SVSound} ControlProperty values */
	static class SoundEditor extends SoundValueEditor implements ControlPropertyValueEditor {

		private final ControlProperty controlProperty;
		private final ReadOnlyValueListener<SVSound> editorValueListener = new ReadOnlyValueListener<SVSound>() {
			@Override
			public void valueUpdated(@NotNull ReadOnlyValueObserver<SVSound> observer, SVSound oldValue, SVSound newValue) {
				controlProperty.setValue(newValue);
			}
		};
		private final ValueListener<SerializableValue> controlPropertyListener = new ValueListener<SerializableValue>() {
			@Override
			public void valueUpdated(@NotNull ValueObserver<SerializableValue> observer, @Nullable SerializableValue oldValue, @Nullable SerializableValue newValue) {
				setValue((SVSound) newValue);
			}
		};

		public SoundEditor(@Nullable ControlClass control, @NotNull ControlProperty controlProperty) {
			this.controlProperty = controlProperty;

			ControlPropertyValueEditor.modifyRawInput(getCustomDataTextField(), controlProperty);

			initListeners();

		}

		@NotNull
		@Override
		public ControlProperty getControlProperty() {
			return controlProperty;
		}

		@Override
		public boolean hasValidData() {
			return getValue() != null;
		}

		@Override
		public void disableEditing(boolean disable) {
			getRootNode().setDisable(disable);
		}

		@Override
		public void setToMode(EditMode mode) {
			setToCustomData(mode == EditMode.CUSTOM_DATA);
		}

		@NotNull
		@Override
		public Class<? extends SerializableValue> getMacroClass() {
			return SVSound.class;
		}

		@Override
		public void clearListeners() {
			getReadOnlyObserver().removeListener(editorValueListener);
			controlProperty.getValueObserver().removeListener(controlPropertyListener);
		}

		@Override
		public void initListeners() {
			getReadOnlyObserver().addListener(editorValueListener);
			controlProperty.getValueObserver().addListener(controlPropertyListener);
		}

		@Override
		public void refresh() {
			setValue((SVSound) controlProperty.getValue());
		}
	}
}
