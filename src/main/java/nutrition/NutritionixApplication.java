package nutrition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class NutritionixApplication extends Application
{
    @Override
    public void start(Stage stage) throws Exception
    {
        NutritionixService service = new NutritionixServiceFactory().newInstance();
        NutritionixRequest request = new NutritionixRequest("banana", "US/Eastern");
        NutritionixController controller = new NutritionixController(service, request);

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/nutrition_application.fxml"));
        loader.setController(controller);
        Parent parent = loader.load();

        Scene scene = new Scene(parent, 400, 500);

        stage.setTitle("Nutrition");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
