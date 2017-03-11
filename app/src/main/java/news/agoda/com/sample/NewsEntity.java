package news.agoda.com.sample;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This represents a news item
 */
public class NewsEntity {
    private static final String TAG = NewsEntity.class.getSimpleName();
    private String title = null;
    private String summary = null;
    private String url = null;
    private String byline;
    private String publishedDate;
    private List<MediaEntity> mediaEntityList;

    public NewsEntity(JSONObject jsonObject, List<MediaEntity> _mediaEntityList) {
            initValue(jsonObject, "title");
            initValue(jsonObject, "summary");
            initValue(jsonObject, "url");
            /*summary = jsonObject.getString("abstract");
            articleUrl = jsonObject.getString("url");
            byline = jsonObject.getString("byline");
            publishedDate = jsonObject.getString("published_date");*/

            //dependency injection - initializing outside the class
            mediaEntityList = _mediaEntityList;
    }

    public static List<NewsEntity> parseNewsEntities(String jsonData){
        List<NewsEntity> newsItemList = new ArrayList<>();
        try {
            JSONObject jsonObject = new JSONObject(jsonData);
            JSONArray resultArray = jsonObject.getJSONArray("results");
            //Log.d(TAG, "json data " + resultArray.toString(2));

            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject newsObject = resultArray.getJSONObject(i);
                List<MediaEntity> mediaEntityList = MediaEntity.parseMediaEntities(newsObject);
                NewsEntity newsEntity = new NewsEntity(newsObject,mediaEntityList);
                if (newsEntity.getTitle() != null) {
                    newsItemList.add(newsEntity);
                }
            }
        } catch (JSONException e) {
            Log.e(TAG, "fail to parse json string");
        }
        return newsItemList;
    }

    private void initValue(JSONObject json, String tag){
        try {
            switch (tag){
                case "title" :
                    title = json.getString("title");
                    break;
                case "summary" :
                    summary = json.getString("abstract");
                    break;
                case "url" :
                    url = json.getString("url");
                    break;
                default :
                    Log.e(TAG,"found unsupported " + tag);
            }

        } catch (JSONException e){
            Log.e(TAG,"malformed " + tag + " skipping it");
        }
    }


    public String getTitle() {
        return title;
    }

    public String getSummary() {
        return summary;
    }

    public String getUrl() {
        return url;
    }

    public String getByline() {
        return byline;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public List<MediaEntity> getMediaEntity() {
        return mediaEntityList;
    }

    public String getImageUrl(){
        try {
            return mediaEntityList.get(0).getUrl();
        }
        catch (IndexOutOfBoundsException e){
            Log.e("NewsListAdapter","No media entity object found for " + getTitle());
        }
        return null;
    }
}
