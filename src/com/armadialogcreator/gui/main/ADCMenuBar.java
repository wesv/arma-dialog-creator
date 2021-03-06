package com.armadialogcreator.gui.main;

import com.armadialogcreator.arma.stringtable.KnownLanguage;
import com.armadialogcreator.arma.stringtable.Language;
import com.armadialogcreator.arma.util.ArmaUIScale;
import com.armadialogcreator.data.ApplicationProperty;
import com.armadialogcreator.gui.fxcontrol.PresetCheckMenuItem;
import com.armadialogcreator.gui.img.ADCImages;
import com.armadialogcreator.gui.main.actions.mainMenu.EditStringTableAction;
import com.armadialogcreator.gui.main.actions.mainMenu.SettingsChangeSaveDirAction;
import com.armadialogcreator.gui.main.actions.mainMenu.TestAction;
import com.armadialogcreator.gui.main.actions.mainMenu.create.CreateMacroAction;
import com.armadialogcreator.gui.main.actions.mainMenu.create.CreateNewControlAction;
import com.armadialogcreator.gui.main.actions.mainMenu.create.CreateNewCustomControlAction;
import com.armadialogcreator.gui.main.actions.mainMenu.create.CreateNewFolderAction;
import com.armadialogcreator.gui.main.actions.mainMenu.edit.*;
import com.armadialogcreator.gui.main.actions.mainMenu.file.*;
import com.armadialogcreator.gui.main.actions.mainMenu.help.CheckForUpdateAction;
import com.armadialogcreator.gui.main.actions.mainMenu.help.WikiUrlAction;
import com.armadialogcreator.gui.main.actions.mainMenu.view.*;
import com.armadialogcreator.gui.main.popup.AboutDialog;
import com.armadialogcreator.gui.main.popup.ExpressionEvaluatorPopup;
import com.armadialogcreator.gui.uicanvas.UIScale;
import com.armadialogcreator.main.ArmaDialogCreator;
import com.armadialogcreator.main.Lang;
import com.armadialogcreator.main.ProgramArgument;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;

import java.util.ResourceBundle;

import static com.armadialogcreator.gui.fxcontrol.MenuUtil.addOnAction;

/**
 Created by Kayler on 05/15/2016.
 */
class ADCMenuBar extends MenuBar {

	final ResourceBundle bundle = Lang.getBundle("MainMenuBarBundle");

	/*File*/
	final MenuItem file_open = addOnAction(new MenuItem(bundle.getString("file_open")), new FileOpenAction());
	final MenuItem file_save = addOnAction(new MenuItem(bundle.getString("file_save"), new ImageView(ADCImages.ICON_SAVE)), new FileSaveAction());
	final MenuItem file_export = addOnAction(new MenuItem(bundle.getString("file_export"), new ImageView(ADCImages.ICON_EXPORT)), new FileExportAction());
	final MenuItem file_closeProject = addOnAction(new MenuItem(bundle.getString("file_close_project")), new FileCloseProjectAction());
	final MenuItem file_exit = addOnAction(new MenuItem(bundle.getString("file_exit")), new FileExitAction());
	final MenuItem file_restart = addOnAction(new MenuItem(bundle.getString("file_restart")), new FileRestartAction());


	/*Edit*/
	final MenuItem edit_undo = new MenuItem(bundle.getString("edit_undo"), new ImageView(ADCImages.ICON_UNDO));
	final MenuItem edit_redo = new MenuItem(bundle.getString("edit_redo"), new ImageView(ADCImages.ICON_REDO));

	{
		edit_redo.setOnAction(new EditRedoAction(edit_redo));
		edit_undo.setOnAction(new EditUndoAction(edit_undo));
	}

	final MenuItem edit_viewChanges = addOnAction(new MenuItem(bundle.getString("edit_changes")), new EditViewChangesAction());
	final MenuItem edit_macros = addOnAction(new MenuItem(bundle.getString("edit_macros")), new EditMacrosAction());
	final MenuItem edit_custom_control = addOnAction(new MenuItem(bundle.getString("edit_custom_control")), new EditCustomControlAction());
	final MenuItem edit_string_table = addOnAction(new MenuItem(bundle.getString("edit_string_table")), new EditStringTableAction());
	final MenuItem edit_exportConfig = addOnAction(new MenuItem(bundle.getString("edit_export_config")), new EditExportConfigAction());
	final MenuItem edit_projectSettings = addOnAction(new MenuItem(bundle.getString("edit_project_settings")), new EditProjectSettingsAction());

