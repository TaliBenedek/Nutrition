package nutrition;

import io.reactivex.rxjava3.core.Single;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NutritionixControllerTest
{
    private NutritionixService service;
    private NutritionixRequest request;
    private NutritionixController controller;
    private TextField nameTextField;
    private Button calculateButton;
    private Label servingSizeLabel;
    private Label servingWeightLabel;
    private Label caloriesLabel;
    private Label totalFatLabel;
    private Label saturatedFatLabel;
    private Label cholesterolLabel;
    private Label sodiumLabel;
    private Label totalCarbsLabel;
    private Label fiberLabel;
    private Label sugarsLabel;
    private Label proteinLabel;
    private Label hundredCaloriesLabel;

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }

    @Test
    public void calculate()
    {
        //given
        givenNutritionController();
        doReturn("chicken breast").when(controller.nameTextField).getText();
        doReturn(Single.never()).when(controller.service).getNutritionFacts(any(NutritionixRequest.class));

        //when
        controller.calculate(mock(ActionEvent.class));

        //then
        verify(controller.service).getNutritionFacts(controller.request);
    }

    @Test
    public void onNutritionixFeedRunLater()
    {
        //given
        givenNutritionController();
        NutritionixFeed feed = mock(NutritionixFeed.class);
        feed.foods = Arrays.asList(mock(NutritionixFeed.Foods.class));
        NutritionixFeed.Foods food = feed.foods.get(0);
        food.serving_qty = 1;
        food.serving_unit = "breast";
        food.serving_weight_grams = 120.0;
        food.nf_calories = 198.0;
        food.nf_total_fat = 4.28;
        food.nf_saturated_fat = 1.21;
        food.nf_cholesterol = 102.0;
        food.nf_sodium = 88.8;
        food.nf_total_carbohydrate = 0.0;
        food.nf_dietary_fiber = 0.0;
        food.nf_sugars = 0.0;
        food.nf_protein = 37.22;

        //when
        controller.onNutritionixFeedRunLater(feed);

        //then
        verify(servingSizeLabel).setText("1 breast");
        verify(servingWeightLabel).setText("120 grams");
        verify(caloriesLabel).setText("198");
        verify(totalFatLabel).setText("4 grams");
        verify(saturatedFatLabel).setText("1 gram");
        verify(cholesterolLabel).setText("102 grams");
        verify(sodiumLabel).setText("89 grams");
        verify(totalCarbsLabel).setText("0 grams");
        verify(fiberLabel).setText("0 grams");
        verify(sugarsLabel).setText("0 grams");
        verify(proteinLabel).setText("37 grams");
        verify(hundredCaloriesLabel).setText("61 grams");
    }

    private void givenNutritionController()
    {
        service = mock(NutritionixService.class);
        request = mock(NutritionixRequest.class);
        controller = new NutritionixController(service, request);

        nameTextField = mock(TextField.class);
        controller.nameTextField = nameTextField;

        calculateButton = mock(Button.class);
        controller.calculateButton = calculateButton;

        servingSizeLabel = mock(Label.class);
        controller.servingSizeLabel = servingSizeLabel;

        servingWeightLabel = mock(Label.class);
        controller.servingWeightLabel = servingWeightLabel;

        caloriesLabel = mock(Label.class);
        controller.caloriesLabel = caloriesLabel;

        totalFatLabel = mock(Label.class);
        controller.totalFatLabel = totalFatLabel;

        saturatedFatLabel = mock(Label.class);
        controller.saturatedFatLabel = saturatedFatLabel;

        cholesterolLabel = mock(Label.class);
        controller.cholesterolLabel = cholesterolLabel;

        sodiumLabel = mock(Label.class);
        controller.sodiumLabel = sodiumLabel;

        totalCarbsLabel = mock(Label.class);
        controller.totalCarbsLabel = totalCarbsLabel;

        fiberLabel = mock(Label.class);
        controller.fiberLabel = fiberLabel;

        sugarsLabel = mock(Label.class);
        controller.sugarsLabel = sugarsLabel;

        proteinLabel = mock(Label.class);
        controller.proteinLabel = proteinLabel;

        hundredCaloriesLabel = mock(Label.class);
        controller.hundredCaloriesLabel = hundredCaloriesLabel;
    }
}