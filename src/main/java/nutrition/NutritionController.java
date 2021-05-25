package nutrition;

import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import javax.swing.*;

public class NutritionController
{
    @FXML
    TextField nameTextField;

    @FXML
    Button calculateButton;

    @FXML
    Label gramsLabel;

    NutritionixService service;

    NutritionixRequest request;

    final int hundredCalories = 100;

    // Dependency injection
    public NutritionController(NutritionixService service)
    {
        this.service = service;
    }

    public void calculate(ActionEvent actionEvent)
    {
        request = new NutritionixRequest(nameTextField.getText(), "US/Eastern");
        Disposable disposable = service.getNutritionFacts(request)
                // request the data in the background
                .subscribeOn(Schedulers.io())
                // work with the data in the foreground
                .observeOn(Schedulers.trampoline())
                // work with the feed whenever it gets downloaded
                .subscribe(this::onNutritionixFeed, this::onError);
    }

    public void onNutritionixFeed(NutritionixFeed feed)
    {
        Platform.runLater(() -> onNutritionixFeedRunLater(feed));
    }

    public void onNutritionixFeedRunLater(NutritionixFeed feed)
    {
        double baseCalories = feed.foods.get(0).nf_calories;
        double baseGrams = feed.foods.get(0).serving_weight_grams;
        double calculatedGrams = hundredCalories * baseGrams / baseCalories;
        gramsLabel.setText(String.format("%.0f grams", calculatedGrams));
    }

    public void onError(Throwable throwable) {
        JOptionPane.showMessageDialog(null,
                "The food is misspelled or is not in the database, please try again.");
    }
}
