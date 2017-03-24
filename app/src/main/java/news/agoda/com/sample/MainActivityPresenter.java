package news.agoda.com.sample;

import android.content.Intent;

import com.hannesdorfmann.mosby.mvp.MvpBasePresenter;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;


/**
 * Created by pocha on 18/03/17.
 */
public class MainActivityPresenter extends MvpBasePresenter<MainActivityView> {

    private MainActivity activity;
    private List<NewsEntity> newsItemList;
    private INewsFetchRetrofit newsFetch;


    public MainActivityPresenter(INewsFetchRetrofit newsFetch) {
        this.newsFetch = newsFetch;
    }

    public void loadData() {
        Call<List<NewsEntity>> call = newsFetch.getNews();
        try {
            newsItemList = call.execute().body();
            getView().onResult(newsItemList);
        }catch (IOException e){
            e.printStackTrace();
        }
        /*
        call.enqueue(new Callback<List<NewsEntity>>() {
            @Override
            public void onResponse(Call<List<NewsEntity>> call, Response<List<NewsEntity>> response) {
                newsItemList = response.body();
                getView().onResult(newsItemList);
            }

            @Override
            public void onFailure(Call<List<NewsEntity>> call, Throwable t) {
                t.printStackTrace();
            }
        });*/
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
