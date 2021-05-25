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
    private Label gramsLabel;

    @BeforeClass
    public static void beforeClass() {
        com.sun.javafx.application.PlatformImpl.startup(()->{});
    }

    @Test
    public void calculate()
    {
        //given
        givenNutritionController();
        doReturn("banana").when(controller.nameTextField).getText();
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
        feed.foods.get(0).nf_calories = 105.02;
        feed.foods.get(0).serving_weight_grams = 118;

        //when
        controller.onNutritionixFeedRunLater(feed);

        //then
        verify(gramsLabel).setText("112 grams");
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

        gramsLabel = mock(Label.class);
        controller.gramsLabel = gramsLabel;
    }
}