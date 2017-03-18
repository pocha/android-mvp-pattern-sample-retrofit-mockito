package news.agoda.com.sample;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.ArrayList;
import java.util.List;

public class MainActivity
        extends ListActivity
{

    private static final String TAG = MainActivity.class.getSimpleName();
    private List<NewsEntity> newsItemList;
    private NewsListAdapter adapter;

    private static MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Fresco.initialize(this);

        newsItemList = new ArrayList<>();
        adapter = new NewsListAdapter(MainActivity.this, R.layout.list_item_news, newsItemList);
        setListAdapter(adapter);


        ListView listView = getListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                NewsEntity newsEntity = newsItemList.get(position);

                Intent intent = new Intent(MainActivity.this, DetailViewActivity.class);
                intent.putExtra("title", newsEntity.getTitle());
                intent.putExtra("storyURL", newsEntity.getUrl());
                intent.putExtra("summary", newsEntity.getSummary());
                intent.putExtra("imageURL", newsEntity.getImageUrl());
                startActivity(intent);
            }
        });

        presenter = new MainActivityPresenter(this);
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



    public void onResult(List <NewsEntity> _newsItemList) {
        newsItemList = _newsItemList;
        adapter.addAll(newsItemList);
        adapter.notifyDataSetChanged();
    }

}
