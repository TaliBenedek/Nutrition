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

public class NutritionixController
{
    @FXML
    TextField nameTextField;

    @FXML
    Button calculateButton;

    @FXML
    Label servingSizeLabel;

    @FXML
    Label servingWeightLabel;

    @FXML
    Label caloriesLabel;

    @FXML
    Label totalFatLabel;

    @FXML
    Label saturatedFatLabel;

    @FXML
    Label cholesterolLabel;

    @FXML
    Label sodiumLabel;

    @FXML
    Label totalCarbsLabel;

    @FXML
    Label fiberLabel;

    @FXML
    Label sugarsLabel;

    @FXML
    Label proteinLabel;

    @FXML
    Label hundredCaloriesLabel;

    NutritionixService service;

    NutritionixRequest request;

    final int hundredCalories = 100;

    // Dependency injection
    public NutritionixController(NutritionixService service, NutritionixRequest request)
    {
        this.service = service;
        this.request = request;
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
        NutritionixFeed.Foods food = feed.foods.get(0);
        //formats the nutrition facts, taking into account whether grams should be plural or not
        //as well as the nutrition facts being rounded up or down
        servingSizeLabel.setText(food.serving_qty + " " + food.serving_unit);
        servingWeightLabel.setText(String.format("%.0f grams", food.serving_weight_grams));
        caloriesLabel.setText(String.format("%.0f", food.nf_calories));
        totalFatLabel.setText(String.format(food.nf_total_fat < 2 && food.nf_total_fat > .5  ? "%.0f gram" : "%.0f grams", food.nf_total_fat));
        saturatedFatLabel.setText(String.format(food.nf_saturated_fat < 2 && food.nf_saturated_fat > .5  ? "%.0f gram" : "%.0f grams", food.nf_saturated_fat));
        cholesterolLabel.setText(String.format(food.nf_cholesterol < 2 && food.nf_cholesterol > .5  ? "%.0f gram" : "%.0f grams", food.nf_cholesterol));
        sodiumLabel.setText(String.format(food.nf_sodium < 2 && food.nf_sodium > .5  ? "%.0f gram" : "%.0f grams", food.nf_sodium));
        totalCarbsLabel.setText(String.format(food.nf_total_carbohydrate < 2 && food.nf_total_carbohydrate > .5  ? "%.0f gram" : "%.0f grams", food.nf_total_carbohydrate));
        fiberLabel.setText(String.format(food.nf_dietary_fiber < 2 && food.nf_dietary_fiber > .5  ? "%.0f gram" : "%.0f grams", food.nf_dietary_fiber));
        sugarsLabel.setText(String.format(food.nf_sugars < 2 && food.nf_sugars > .5 ? "%.0f gram" : "%.0f grams", food.nf_sugars));
        proteinLabel.setText(String.format(food.nf_protein < 2 && food.nf_protein > .5  ? "%.0f gram" : "%.0f grams", food.nf_protein));

        double baseCalories = feed.foods.get(0).nf_calories;
        double baseGrams = feed.foods.get(0).serving_weight_grams;
        double calculatedGrams = hundredCalories * baseGrams / baseCalories;
        hundredCaloriesLabel.setText(String.format("%.0f grams", calculatedGrams));
    }

    public void onError(Throwable throwable) {
        JOptionPane.showMessageDialog(null,
                "The food is misspelled or is not in the database, please try again.");
        throwable.printStackTrace();
    }
}
