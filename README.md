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

Below is the original problem statement - which you may conveniently ignore :).

------------

This is a sample News Reader app that is supposed to display news list and the details.
The first page displays news list, when one of the items is clicked, it is supposed to show the detail of the selected news.
Unfortunately, the app is full of bugs and it crashes as soon as it is launched.
Also, the code is not properly written and there are no unit tests.
Can you help to fix all the problems?

## Before you start
This project requires the following

1. Android Studio 2.1
2. Android SDK 23 or above.
3. Android SDK build tools 23.0.3 or above.

## Screenshots
The screenshot below shows how the app looks like when it is done.

![](http://i.imgur.com/GgEP7FM.jpg)
![](http://i.imgur.com/yAtzntJ.jpg)

## About the project
All the data is coming from the web endpoint.
The response contains a list of news items as well as URLs to the pictures associated with each story.

## Fix crashes in News List page
Can you help fix all bugs so that it can display news list properly?

## Fix crashes in News Detail page
Can you help fix all bugs so that the app can show news detail properly? Also, clicking on "Full Story" button, it should open a browser and display full story in the browser.

## Basic unit test
Can you help to write unit tests for MediaEntity and NewsEntity?

## Improvements
1. The main logic is written in MainActivity, which is not a very clean way to construct an app. Can you help to improve it?

2. The way of fetching and  parsing JSON data is not very nice. For example, if one of the name/value is missing, it
can cause the app to crash.

3. The layout is only suitable for phones. Can you create an immersive tablet experience?

Can you help to make it better?

## Notes
1. It is possible that some of the stories do not have images.
2. It is possible that the link to the full story might not work as it is controlled by New York Times.
