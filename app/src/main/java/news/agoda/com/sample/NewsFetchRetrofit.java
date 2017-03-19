package news.agoda.com.sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.List;

import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.Retrofit;

/**
 * Created by pocha on 19/03/17.
 */
public class NewsFetchRetrofit {

    public static INewsFetchRetrofit getInstance(Object typeAdapter){
        Gson gson = new GsonBuilder()
                .registerTypeAdapter(new TypeToken<List<NewsEntity>>() {}.getType(), typeAdapter)
                .create();
        return new Retrofit.Builder()
                .baseUrl(INewsFetchRetrofit.baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(INewsFetchRetrofit.class);
    }
}
