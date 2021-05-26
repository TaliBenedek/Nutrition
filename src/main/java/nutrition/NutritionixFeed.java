package nutrition;

import java.util.List;

public class NutritionixFeed
{
    List<Foods> foods;

    static class Foods
    {
        String food_name;
        int serving_qty;
        String serving_unit;
        double serving_weight_grams;
        double nf_calories;
        double nf_total_fat;
        double nf_saturated_fat;
        double nf_cholesterol;
        double nf_sodium;
        double nf_total_carbohydrate;
        double nf_dietary_fiber;
        double nf_sugars;
        double nf_protein;

    }
}
