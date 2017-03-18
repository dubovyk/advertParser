import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Sergey Dubovyk
 * @version 1.0
 */
public class Main extends Application{
    public void start(Stage primeStage){
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/layouts/main.fxml"));
            Scene scene = new Scene(root, 1000, 500);
            primeStage.setResizable(false);
            primeStage.setTitle("Video data crawler");
            primeStage.setScene(scene);
            primeStage.show();
        } catch (Exception ex){
            ex.printStackTrace();
        }

    }

    public static void main(String... args){
        launch(args);
    }
}
