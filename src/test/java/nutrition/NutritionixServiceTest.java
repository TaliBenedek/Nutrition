package nutrition;

import io.reactivex.rxjava3.core.Single;
import org.junit.Assert;
import org.junit.Test;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;

public class NutritionixServiceTest
{
    @Test
    public void getCalories()
    {
        //given
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://trackapi.nutritionix.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();

        NutritionixService service = retrofit.create(NutritionixService.class);
        NutritionixRequest request = new NutritionixRequest("banana", "US/Eastern");

        //when
        Single<NutritionixFeed> single = service.getCalories(request);

        // DO NOT USE BLOCKING GET!
        NutritionixFeed feed = single.blockingGet();

        //then
        Assert.assertNotNull(feed);
        Assert.assertNotNull(feed.foods);
        Assert.assertTrue(feed.foods.get(0).serving_weight_grams > 0);
        Assert.assertTrue(feed.foods.get(0).nf_calories > 0);
        Assert.assertEquals("banana", feed.foods.get(0).food_name);
    }
}
