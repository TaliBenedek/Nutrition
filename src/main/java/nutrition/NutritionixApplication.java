package nutrition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class NutritionixApplication extends Application
{
    @Override
    public void start(Stage stage) throws IOException
    {
        NutritionixService service = new NutritionixServiceFactory().newInstance();
        NutritionController controller = new NutritionController(service);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/nutrition_application.fxml"));
        loader.setController(controller);
        Parent parent = loader.load();

        Scene scene = new Scene(parent, 300, 250);

        stage.setTitle("Nutrition");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
