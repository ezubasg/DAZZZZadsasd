package daz.lmis_light;


import java.util.List;
import java.util.Map;

/**
 * Created by Zubair<rajazubair.asghar@gmail.com> on 20.11.15.;
 */
public class Message {


    public  Message()
    {}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public List<Map<String, String>> getUsers() {
        return users;
    }

    public void setUsers(List<Map<String, String>> users) {
        this.users = users;
    }

    private String subject;
    private String text;
    private List<Map<String,String>> users;

    public List<Map<String, String>> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<Map<String, String>> userGroups) {
        this.userGroups = userGroups;
    }

    public List<Map<String, String>> getOrganisationUnits() {
        return organisationUnits;
    }

    public void setOrganisationUnits(List<Map<String, String>> organisationUnits) {
        this.organisationUnits = organisationUnits;
    }

    private List<Map<String,String>> userGroups;
    private List<Map<String,String>> organisationUnits;

}