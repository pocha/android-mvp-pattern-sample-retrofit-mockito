package news.agoda.com.sample;

import android.content.Intent;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * Created by pocha on 18/03/17.
 */
public class MainActivityPresenter {

    private MainActivity activity;
    private List<NewsEntity> newsItemList;


    public MainActivityPresenter(MainActivity _activity, INewsFetchRetrofit newsFetch) {
        activity = _activity;
        Call<List<NewsEntity>> call = newsFetch.getNews();
        call.enqueue(new Callback<List<NewsEntity>>() {
            @Override
            public void onResponse(Call<List<NewsEntity>> call, Response<List<NewsEntity>> response) {
                newsItemList = response.body();
                activity.onResult(newsItemList);
            }

            @Override
            public void onFailure(Call<List<NewsEntity>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public Intent getIntentOnNewsItemClick(int position){
        NewsEntity newsEntity = newsItemList.get(position);

        Intent intent = new Intent(activity, DetailViewActivity.class);
        intent.putExtra("title", newsEntity.getTitle());
        intent.putExtra("storyURL", newsEntity.getUrl());
        intent.putExtra("summary", newsEntity.getSummary());
        intent.putExtra("imageURL", newsEntity.getImageUrl());
        return intent;
    }

    public static String getHelloWorldString(){
        return "hello world";
    }
}
