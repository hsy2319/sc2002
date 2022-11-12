package main;
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Essh
 */
import java.util.HashMap;
public class AdminData {
    HashMap<String,String> logindata = new HashMap<String,String>();
    AdminData()
    {
        logindata.put("james","bond123");
        logindata.put("ben","7eleven");
        logindata.put("tracy","abc123");
    }
    protected HashMap getLoginData()
    {
        return logindata;
    }
}
