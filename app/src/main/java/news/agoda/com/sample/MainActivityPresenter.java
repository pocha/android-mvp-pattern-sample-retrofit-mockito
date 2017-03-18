package news.agoda.com.sample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;
import retrofit.http.GET;

/**
 * Created by pocha on 18/03/17.
 */
public class MainActivityPresenter {

    private String baseUrl = "https://api.myjson.com/";
    private NewsFetch newsFetch;
    private final MainActivity activity;

    public MainActivityPresenter(MainActivity _activity) {
        activity = _activity;
        Gson gson = new GsonBuilder()
                    .registerTypeAdapter(new TypeToken<List<NewsEntity>>() {}.getType(), new MyDeserializer())
                    .create();
        newsFetch = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build()
                .create(NewsFetch.class);
        Call<List<NewsEntity>> call = newsFetch.getNews();
        call.enqueue(new Callback<List<NewsEntity>>() {
            @Override
            public void onResponse(Response<List<NewsEntity>> response, Retrofit retrofit) {
                activity.onResult(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private interface NewsFetch {
        @GET("bins/nl6jh")
        Call<List<NewsEntity>> getNews();
    }


    private class MyDeserializer implements JsonDeserializer<List<NewsEntity>>
    {
        @Override
        public List<NewsEntity> deserialize(JsonElement je, Type type, JsonDeserializationContext jdc)
                throws JsonParseException
        {
            // Get the "content" element from the parsed JSON
            List <NewsEntity> newsItemList = new ArrayList<>();

            if (je.getAsJsonObject().get("results").isJsonArray()) {
                JsonArray results = je.getAsJsonObject().getAsJsonArray("results");
                for (int i = 0; i < results.size(); i++) {
                    JsonObject newsObject = results.get(i).getAsJsonObject();
                    String title = newsObject.get("title").getAsString();
                    String summary = newsObject.get("abstract").getAsString();
                    String url = newsObject.get("url").getAsString();
                    List<MediaEntity> mediaEntityList = new ArrayList<>();
                    if (newsObject.get("multimedia").isJsonArray()) {
                        JsonArray multimedia = newsObject.getAsJsonArray("multimedia");
                        for (int j = 0; j < multimedia.size(); j++) {
                            MediaEntity mediaEntity = new MediaEntity(multimedia.get(j).getAsJsonObject().get("url").getAsString());
                            mediaEntityList.add(mediaEntity);
                        }
                    }
                    NewsEntity newsEntity = new NewsEntity(title, summary, url, mediaEntityList);
                    if (title != null) {
                        newsItemList.add(newsEntity);
                    }
                }
            }
            return newsItemList;
        }
    }


    private class FakeNewsEntityDeserializer implements JsonDeserializer<List<NewsEntity>> {

        public List<NewsEntity> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
            List<MediaEntity> mediaEntityList = new ArrayList<>();
            NewsEntity newsEntity = new NewsEntity("title","summary","http://google.com", mediaEntityList);
            List<NewsEntity> newsEntities = new ArrayList<>();
            newsEntities.add(newsEntity);
            return newsEntities;
        }
    }
}