	/*View*/
	final MenuItem view_preview = addOnAction(new MenuItem(bundle.getString("view_preview"), null), new ViewPreviewAction());
	final CheckMenuItem view_showGrid = addOnAction(new CheckMenuItem(bundle.getString("view_show_grid")), new ViewShowGridAction());

	final CheckMenuItem view_darkTheme = addOnAction(new CheckMenuItem(bundle.getString("view_dark_theme")), new
			ViewDarkThemeAction(ApplicationProperty.DARK_THEME.getValue()));

	{
		view_darkTheme.setSelected(ApplicationProperty.DARK_THEME.getValue());
	}

	final MenuItem view_colors = addOnAction(new MenuItem(bundle.getString("view_change_colors")), new ViewColorsAction());
	final MenuItem view_fullScreen = addOnAction(new MenuItem(bundle.getString("view_full_screen")), new ViewFullScreenAction());
	/*screen*/
	final ChoiceBox<UIScale> choiceBoxUiScale = new ChoiceBox<>();
	final Label lblUiScale = new Label(bundle.getString("view_ui_scale"), choiceBoxUiScale);
	{
		lblUiScale.setContentDisplay(ContentDisplay.RIGHT);
		choiceBoxUiScale.getItems().addAll(ArmaUIScale.values());
		choiceBoxUiScale.getSelectionModel().select(ArmaUIScale.DEFAULT);
		choiceBoxUiScale.getSelectionModel().selectedItemProperty().addListener(new ViewScreenUIScaleChangeAction());
	}

	final CustomMenuItem view_uiScale = new CustomMenuItem(lblUiScale, false);

	final ChoiceBox<Language> choiceBoxUiLanguage = new ChoiceBox<>();
	final Label lblUiLanguage = new Label(bundle.getString("view_ui_language"), choiceBoxUiLanguage);

	{
		lblUiLanguage.setContentDisplay(ContentDisplay.RIGHT);
		choiceBoxUiLanguage.getItems().addAll(KnownLanguage.values());
		choiceBoxUiLanguage.getSelectionModel().selectedItemProperty().addListener(new ViewScreenUILanguageChangeAction());
		choiceBoxUiLanguage.getSelectionModel().select(KnownLanguage.Original);
	}

	final CustomMenuItem view_uiLanguage = new CustomMenuItem(lblUiLanguage, false);

	final Menu view_ui = new Menu(bundle.getString("view_ui"), null, view_uiScale, view_uiLanguage);
	/*abs region*/
	final PresetCheckMenuItem view_absRegion_show = addOnAction(new PresetCheckMenuItem(bundle.getString("view_abs_region_show"), true), new ViewAbsRegionShowAction());
	final PresetCheckMenuItem view_absRegion_alwaysFront = addOnAction(new PresetCheckMenuItem(bundle.getString("view_abs_region_always_front"), true), new ViewAbsRegionAlwaysFrontAction());
	final Menu view_absRegionAll = new Menu(bundle.getString("view_abs_region"), null, view_absRegion_show, view_absRegion_alwaysFront);
	/*background*/
	final RadioMenuItem view_bg_img1 = addOnAction(new RadioMenuItem(bundle.getString("view_change_background_image1")), new ViewBackgroundAction(ViewBackgroundAction.IMAGE_1));
	final RadioMenuItem view_bg_img2 = addOnAction(new RadioMenuItem(bundle.getString("view_change_background_image2")), new ViewBackgroundAction(ViewBackgroundAction.IMAGE_2));
	final RadioMenuItem view_bg_img3 = addOnAction(new RadioMenuItem(bundle.getString("view_change_background_image3")), new ViewBackgroundAction(ViewBackgroundAction.IMAGE_3));
	final RadioMenuItem view_bg_custom = addOnAction(new RadioMenuItem(bundle.getString("view_change_background_image_custom")), new ViewBackgroundAction(ViewBackgroundAction.IMAGE_CUSTOM));
	final RadioMenuItem view_bg_noImage = addOnAction(new RadioMenuItem(bundle.getString("view_change_background_none")), new ViewBackgroundAction(ViewBackgroundAction.NO_IMAGE));

