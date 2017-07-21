package com.kaylerrenslow.armaDialogCreator.gui.main.popup.newControl;

import com.kaylerrenslow.armaDialogCreator.arma.control.impl.ArmaControlLookup;
import com.kaylerrenslow.armaDialogCreator.control.ControlClass;
import com.kaylerrenslow.armaDialogCreator.control.ControlPropertyUpdate;
import com.kaylerrenslow.armaDialogCreator.control.ControlType;
import com.kaylerrenslow.armaDialogCreator.control.CustomControlClass;
import com.kaylerrenslow.armaDialogCreator.data.Project;
import com.kaylerrenslow.armaDialogCreator.data.export.ProjectExporter;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.BorderedImageView;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.CBMBGroupMenu;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.CBMBMenuItem;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.ComboBoxMenuButton;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.inputfield.IdentifierChecker;
import com.kaylerrenslow.armaDialogCreator.gui.fxcontrol.inputfield.InputField;
import com.kaylerrenslow.armaDialogCreator.gui.main.controlPropertiesEditor.ControlPropertiesEditorPane;
import com.kaylerrenslow.armaDialogCreator.gui.main.fxControls.ControlClassMenuButton;
import com.kaylerrenslow.armaDialogCreator.gui.main.fxControls.ControlClassMenuItem;
import com.kaylerrenslow.armaDialogCreator.gui.popup.SimpleResponseDialog;
import com.kaylerrenslow.armaDialogCreator.gui.popup.StageDialog;
import com.kaylerrenslow.armaDialogCreator.main.ArmaDialogCreator;
import com.kaylerrenslow.armaDialogCreator.main.ExceptionHandler;
import com.kaylerrenslow.armaDialogCreator.main.HelpUrls;
import com.kaylerrenslow.armaDialogCreator.main.Lang;
import com.kaylerrenslow.armaDialogCreator.util.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

/**
 Dialog window that allows for creating a new custom control ({@link CustomControlClass}).
 It has a control properties editor on the left and a preview window on the right to preview the outputted .h file text

 @author Kayler
 @since 07/06/2016. */
public class NewCustomControlClassDialog extends StageDialog<VBox> {

	private final StackPane stackPaneProperties = new StackPane();
	private final TextArea taPreviewSample = new TextArea();
	private final InputField<IdentifierChecker, String> inClassName = new InputField<>(new IdentifierChecker());
	private final Label lblBaseControl;
	private final TextArea taComment = new TextArea();
	private final ControlClassMenuButton extendClassMenuButton;

	private ControlPropertiesEditorPane editorPane;

	private final ResourceBundle bundle = Lang.ApplicationBundle();

	private final UpdateGroupListener<ControlPropertyUpdate> controlClassListener = new UpdateGroupListener<ControlPropertyUpdate>() {
		@Override
		public void update(@NotNull UpdateListenerGroup<ControlPropertyUpdate> group, ControlPropertyUpdate data) {
			updatePreview();
		}
	};

	private final Project project = Project.getCurrentProject();

