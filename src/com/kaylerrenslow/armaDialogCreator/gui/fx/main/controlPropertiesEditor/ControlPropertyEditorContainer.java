/*
 * Copyright (c) 2016 Kayler Renslow
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the "Software"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.
 *
 * The software is provided "as is", without warranty of any kind, express or implied, including but not limited to the warranties of merchantability, fitness for a particular purpose and noninfringement. in no event shall the authors or copyright holders be liable for any claim, damages or other liability, whether in an action of contract, tort or otherwise, arising from, out of or in connection with the software or the use or other dealings in the software.
 */

package com.kaylerrenslow.armaDialogCreator.gui.fx.main.controlPropertiesEditor;

import com.kaylerrenslow.armaDialogCreator.control.*;
import com.kaylerrenslow.armaDialogCreator.control.sv.SerializableValue;
import com.kaylerrenslow.armaDialogCreator.gui.fx.popup.SimpleResponseDialog;
import com.kaylerrenslow.armaDialogCreator.main.ArmaDialogCreator;
import com.kaylerrenslow.armaDialogCreator.main.Lang;
import com.kaylerrenslow.armaDialogCreator.util.UpdateListenerGroup;
import com.kaylerrenslow.armaDialogCreator.util.ValueListener;
import com.kaylerrenslow.armaDialogCreator.util.ValueObserver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SeparatorMenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import org.jetbrains.annotations.NotNull;

/**
 @author Kayler
 @since 11/20/2016 */
class ControlPropertyEditorContainer extends HBox {
	private final ControlPropertyValueEditor propertyInput;
	private final ControlClass controlClass;
	private ControlPropertyUpdateListener controlPropertyUpdateListener;
	private ControlClassUpdateListener controlClassUpdateListener;
	private StackPane stackPanePropertyInput;
	private MenuItem miInheritanceButton;

	public ControlPropertyEditorContainer(@NotNull ControlClass controlClass, @NotNull ControlPropertyValueEditor input) {
		super(5);
		this.controlClass = controlClass;
		this.propertyInput = input;
		setAlignment(Pos.TOP_LEFT);

		init();
	}

