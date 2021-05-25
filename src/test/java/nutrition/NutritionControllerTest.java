package nutrition;

import io.reactivex.rxjava3.core.Single;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class NutritionControllerTest
{
    private NutritionixService service;
    private NutritionixRequest request;
    private NutritionController controller;
    private TextField nameTextField;
    private Button calculateButton;
    private Label gramsLabel;
    //private int hundredCalories;

    @Test
    public void calculate()
    {
        //given
        givenNutritionController();
        doReturn("banana").when(controller.nameTextField).getText();
        doReturn(Single.never()).when(service).getNutritionFacts(request);

        //when
        controller.calculate(mock(ActionEvent.class));

        //then
        verify(service.getNutritionFacts(request));
    }

    @Test
    public void onNutritionixFeed()
    {
        //given

        //when

        //then
    }

    @Test
    public void onNutritionixFeedRunLater()
    {
        //given

        //when

        //then
    }

    private void givenNutritionController()
    {
        service = mock(NutritionixService.class);
        controller = new NutritionController(service);

        request = mock(NutritionixRequest.class);
        controller.request = request;

        nameTextField = mock(TextField.class);
        controller.nameTextField = nameTextField;

        calculateButton = mock(Button.class);
        controller.calculateButton = calculateButton;

        gramsLabel = mock(Label.class);
        controller.gramsLabel = gramsLabel;
    }
}