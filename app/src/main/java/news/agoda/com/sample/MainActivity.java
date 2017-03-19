package news.agoda.com.sample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

public class MainActivity
        extends ListActivity
{

    private static final String TAG = MainActivity.class.getSimpleName();
    private NewsListAdapter adapter;

    private static MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new NewsListAdapter(MainActivity.this, R.layout.list_item_news);
        setListAdapter(adapter);

        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = presenter.getIntentOnNewsItemClick(position);
                startActivity(intent);
            }
        });

        INewsFetchRetrofit newsFetch = NewsFetchRetrofit.getInstance(new NewsDeserializer());
        presenter = new MainActivityPresenter(this, newsFetch);
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


    public void onResult(List <NewsEntity> newsItemList) {
        adapter.addAll(newsItemList);
        adapter.notifyDataSetChanged();
    }

}
