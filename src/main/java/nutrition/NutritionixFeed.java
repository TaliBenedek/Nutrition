package nutrition;

import java.util.List;

public class NutritionixFeed
{
    List<Foods> foods;

    static class Foods
    {
        String food_name;
        int serving_weight_grams;
        double nf_calories;
    }
}