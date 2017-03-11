package news.agoda.com.sample;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

/**
 * This represents a news item
 */
public class NewsEntity {
    private static final String TAG = NewsEntity.class.getSimpleName();
    private String title = null;
    private String summary;
    private String articleUrl;
    private String byline;
    private String publishedDate;
    private List<MediaEntity> mediaEntityList;

    public NewsEntity(JSONObject jsonObject, List<MediaEntity> _mediaEntityList) {
            initValue(jsonObject, "title");
            /*summary = jsonObject.getString("abstract");
            articleUrl = jsonObject.getString("url");
            byline = jsonObject.getString("byline");
            publishedDate = jsonObject.getString("published_date");*/

            mediaEntityList = _mediaEntityList;
    }

    private void initValue(JSONObject json, String tag){
        try {
            switch (tag){
                case "title" :
                    title = json.getString("title");
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

    public String getArticleUrl() {
        return articleUrl;
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
}
