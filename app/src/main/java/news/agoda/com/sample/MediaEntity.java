package news.agoda.com.sample;

import com.google.gson.annotations.SerializedName;

/**
 * This class represents a media item
 */
public class MediaEntity {
    private static final String TAG = MediaEntity.class.getSimpleName();
    @SerializedName("url")
    private String url = null;
    private String format;
    private int height;
    private int width;
    private String type;
    private String subType;
    private String caption;
    private String copyright;


    public MediaEntity(String url){
        this.url = url;
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
