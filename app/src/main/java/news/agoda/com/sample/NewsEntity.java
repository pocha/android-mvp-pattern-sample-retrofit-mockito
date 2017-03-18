package news.agoda.com.sample;

import android.util.Log;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * This represents a news item
 */
public class NewsEntity {
    private static final String TAG = NewsEntity.class.getSimpleName();
    @SerializedName("title")
    private String title = null;
    @SerializedName("abstract")
    private String summary = null;
    @SerializedName("url")
    private String url = null;
    private String byline;
    private String publishedDate;
    @SerializedName("multimedia")
    private List<MediaEntity> mediaEntityList;

    public NewsEntity(String _title, String _summary, String _url, List<MediaEntity> _mediaEntityList){
        title = _title;
        summary = _summary;
        url = _url;
        mediaEntityList = _mediaEntityList;
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
