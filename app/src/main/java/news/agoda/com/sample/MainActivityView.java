package news.agoda.com.sample;

import com.hannesdorfmann.mosby.mvp.MvpView;

import java.util.List;

/**
 * Created by pocha on 25/03/17.
 */

public interface MainActivityView extends MvpView {
    public void onResult(List<NewsEntity> newsItemList);
}
