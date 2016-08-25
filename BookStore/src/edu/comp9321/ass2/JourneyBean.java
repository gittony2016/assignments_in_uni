package edu.comp9321.ass2;

import java.util.*; 
import java.io.Serializable;

/*
 * This keeps a track of the person's chosen itinerary
 */
public class JourneyBean implements Serializable { 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String error;
    private String user_email;
    private String user_name;  
    private String user_id;
    private String user_password;
    private Vector<String> item_name;
    private Vector<String> item_id;
    private Vector<String> list_name;
    private Vector<String> url;
    public JourneyBean() {
    	item_name = new Vector<String>();
    	list_name = new Vector<String>();
    }
    public Vector<String> getItem_name() {  
        return item_name;
    }  
    public void setItem_name(Vector<String> item_name) {  
        this.item_name = item_name;  
    }
    public Vector<String> getUrl() {  
        return url;
    }  
    public void setUrl(Vector<String> url) {  
        this.url = url;  
    }
    public Vector<String> getItem_id() {  
        return item_id;
    }  
    public void setItem_id(Vector<String> item_id) {  
        this.item_id = item_id;  
    }
    public String getUser_id() {  
        return user_id;
    }  
    public void setUser_id(String user_id) {  
        this.user_id = user_id;  
    }
    public Vector<String> getList_name() {  
        return list_name;
    }  
    public void setList_name(Vector<String> list_name) {  
        this.list_name = list_name;  
    }
	public String getError() {  
        return error;
    }  
    public void setError(String error) {  
        this.error = error;  
    }  
    public String getUser_email() {  
        return user_email;  
    }  
    public void setUser_email(String userEmail) {  
        user_email = userEmail;  
    }  
    public String getUser_name() {  
        return user_name;  
    }  
    public void setUser_name(String userName) {  
        user_name = userName;  
    }  
    public String getUser_password() {  
        return user_password;  
    }  
    public void setUser_password(String userPassword) {  
        user_password = userPassword;  
    }  
    
    public int[] randomArray(int min,int max,int n){  
        int len = max-min+1;  
          
        if(max < min || n > len){  
            return null;  
        } 
        int[] source = new int[len];  
           for (int i = min; i < min+len; i++){  
            source[i-min] = i;  
           }  
             
           int[] result = new int[n];  
           Random rd = new Random();  
           int index = 0;  
           for (int i = 0; i < result.length; i++) {  
               index = Math.abs(rd.nextInt() % len--);  
               result[i] = source[index];  
               source[index] = source[len];  
           }  
           return result;  
    }  

	
}