package nutrition;

public class NutritionixRequest
{
    final String query;
    final String timezone;

    NutritionixRequest(String query, String timezone) {
        this.query = query;
        this.timezone = timezone;
    }
}