	public NewCustomControlClassDialog() {
		super(ArmaDialogCreator.getPrimaryStage(), new VBox(5), null, true, true, true);
		setTitle(bundle.getString("Popups.NewCustomControl.popup_title"));

		/*HEADER*/
		{
			final HBox hboxHeader = new HBox(10);
			hboxHeader.setFillHeight(true);

			//control class name
			{
				final Label lblControlClassName = new Label(bundle.getString("Popups.NewCustomControl.control_class_name"));
				lblControlClassName.setFont(Font.font(18d));
				hboxHeader.getChildren().add(lblControlClassName);
				hboxHeader.getChildren().add(inClassName);

				inClassName.getValueObserver().addListener(new ValueListener<String>() {
					@Override
					public void valueUpdated(@NotNull ValueObserver<String> observer, String oldValue, String newValue) {
						newValue = newValue != null ? newValue : "";
						editorPane.getControlClass().setClassName(newValue);
						updatePreview();
					}
				});
			}

			//extend/parent class
			{
				hboxHeader.getChildren().add(new Label(":"));
				extendClassMenuButton = new ControlClassMenuButton(
						true,
						bundle.getString("Popups.NewCustomControl.no_parent_class"),
						null
				);

				hboxHeader.getChildren().add(extendClassMenuButton);

				extendClassMenuButton.addItems(getCustomControlClassesItems());
				extendClassMenuButton.getSelectedValueObserver().addListener((observer, oldValue, newValue) -> {
					if (getEditorPane() == null) {
						return;
					}
					ControlClass editClass = getEditorPane().getControlClass();
					if (newValue != null) {
						if (editClass.hasInheritanceLoop(newValue)) {
							SimpleResponseDialog dialog = new SimpleResponseDialog(
									myStage,
									bundle.getString("Popups.NewCustomControl.inheritance_loop_title"),
									String.format(
											bundle.getString("Popups.NewCustomControl.inheritance_loop_body_f"),
											editClass.getClassName()
									),
									false, true, false
							);
							dialog.setStageSize(400, 120);
							dialog.show();
							extendClassMenuButton.chooseItem((ControlClass) null);
							return;
						}
					}
					editClass.extendControlClass(newValue);
					updatePreview();
				});

			}

			//templates
			{
				List<CBMBMenuItem<String>> controlTypeControlClasses = new ArrayList<>(
						ControlType.AVAILABLE_TYPES.length
				);
				CBMBMenuItem<String> toSelect = null;
				for (int i = 0; i < ControlType.AVAILABLE_TYPES.length; i++) {
					ArmaControlLookup lookup = ArmaControlLookup.findByControlType(ControlType.AVAILABLE_TYPES[i]);
					CBMBMenuItem<String> menuItem = new CBMBMenuItem<>(
							lookup.controlType.getDisplayName(),
							new BorderedImageView(lookup.controlType.getIcon())
					);
					menuItem.setUserData(lookup);
					controlTypeControlClasses.add(menuItem);
					if (lookup.controlType == ControlType.Static) {
						toSelect = menuItem;
					}
				}
				final ComboBoxMenuButton<String> templateControlMenuButton = new ComboBoxMenuButton<>(
						false, "", null,
						new CBMBGroupMenu<>(
								bundle.getString("Popups.NewCustomControl.control_types"),
								controlTypeControlClasses
						)
				);
				{
					CBMBGroupMenu<String> customControlsMenu = new CBMBGroupMenu<>(
							bundle.getString("Popups.NewCustomControl.custom_controls"),
							getCustomControlClassesNamesItems()
					);
					if (customControlsMenu.getItems().size() > 0) {
						templateControlMenuButton.addGroup(customControlsMenu);
					}
				}
				templateControlMenuButton.getSelectedItemObserver().addListener((observer, oldValue, selectedItem) -> {
					if (selectedItem == null) {
						throw new IllegalStateException("selectedItem shouldn't be null");
					}
					if (selectedItem.getUserData() instanceof ArmaControlLookup) {
						ArmaControlLookup lookup = (ArmaControlLookup) selectedItem.getUserData();
						ControlClass cc = new ControlClass(
								"Custom_" + lookup.controlType.getNameAsClassName(),
								lookup.specProvider,
								project
						);
						setToControlClass(cc);
					} else if (selectedItem.getUserData() instanceof CustomControlClass) {
						CustomControlClass ccc = (CustomControlClass) selectedItem.getUserData();
						setToControlClass(ccc.newSpecification().constructNewControlClass(project));
					} else {
						throw new IllegalStateException("unknown user data");
					}
				});

				templateControlMenuButton.chooseItem(toSelect);

				lblBaseControl = new Label(bundle.getString("Popups.NewCustomControl.template"), templateControlMenuButton);
				lblBaseControl.setContentDisplay(ContentDisplay.RIGHT);
				hboxHeader.getChildren().add(lblBaseControl);
			}

			myRootElement.getChildren().add(hboxHeader);
		}


		myRootElement.getChildren().add(new Separator(Orientation.HORIZONTAL));

		//BODY
		{
			final HBox hboxBody = new HBox(10);

			VBox.setVgrow(hboxBody, Priority.ALWAYS);

			final VBox vboxProperties = new VBox(5, new Label(bundle.getString("Popups.NewCustomControl.properties")), stackPaneProperties);
			VBox.setVgrow(vboxProperties, Priority.ALWAYS);
			VBox.setVgrow(stackPaneProperties, Priority.ALWAYS);
			hboxBody.getChildren().add(vboxProperties);

			final VBox vboxComment = new VBox(5, new Label(bundle.getString("Popups.NewCustomControl.comment")), taComment);
			vboxComment.setFillWidth(true);
			VBox.setVgrow(taComment, Priority.ALWAYS);
			VBox.setVgrow(vboxComment, Priority.ALWAYS);
			taComment.focusedProperty().addListener(new ChangeListener<Boolean>() {
				@Override
				public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean focused) {
					if (!focused) {
						updatePreview();
					}
				}
			});

			final VBox vboxPreview = new VBox(5, new Label(bundle.getString("Popups.NewCustomControl.preview_sample")), taPreviewSample);
			taPreviewSample.setEditable(false);
			vboxPreview.setFillWidth(true);
			VBox.setVgrow(taPreviewSample, Priority.ALWAYS);
			VBox.setVgrow(vboxPreview, Priority.ALWAYS);

			final SplitPane splitPane = new SplitPane(vboxComment, vboxPreview);
			splitPane.setStyle("-fx-background-color:transparent;"); //remove border
			splitPane.setPrefWidth(300d);
			splitPane.setOrientation(Orientation.VERTICAL);

			hboxBody.getChildren().add(splitPane);

			for (Node n : hboxBody.getChildren()) {
				HBox.setHgrow(n, Priority.ALWAYS);
			}

			myRootElement.getChildren().add(hboxBody);
		}

