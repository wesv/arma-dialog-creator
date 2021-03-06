package com.armadialogcreator.gui.main.popup.editor;

import com.armadialogcreator.control.ControlProperty;
import com.armadialogcreator.gui.popup.StageDialog;
import com.armadialogcreator.main.Lang;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.ResourceBundle;

/**
 Created by Kayler on 06/02/2016.
 */
class MissingControlPropertiesConfigDialog extends StageDialog<VBox> {
	/**
	 */
	public MissingControlPropertiesConfigDialog(@Nullable Stage primaryStage, @NotNull List<ControlProperty> missing) {
		super(primaryStage, new VBox(5), null, true, true, false);

		ResourceBundle bundle = Lang.getBundle("ControlPropertyEditorBundle");

		setTitle(bundle.getString("MissingControlPropertiesConfig.popup_title"));

		ListView<String> listView = new ListView<>();
		for (ControlProperty controlProperty : missing) {
			listView.getItems().add(controlProperty.getName());
		}
		getFooter().getBtnOk().setText(bundle.getString("MissingControlPropertiesConfig.close_anyway"));
		getFooter().getBtnOk().setPrefWidth(180);
		myRootElement.getChildren().addAll(new Label(bundle.getString("MissingControlPropertiesConfig.missing_properties_message")), listView);
		myRootElement.setFillWidth(true);
		setStageSize(500, 300);
	}
}