	private void init() {
		stackPanePropertyInput = new StackPane();
		final ControlProperty property = propertyInput.getControlProperty();

		propertyInput.disableEditing(property.getPropertyLookup() == ControlPropertyLookup.TYPE);

		if (propertyInput.displayFullWidth()) {
			HBox.setHgrow(stackPanePropertyInput, Priority.ALWAYS);
		}

		if (property.getMacro() != null) {
			updatePropertyInputMode(stackPanePropertyInput, propertyInput, ControlPropertyValueEditor.EditMode.MACRO);
		}

		final MenuItem miDefaultEditor = new MenuItem(Lang.ApplicationBundle().getString("ControlPropertiesEditorPane.use_default_editor"));
		final MenuItem miResetToInitial = new MenuItem(Lang.ApplicationBundle().getString("ControlPropertiesEditorPane.reset_to_initial"));
		final MenuItem miMacro = new MenuItem(Lang.ApplicationBundle().getString("ControlPropertiesEditorPane.set_to_macro"));
		final MenuItem miCustomData = new MenuItem(Lang.ApplicationBundle().getString("ControlPropertiesEditorPane.value_custom_data"));//broken. Maybe fix it later. Don't delete this in case you change your mind
		miInheritanceButton = new MenuItem(
				property.isInherited() ? Lang.ApplicationBundle().getString("ControlPropertiesEditorPane.override") :
						Lang.ApplicationBundle().getString("ControlPropertiesEditorPane.inherit")
		);
		final MenuItem miClearValue = new MenuItem(Lang.ApplicationBundle().getString("ControlPropertiesEditorPane.clear_value"));
		final MenuButton menuButton = new MenuButton(property.getName(), null, miDefaultEditor, new SeparatorMenuItem(), miResetToInitial, miMacro, miInheritanceButton, miClearValue/*,miCustomData*/);
		ControlPropertiesEditorPane.placeTooltip(menuButton, propertyInput.getControlProperty().getPropertyLookup());

		controlClassUpdateListener = new ControlClassUpdateListener(controlClass) {
			@Override
			public void update(@NotNull UpdateListenerGroup<ControlClassUpdate> group, ControlClassUpdate data) {
				if (data instanceof ControlClassExtendUpdate) {
					ControlClassExtendUpdate update = (ControlClassExtendUpdate) data;
					miInheritanceButton.setVisible(update.getNewValue() != null);
				}
			}
		};
		controlClass.getControlClassUpdateGroup().addListener(controlClassUpdateListener);

		if (property.getPropertyLookup() instanceof ControlPropertyLookup) {
			switch ((ControlPropertyLookup) property.getPropertyLookup()) {
				case TYPE: {
					for (MenuItem item : menuButton.getItems()) {
						item.setDisable(true);
					}
					break;
				}
				//intentional fallthrough for all below properties in case statements
				case STYLE:
				case X:
				case Y:
				case W:
				case H: {
					miCustomData.setDisable(true);//NEVER allow custom input
					break;
				}
			}
		}

		controlPropertyUpdateListener = new ControlPropertyUpdateListener(property) {
			@Override
			public void update(@NotNull UpdateListenerGroup<ControlPropertyUpdate> group, ControlPropertyUpdate data) {
				if (data instanceof ControlPropertyInheritUpdate) {
					ControlPropertyInheritUpdate update = (ControlPropertyInheritUpdate) data;
					boolean disable = update.wasInherited();
					stackPanePropertyInput.setDisable(disable);
					miCustomData.setDisable(disable);
					miDefaultEditor.setDisable(disable);
					miResetToInitial.setDisable(disable);
					miMacro.setDisable(disable);
					miClearValue.setDisable(disable);
					if (update.wasInherited()) {
						miInheritanceButton.setText(Lang.ApplicationBundle().getString("ControlPropertiesEditorPane.override"));
					} else {
						miInheritanceButton.setText(Lang.ApplicationBundle().getString("ControlPropertiesEditorPane.inherit"));
					}
				}
			}
		};
		property.getControlPropertyUpdateGroup().addListener(controlPropertyUpdateListener);


		stackPanePropertyInput.getChildren().add(propertyInput.getRootNode());
		getChildren().addAll(menuButton, new Label("="), stackPanePropertyInput);

		miResetToInitial.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				getControlProperty().setValue(getControlProperty().getDefaultValue());
			}
		});
		miDefaultEditor.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				updatePropertyInputMode(stackPanePropertyInput, propertyInput, ControlPropertyValueEditor.EditMode.DEFAULT);
			}
		});
		miMacro.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				updatePropertyInputMode(stackPanePropertyInput, propertyInput, ControlPropertyValueEditor.EditMode.MACRO);

			}
		});
		miCustomData.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				updatePropertyInputMode(stackPanePropertyInput, propertyInput, ControlPropertyValueEditor.EditMode.CUSTOM_DATA);
			}
		});
		miInheritanceButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (getControlProperty().isInherited()) {
					getControlProperty().inherit(null);
				} else {
					controlClass.inheritProperty(getControlProperty().getPropertyLookup());
				}
			}
		});
		miClearValue.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				if (getControlProperty().getValue() == null) {
					return;
				}
				if (getControlProperty().getMacro() != null) {
					if (!askClearMacro()) {
						return;
					}
				}

				SimpleResponseDialog dialog = new SimpleResponseDialog(
						ArmaDialogCreator.getPrimaryStage(),
						Lang.ApplicationBundle().getString("ControlPropertiesEditorPane.ClearValuePopup.popup_title"),
						Lang.ApplicationBundle().getString("ControlPropertiesEditorPane.ClearValuePopup.body"), true, true, false
				);
				dialog.getFooter().getBtnCancel().setText(Lang.ApplicationBundle().getString("Confirmation.no"));
				dialog.getFooter().getBtnOk().setText(Lang.ApplicationBundle().getString("Confirmation.yes"));
				dialog.setStageSize(300, 120);
				dialog.show();
				if (dialog.wasCancelled()) {
					return;
				}
				getControlProperty().setValue((SerializableValue) null);
			}
		});

		updateContainer();
	}

	private void updateContainer() {
		miInheritanceButton.setVisible(controlClass.getExtendClass() != null);
		if (getControlProperty().isCustomData()) {
			updatePropertyInputMode(stackPanePropertyInput, propertyInput, ControlPropertyValueEditor.EditMode.CUSTOM_DATA);
		} else if (getControlProperty().getMacro() != null) {
			updatePropertyInputMode(stackPanePropertyInput, propertyInput, ControlPropertyValueEditor.EditMode.MACRO);
		}
	}

	@SuppressWarnings("unchecked")
	private void updatePropertyInputMode(StackPane stackPanePropertyInput, ControlPropertyValueEditor propertyInput, ControlPropertyValueEditor.EditMode mode) {
		if (mode == ControlPropertyValueEditor.EditMode.MACRO) {
			stackPanePropertyInput.getChildren().clear();

			MacroGetterButton<? extends SerializableValue> macroGetterButton = new MacroGetterButton(propertyInput.getMacroClass(), propertyInput.getControlProperty().getMacro());
			stackPanePropertyInput.getChildren().add(macroGetterButton);
			macroGetterButton.getChosenMacroValueObserver().addListener(new ValueListener() {
				@Override
				public void valueUpdated(@NotNull ValueObserver observer, Object oldValue, Object newValue) {
					Macro m = (Macro) newValue;
					propertyInput.getControlProperty().setValueToMacro(m);
				}
			});
		} else {
			if (propertyInput.getControlProperty().getMacro() != null) {
				if (!askClearMacro()) {
					return;
				}
				propertyInput.getControlProperty().setValueToMacro(null);
			}
			stackPanePropertyInput.getChildren().clear();
			stackPanePropertyInput.getChildren().add(propertyInput.getRootNode());
			propertyInput.setToMode(mode);
			propertyInput.getControlProperty().setHasCustomData(mode == ControlPropertyValueEditor.EditMode.CUSTOM_DATA);
		}
	}

	private boolean askClearMacro() {
		SimpleResponseDialog dialog = new SimpleResponseDialog(
				ArmaDialogCreator.getPrimaryStage(),
				Lang.ApplicationBundle().getString("ControlPropertiesEditorPane.RemoveMacroDialog.dialog_title"),
				Lang.ApplicationBundle().getString("ControlPropertiesEditorPane.RemoveMacroDialog.body"),
				true, true, false
		);
		dialog.getFooter().getBtnCancel().setText(Lang.ApplicationBundle().getString("Confirmation.no"));
		dialog.getFooter().getBtnOk().setText(Lang.ApplicationBundle().getString("Confirmation.yes"));
		dialog.show();
		return !dialog.wasCancelled();
	}

	public void unlink() {
		propertyInput.clearListeners();
		controlClass.getControlClassUpdateGroup().removeListener(this.controlClassUpdateListener);
		getControlProperty().getControlPropertyUpdateGroup().removeListener(this.controlPropertyUpdateListener);
	}

	public void link() {
		propertyInput.initListeners();
		propertyInput.refresh();
		controlClass.getControlClassUpdateGroup().addListener(this.controlClassUpdateListener);
		getControlProperty().getControlPropertyUpdateGroup().addListener(this.controlPropertyUpdateListener);
		updateContainer();
	}

	@NotNull
	public ControlPropertyValueEditor getPropertyInput() {
		return propertyInput;
	}

	@NotNull
	public ControlProperty getControlProperty() {
		return getPropertyInput().getControlProperty();
	}
}