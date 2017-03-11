package news.agoda.com.sample;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a media item
 */
public class MediaEntity {
    private static final String TAG = MediaEntity.class.getSimpleName();
    private String url;
    private String format;
    private int height;
    private int width;
    private String type;
    private String subType;
    private String caption;
    private String copyright;

    public MediaEntity(JSONObject jsonObject)  {
        initValue(jsonObject, "url");
        /*url = jsonObject.getString("url");
        format = jsonObject.getString("format");
        height = jsonObject.getInt("height");
        width = jsonObject.getInt("width");
        type = jsonObject.getString("type");
        subType = jsonObject.getString("subtype");
        caption = jsonObject.getString("caption");
        copyright = jsonObject.getString("copyright");*/
    }

    public static List<MediaEntity> parseMediaEntities(JSONObject newsObject){
        List<MediaEntity> mediaEntityList = new ArrayList<>();
        try {
            JSONArray mediaArray = newsObject.getJSONArray("multimedia");
            for (int i = 0; i < mediaArray.length(); i++) {
                JSONObject mediaObject = mediaArray.getJSONObject(i);
                MediaEntity mediaEntity = new MediaEntity(mediaObject);
                if (mediaEntity.getUrl() != null) {
                    mediaEntityList.add(mediaEntity);
                }
            }
        } catch (JSONException e) {
            Log.e(TAG,e.getMessage());
        }
        return mediaEntityList;
    }

    private void initValue(JSONObject json, String tag){
        try {
            switch (tag){
                case "url" :
                    url = json.getString("url");
                    break;
                default :
                    Log.e(TAG, "found unsupported " + tag);
            }

        } catch (JSONException e){
            Log.e(TAG,"malformed " + tag + " skipping it");
        }
    }

    public String getUrl() {
        return url;
    }

    public String getFormat() {
        return format;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public String getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    public String getCaption() {
        return caption;
    }

    public String getCopyright() {
        return copyright;
    }

}
