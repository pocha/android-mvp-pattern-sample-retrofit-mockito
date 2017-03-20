package news.agoda.com.sample;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.mock.BehaviorDelegate;
import retrofit2.mock.MockRetrofit;
import retrofit2.mock.NetworkBehavior;

public class MockINewsFetchRetrofit implements INewsFetchRetrofit {
    private String TAG="MockINewsFetchRetrofit";
    private BehaviorDelegate<INewsFetchRetrofit> delegate;
    private List<NewsEntity> newsEntityList;

    public MockINewsFetchRetrofit(BehaviorDelegate<INewsFetchRetrofit> delegate){
        this.delegate = delegate;
        newsEntityList = new ArrayList<NewsEntity>();
    }

    @Override
    public Call<List<NewsEntity>> getNews() {
        System.out.println("inside getNews of MockINewsFetchRetrofit");
        newsEntityList.add(new NewsEntity("test title", "test summary","http://google.com", new ArrayList<MediaEntity>()));
        return delegate.returningResponse(newsEntityList).getNews();
    }

    public static MockINewsFetchRetrofit getInstance() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(INewsFetchRetrofit.baseUrl)
                .build();

        // Create a MockRetrofit object with a NetworkBehavior which manages the fake behavior of calls.
        NetworkBehavior behavior = NetworkBehavior.create();
        MockRetrofit mockRetrofit = new MockRetrofit.Builder(retrofit)
                .networkBehavior(behavior)
                .build();

        BehaviorDelegate<INewsFetchRetrofit> delegate = mockRetrofit.create(INewsFetchRetrofit.class);
        return new MockINewsFetchRetrofit(delegate);
    }
}