## Android MVP Pattern Sample App ###

This is part of assignment (details below) that required fixing a broken app & re-coding it with *code for testability* approach. 

I had done a few months of Android coding way back in 2014. I come from web development background & all I was aware was MVC pattern. I heard about MVP first time while working on this assignment. It took me a few days understanding the pattern & the **why** behind it. 

The best article to understand this concept for total beginners is [http://konmik.com/post/introduction_to_model_view_presenter_on_android/](http://konmik.com/post/introduction_to_model_view_presenter_on_android/)

This project uses Mosby library. You should look into MainActivity & MainActivityPresenter class. MainActivity calls *loadData()* of presenter to request data. On successful network call, presenter calls onResult() function of activity by doing *getView().onResult(..)* . 

The same can be tested through pure JVM code as below

```
    private MainActivityPresenter presenter;

    @Mock
    MainActivityView view;

    @Captor
    private ArgumentCaptor<List<NewsEntity>> listNewsEntityCaptor;

    @Test
    public void testHappyFlow() {
        MockINewsFetchRetrofit newsFetchRetrofit = MockINewsFetchRetrofit.getInstance();
        presenter = new MainActivityPresenter(newsFetchRetrofit);
        presenter.attachView(view);
        presenter.loadData();
        Mockito.verify(view).onResult(listNewsEntityCaptor.capture());
        Assert.assertEquals(listNewsEntityCaptor.getValue().get(0).getTitle(),"test title");
    }

```

If you are new to Mockito, the *ArgumentCaptor* part might look a bit daunting. The part is just to capture the parameters passed to onResult & then verifying them. This is the way, the presenter (or the business logic) can be compeltely tested without any Android depdendence. 

**I have removed the HTTP end point. Specify the same in INewsFetchRetrofit.java**
