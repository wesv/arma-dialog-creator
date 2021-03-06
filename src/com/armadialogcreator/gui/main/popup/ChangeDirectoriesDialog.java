package com.armadialogcreator.gui.main.popup;

import com.armadialogcreator.arma.util.ArmaTools;
import com.armadialogcreator.data.ApplicationDataManager;
import com.armadialogcreator.gui.popup.StageDialog;
import com.armadialogcreator.main.ArmaDialogCreator;
import com.armadialogcreator.main.HelpUrls;
import com.armadialogcreator.main.Lang;
import com.armadialogcreator.util.BrowserUtil;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.DirectoryChooser;
import javafx.stage.StageStyle;
import org.jetbrains.annotations.Nullable;

import java.io.File;
import java.util.ResourceBundle;

/**
 Used for setting Arma 3 Tools directory {@link ApplicationDataManager#getArma3ToolsDirectory()}. All changes will be set once popup is closed.

 @author Kayler
 @since 05/26/2016. */
public class ChangeDirectoriesDialog extends StageDialog<VBox> {

	private final TextField tfA3ToolsDir = new TextField();

	private BadArma3ToolsDirectoryPopup badArma3ToolsDirectoryPopup;
	private final ResourceBundle bundle = Lang.ApplicationBundle();

	/**
	 Creates the "change directories" popup
	 */
	public ChangeDirectoriesDialog(@Nullable File a3ToolsDir) {
		super(ArmaDialogCreator.getPrimaryStage(), new VBox(5), null, true, true, true);
		setTitle(bundle.getString("Popups.SelectSaveLocation.popup_title"));
		initialize(a3ToolsDir);
		myStage.setMinWidth(600d);
		myStage.initStyle(StageStyle.UTILITY);
	}

	private void initialize(@Nullable File a3ToolsDir) {
		tfA3ToolsDir.setEditable(false);

		if (a3ToolsDir != null) {
			tfA3ToolsDir.setText(a3ToolsDir.getPath());
		}

		final Label lblA3ToolsDir = new Label(bundle.getString("Popups.SelectSaveLocation.lbl_a3_tools_dir"));
		final Button btnChangeA3Tools = new Button(bundle.getString("Popups.SelectSaveLocation.btn_change"));
		final Button btnClearA3ToolsDir = new Button(bundle.getString("Popups.SelectSaveLocation.btn_clear"));
		
		btnChangeA3Tools.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				DirectoryChooser dc = new DirectoryChooser();
				dc.setInitialDirectory(a3ToolsDir);
				dc.setTitle(lblA3ToolsDir.getText());
				File f = dc.showDialog(myStage);
				if (f == null) {
					return;
				}
				if (ArmaTools.isValidA3ToolsDirectory(f)) {
					chooseA3ToolsSaveDir(f);
				} else {
					if (badArma3ToolsDirectoryPopup == null) {
						badArma3ToolsDirectoryPopup = new BadArma3ToolsDirectoryPopup();
					}
					badArma3ToolsDirectoryPopup.show();
				}
			}
		});
		btnClearA3ToolsDir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				chooseA3ToolsSaveDir(null);
			}
		});


		HBox hbA3Tools = new HBox(5);
		hbA3Tools.getChildren().addAll(tfA3ToolsDir, btnChangeA3Tools, btnClearA3ToolsDir);
		HBox.setHgrow(tfA3ToolsDir, Priority.ALWAYS);

		myRootElement.getChildren().addAll(lblA3ToolsDir, hbA3Tools);
		myStage.setResizable(false);
	}

	private void chooseA3ToolsSaveDir(@Nullable File f) {
		tfA3ToolsDir.setText(f != null ? f.getPath() : "");
	}


	@Nullable
	public String getArma3ToolsDirectoryPath() {
		String s = tfA3ToolsDir.getText();
		if (s.length() == 0) {
			return null;
		}
		return s;
	}

	@Override
	protected void ok() {
		String a3tools = getArma3ToolsDirectoryPath();
		if (a3tools != null) {
			ArmaDialogCreator.getApplicationDataManager().setArma3ToolsLocation(new File(a3tools));
		} else {
			ArmaDialogCreator.getApplicationDataManager().setArma3ToolsLocation(null);
		}
		ArmaDialogCreator.getApplicationDataManager().saveApplicationProperties();

		close();
	}

	@Override
	protected void help() {
		BrowserUtil.browse(HelpUrls.CONFIGURE_DIRECTORIES_POPUP);
	}


	private class BadArma3ToolsDirectoryPopup extends StageDialog<VBox> {

		public BadArma3ToolsDirectoryPopup() {
			super(ArmaDialogCreator.getPrimaryStage(), new VBox(5, new Label(bundle.getString("Popups.SelectSaveLocation.bad_a3_tools_dir"))), bundle.getString
					("Popups.generic_popup_title"), false, true, false);
			myStage.setWidth(300d);
		}
	}
}
