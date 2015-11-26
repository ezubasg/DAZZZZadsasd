package daz.lmis_light;

import java.util.List;
import java.util.Map;

/**
 * Created by Zubair<rajazubair.asghar@gmail.com>on 23.11.15.
 */
public class DhisRecipients {



    private Map<String,String> pager;
    private List<Map<String,String>> users;
    private List<Map<String,String>> userGroups;
    private List<Map<String,String>> organisationUnits;




    public List<Map<String, String>> getOrganizationalUnits() {
        return organisationUnits;
    }

    public void setOrganizationalUnits(List<Map<String, String>> organizationalUnits) {
        this.organisationUnits = organizationalUnits;
    }

    public List<Map<String, String>> getUserGroups() {
        return userGroups;
    }

    public void setUserGroups(List<Map<String, String>> userGroups) {
        this.userGroups = userGroups;
    }



    public Map<String, String> getPager() {
        return pager;
    }

    public void setPager(Map<String, String> pager) {
        this.pager = pager;
    }


    public List<Map<String, String>> getUsers() {
        return users;
    }

    public void setUsers(List<Map<String, String>> users) {
        this.users = users;
    }



}
