package nutrition;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface NutritionixService
{
    @Headers({"Content-Type: application/json",
            "x-app-id: 6da14d17",
            "x-app-key: 0ad507a73fef07ee2a6cb4f9d7cb6354"})
    @POST("v2/natural/nutrients/")
    Single<NutritionixFeed> getNutritionFacts(@Body NutritionixRequest request);
}
