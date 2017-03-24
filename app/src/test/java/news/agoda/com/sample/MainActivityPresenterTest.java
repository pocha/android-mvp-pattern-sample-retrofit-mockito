package news.agoda.com.sample;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class MainActivityPresenterTest {
    private MainActivityPresenter presenter;

    @Mock
    MainActivityView view;

    @Captor
    private ArgumentCaptor<List<NewsEntity>> listNewsEntityCaptor;

    @Test
    public void testHappyFlow() {
        MockitoAnnotations.initMocks(this);
        MockINewsFetchRetrofit newsFetchRetrofit = MockINewsFetchRetrofit.getInstance();
        presenter = new MainActivityPresenter(newsFetchRetrofit);
        presenter.attachView(view);
        presenter.loadData();
        Mockito.verify(view).onResult(listNewsEntityCaptor.capture());
        Assert.assertEquals(listNewsEntityCaptor.getValue().get(0).getTitle(),"test title");
    }
}