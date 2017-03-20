package news.agoda.com.sample;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.reflect.Type;
import java.util.List;


/**
 * Created by pocha on 20/03/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class NewsDeserializerTest {
    private NewsDeserializer newsDeserializer;
    private JsonElement je;
    private Gson gson;

    @Mock
    Type type;
    @Mock
    JsonDeserializationContext jdc;
    @Mock
    Context context;

    @Before public void initialize(){
        newsDeserializer = new NewsDeserializer();
        gson = new GsonBuilder().create();
    }

    @Test
    public void testHappyFlow(){
        JsonElement je = parseLocalJson("news_data.json");
        String title = je.getAsJsonObject()
                        .getAsJsonArray("results").get(0)
                        .getAsJsonObject().get("title").getAsString();
        System.out.println("title - " + title);
        List<NewsEntity> newsEntities = newsDeserializer.deserialize(je,type,jdc);
        Assert.assertEquals(newsEntities.get(0).getTitle(),title);
    }

    private JsonElement parseLocalJson(String localFile){

        //InputStream is = context.getResources().openRawResource(localFile);
        InputStream is = getClass().getResourceAsStream(localFile);
        Writer writer = new StringWriter();
        char[] buffer = new char[1024];
        try {
            Reader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            int n;
            while ((n = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, n);
            }
            is.close();
        } catch (Exception e){
            e.printStackTrace();
        }

        String jsonString = writer.toString();
        return gson.fromJson(jsonString, JsonElement.class);
    }
}
