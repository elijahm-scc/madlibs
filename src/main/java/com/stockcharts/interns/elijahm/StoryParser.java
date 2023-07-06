package main.java.com.stockcharts.interns.elijahm;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class StoryParser {
    
    public static Map<Integer, Story> cache = new HashMap<>();

    public static Story parseStory(int id) {
        if (cache.containsKey(id))
            return cache.get(id);
        Story story = null;
        try {
            StringBuilder sb = new StringBuilder();
            File myObj = new File("/Users/elijahm/repos/madlibs/stories/story" + id + ".json");
            Scanner reader = new Scanner(myObj);
            while (reader.hasNextLine()) {
                sb.append(reader.nextLine());
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(sb.toString());

            int adjectiveCount = jsonObject.getInt("adjective");
            int nounCount = jsonObject.getInt("noun");
            int verbCount = jsonObject.getInt("verb");
            String storyText = jsonObject.getString("text");

            story = new Story(id, adjectiveCount, nounCount, verbCount, storyText);

        } catch (Exception e) {
            e.printStackTrace();
        }
        cache.put(id, story);
        return story;
    }
}


