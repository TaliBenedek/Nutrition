package nutrition;

import io.reactivex.rxjava3.core.Single;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NutritionixServiceFactory
{
    public NutritionixService newInstance()
    {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trackapi.nutritionix.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        NutritionixService service = retrofit.create(NutritionixService.class);
        return service;
    }
}
