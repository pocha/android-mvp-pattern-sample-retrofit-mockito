package news.agoda.com.sample;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pocha on 20/03/17.
 */

public class FakeDeserializer implements JsonDeserializer<List<NewsEntity>> {

    public List<NewsEntity> deserialize(JsonElement json, Type type, JsonDeserializationContext context) throws JsonParseException {
        List<MediaEntity> mediaEntityList = new ArrayList<>();
        NewsEntity newsEntity = new NewsEntity("title","summary","http://google.com", mediaEntityList);
        List<NewsEntity> newsEntities = new ArrayList<>();
        newsEntities.add(newsEntity);
        return newsEntities;
    }
}