	{
		final ToggleGroup view_bg_toggleGroup = new ToggleGroup();
		view_bg_img1.setToggleGroup(view_bg_toggleGroup);
		view_bg_img2.setToggleGroup(view_bg_toggleGroup);
		view_bg_img3.setToggleGroup(view_bg_toggleGroup);
		view_bg_custom.setToggleGroup(view_bg_toggleGroup);
		view_bg_noImage.setToggleGroup(view_bg_toggleGroup);
		view_bg_toggleGroup.selectToggle(view_bg_noImage);
	}

	final Menu backgroundAll = new Menu(
			bundle.getString("view_background_image"), null,
			view_bg_img1,
			view_bg_img2,
			view_bg_img3,
			view_bg_custom,
			view_bg_noImage
	);

	/*settings*/
	final MenuItem settings_configureDirs = addOnAction(
			new MenuItem(bundle.getString("settings_configure_dirs")), new SettingsChangeSaveDirAction()
	);

	/*create*/
	final MenuItem create_macro = addOnAction(new MenuItem(bundle.getString("create_macro")), new CreateMacroAction());
	final MenuItem create_control_class = addOnAction(new MenuItem(bundle.getString("create_control_class")), new CreateNewCustomControlAction());
	final MenuItem create_new_control = addOnAction(new MenuItem(bundle.getString("create_control")), new CreateNewControlAction());
	final MenuItem create_new_folder = addOnAction(
			new MenuItem(bundle.getString("create_folder")),
			new CreateNewFolderAction()
	);

	/*help*/
	final MenuItem help_wiki = addOnAction(new MenuItem(bundle.getString("help_wiki")), new WikiUrlAction());
	final MenuItem help_about = addOnAction(new MenuItem(bundle.getString("help_about")), event -> new AboutDialog().show());
	final MenuItem help_evaluator = addOnAction(new MenuItem(bundle.getString("help_evaluator")), event -> new ExpressionEvaluatorPopup().show());
	final MenuItem help_checkForUpdate = addOnAction(new MenuItem(bundle.getString("help_check_for_update")), new CheckForUpdateAction());

	final Menu menuFile = new Menu(
			bundle.getString("file"), null,
			/*file_open,*/
			file_save,
			file_export,
			new SeparatorMenuItem(),
			file_closeProject,
			file_restart,
			file_exit
	);
	final Menu menuEdit = new Menu(
			bundle.getString("edit"), null,
			edit_undo,
			edit_redo,
			edit_viewChanges,
			new SeparatorMenuItem(),
			edit_macros,
			edit_custom_control,
			edit_string_table,
			edit_exportConfig,
			edit_projectSettings
	);
	final Menu menuView = new Menu(
			bundle.getString("view"), null,
			view_preview,
			view_showGrid,
			view_colors,
			view_darkTheme,
			backgroundAll,
			view_absRegionAll,
			view_ui,
			view_fullScreen
	);
	final Menu menuSettings = new Menu(
			bundle.getString("settings"), null,
			settings_configureDirs
	);
	final Menu menuCreate = new Menu(
			bundle.getString("create"), null,
			create_macro,
			create_control_class,
			create_new_control,
			create_new_folder
	);
	final Menu menuHelp = new Menu(
			bundle.getString("help"), null,
			help_evaluator,
			help_wiki,
			help_about,
			help_checkForUpdate
	);

	ADCMenuBar() {
		this.getMenus().addAll(menuFile, menuEdit, menuView, menuSettings, menuCreate, menuHelp);
		if (ArmaDialogCreator.containsUnamedLaunchParameter(ProgramArgument.ShowDebugFeatures)) {
			this.getMenus().add(new Menu("Debug", null, addOnAction(new MenuItem("Debug Menu"), new TestAction())));
		}

		view_showGrid.getParentMenu().showingProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
				view_showGrid.setSelected(ArmaDialogCreator.getCanvasView().getConfiguration().showGrid());
			}
		});

	}


}
