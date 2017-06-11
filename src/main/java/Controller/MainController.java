package Controller;

import Crawler.WebCrawler;
import Model.GlobalModel;
import Models.PageData;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.prefs.Preferences;

/**
 * @author Sergey Dubovyk
 * @version 1.0
 */
public class MainController{
    public TextField urlSource;
    public TextField titleField;
    public TextArea descField;
    public TextField tagsField;
    public Label prodCounter;
    public TextField filePath;
    public Button selectPath;

    private Preferences prefs;

    @FXML
    public void handleLoad(ActionEvent event){
        GlobalModel.getInstance().setUrl(urlSource.getText());
        WebCrawler crawler = new WebCrawler();
        PageData pageData = crawler.parseURL(GlobalModel.getInstance().getUrl());
        descField.setWrapText(true);
        titleField.setText(pageData.getTitle());
        descField.setText(pageData.getDescription());
        tagsField.setText(String.join(",", pageData.getTags()));
        prodCounter.setText(((Integer) pageData.getSlides().size()).toString());
        GlobalModel.getInstance().setPageData(pageData);
    }

    @FXML
    public void handleSave(ActionEvent event){
        prefs = Preferences.userRoot().node(this.getClass().getName());
        if(GlobalModel.getInstance().getPageData() == null){
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Load URL first");
            error.showAndWait();
            return;
        }

        if(GlobalModel.getInstance().getFilePath() == null){
            handleSave(null);
        }
        PageData data = GlobalModel.getInstance().getPageData();
        List<String> tags = new ArrayList<String>();
        for(String tag: tagsField.getText().split(",")){
            tags.add(tag.trim());
        }
        data.setTags(tags);

        String desc = descField.getText();
        data.setDescription(desc);

        String title = titleField.getText();
        data.setTitle(title);



        try {
            Writer writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(GlobalModel.getInstance().getFilePath()), "UTF-8"));
            writer.write(GlobalModel.getInstance().getPageData().toXml());
            writer.flush();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("File was saved successfully.");
            alert.setHeaderText("Done");
            alert.showAndWait();
            prefs.put("initDir", GlobalModel.getInstance().getFilePath());
        } catch (IOException ex){
            ex.printStackTrace();
            Alert error = new Alert(Alert.AlertType.ERROR);
            error.setContentText("Can`t save file");
            error.showAndWait();
        } finally {

        }
    }

    @FXML
    public void handleChosePath(ActionEvent event){
        prefs = Preferences.userRoot().node(this.getClass().getName());
        FileChooser chooser = new FileChooser();
        File initDir = new File(new File(prefs.get("initDir", System.getProperty("user.dir"))).getParent());
        chooser.setInitialDirectory(initDir);
        chooser.setInitialFileName(GlobalModel.getInstance().getPageData().getTitle().replaceAll("(\\W)", "_") + ".xml");

        File path = chooser.showSaveDialog(new Stage());
        if(path!=null){
            filePath.setText(path.getAbsolutePath());
            GlobalModel.getInstance().setFilePath(path.getAbsolutePath());
        }
    }

    @FXML
    public void handleExit(ActionEvent event){
        Platform.exit();
    }

    @FXML
    public void handleHelp(ActionEvent event){
        Alert info = new Alert(Alert.AlertType.INFORMATION);
        info.setHeaderText("Help");
        info.setTitle("Help");
        info.setContentText("To create an xml-file enter the source page link into the 'Source URL' field, press 'Load'," +
                "edit fields you want, select output path and press 'Save'.");
        info.showAndWait();
    }
}
