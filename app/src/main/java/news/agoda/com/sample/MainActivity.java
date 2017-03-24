package news.agoda.com.sample;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.hannesdorfmann.mosby.mvp.MvpActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity
        extends  MvpActivity<MainActivityView,MainActivityPresenter> implements MainActivityView
{

    private static final String TAG = MainActivity.class.getSimpleName();
    private NewsListAdapter adapter;


    @BindView(R.id.listView) ListView listView;

    @Override
    public MainActivityPresenter createPresenter(){
        INewsFetchRetrofit newsFetch = NewsFetchRetrofit.getInstance(new NewsDeserializer());
        return new MainActivityPresenter(newsFetch);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        adapter = new NewsListAdapter(MainActivity.this, R.layout.list_item_news);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = presenter.getIntentOnNewsItemClick(position);
                startActivity(intent);
            }
        });
        presenter.loadData();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onResult(List <NewsEntity> newsItemList) {
        adapter.addAll(newsItemList);
        adapter.notifyDataSetChanged();
    }

}
