package daz.lmis_light;

import java.util.List;
import java.util.Map;



public class DataPopulator {

    private List<String> userNames;

    public List<String> getUserNames() {
        return userNames;
    }

    public void setUserNames(List<String> userNames) {
        this.userNames = userNames;
    }

    public Map<String, String> getUserIDMapper() {
        return userIDMapper;
    }

    public void setUserIDMapper(Map<String, String> userIDMapper) {
        this.userIDMapper = userIDMapper;
    }

    private Map<String,String> userIDMapper;


}
