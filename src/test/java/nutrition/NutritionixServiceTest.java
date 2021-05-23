package nutrition;

import io.reactivex.rxjava3.core.Single;
import org.junit.Assert;
import org.junit.Test;

public class NutritionixServiceTest
{
    @Test
    public void getCalories()
    {
        //given
        NutritionixService service = new NutritionixServiceFactory().newInstance();
        NutritionixRequest request = new NutritionixRequest("banana", "US/Eastern");

        //when
        Single<NutritionixFeed> single = service.getNutritionFacts(request);

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