		myStage.sizeToScene();
	}

	private List<CBMBMenuItem<String>> getCustomControlClassesNamesItems() {
		ReadOnlyList<CustomControlClass> cccList = project.getProjectCustomControlClassRegistry().getControlClassList();
		List<CBMBMenuItem<String>> items = new ArrayList<>(cccList.size());
		for (CustomControlClass ccc : cccList) {
			CBMBMenuItem<String> menuItem = new CBMBMenuItem<>(ccc.getControlClass().getClassName());
			menuItem.setUserData(ccc);
			items.add(menuItem);
		}
		return items;
	}

	private List<CBMBMenuItem<ControlClass>> getCustomControlClassesItems() {
		ReadOnlyList<CustomControlClass> cccList = project.getProjectCustomControlClassRegistry().getControlClassList();
		List<CBMBMenuItem<ControlClass>> items = new ArrayList<>(cccList.size());
		for (CustomControlClass ccc : cccList) {
			items.add(new ControlClassMenuItem(ccc.getControlClass()));
		}
		return items;
	}

	protected void hideBaseControlMenuButton(boolean hidden) {
		lblBaseControl.setVisible(!hidden);
	}

	/**
	 Set the editor to the given {@link ControlClass} instance.
	 <b>Beware: the given {@link ControlClass} will be edited regardless of whether the user presses ok or not.</b>

	 @param controlClass instance to edit
	 */
	protected void setToControlClass(@NotNull ControlClass controlClass) {
		if (editorPane != null) {
			removeListeners();
		}
		editorPane = new ControlPropertiesEditorPane(controlClass);
		stackPaneProperties.getChildren().clear();
		stackPaneProperties.getChildren().add(editorPane);

		controlClass.getPropertyUpdateGroup().addListener(controlClassListener);
		inClassName.setValue(controlClass.getClassName());
		extendClassMenuButton.chooseItem(controlClass.getExtendClass());
		updatePreview();
	}

	@NotNull
	protected InputField<IdentifierChecker, String> getInClassName() {
		return inClassName;
	}

	@NotNull
	protected TextArea getTaComment() {
		return taComment;
	}

	@Nullable
	protected ControlPropertiesEditorPane getEditorPane() {
		return editorPane;
	}

	@Override
	protected void closing() {
		removeListeners();
	}

	private void removeListeners() {
		editorPane.getControlClass().getPropertyUpdateGroup().removeListener(controlClassListener);
		editorPane.unlink();
	}

	@Override
	protected void ok() {
		if (inClassName.getValue() == null) {
			beep();
			inClassName.requestFocus();
			return;
		}
		CustomControlClass customControlClass = new CustomControlClass(editorPane.getControlClass());
		customControlClass.setComment(taComment.getText());
		project.getProjectCustomControlClassRegistry().addControlClass(customControlClass);

		super.ok();
	}

	@Override
	protected void help() {
		BrowserUtil.browse(HelpUrls.NEW_CUSTOM_CONTROL_POPUP);
	}

	private void updatePreview() {
		taPreviewSample.setText(getPreviewText());
	}

	private String getPreviewText() {
		ByteArrayOutputStream stream = new ByteArrayOutputStream(128);
		try {
			if (getTaComment().getText() != null && getTaComment().getText().length() > 0) {
				stream.write("/*\n".getBytes());
				stream.write(getTaComment().getText().getBytes());
				stream.write("\n*/\n".getBytes());
			}
			ProjectExporter.exportControlClass(project.getExportConfiguration(), editorPane.getControlClass(), stream);
			stream.close();
		} catch (IOException e) {
			ExceptionHandler.error(e);
		}
		return stream.toString();
	}
}
