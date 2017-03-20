package news.agoda.com.sample;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {
    private MainActivityPresenter presenter;

    @Mock MainActivity activity;
    @Captor private ArgumentCaptor<List<NewsEntity>> listNewsEntityCaptor;

    @Test
    public void testHappyFlow() {
        MockINewsFetchRetrofit newsFetchRetrofit = MockINewsFetchRetrofit.getInstance();
        presenter = new MainActivityPresenter(activity,newsFetchRetrofit);
        Mockito.verify(activity).onResult(listNewsEntityCaptor.capture());
        Assert.assertEquals(listNewsEntityCaptor.getValue().get(0).getTitle(),"test title");
    }
}