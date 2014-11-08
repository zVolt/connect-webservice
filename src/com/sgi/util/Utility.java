package com.sgi.util;
import java.security.MessageDigest;
import java.util.ArrayList;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import com.sgi.constants.Constants;
import com.sgi.util.Faculty.FacultyMin;
import com.sgi.util.Student.StudentMin;
public class Utility {
	public static String ConstructJSON(String tagr,boolean statusr,String msg){ 
		JSONObject obj=new JSONObject();
		try{
			obj.put("tag",tagr);
			obj.put("status",statusr);
			if(statusr)
				obj.put("token",msg);
			else
				obj.put("error",msg);
		}
		catch(Exception e){
			return ConstructJSON(tagr,false,e.getMessage());
		}
		return obj.toString();  
	}
	
	
	public static String ConstructJSONArray(ArrayList<?> data,String metadata) throws JSONException{
		//JSONObject obj=new JSONObject();
		JSONArray obja=new JSONArray();
		
		for(Object str:data){
			if(metadata.equalsIgnoreCase("student")){
				StudentMin tmpusr=(StudentMin) str;
				JSONObject tmpobj=new JSONObject();
				
				tmpobj.put(Constants.FIRST_NAME, tmpusr.f_name);
				tmpobj.put(Constants.LAST_NAME,tmpusr.l_name);
				tmpobj.put(Constants.L_ID, tmpusr.l_id);
				tmpobj.put(Constants.PROFILE_IMAGE, tmpusr.picUrl);
				tmpobj.put(Constants.DEPARTMENT,tmpusr.branch);
				tmpobj.put(Constants.YEAR, tmpusr.year);
				tmpobj.put(Constants.SECTION, tmpusr.section);
				tmpobj.put(Constants.COURSE, tmpusr.course);
				
				obja.put(tmpobj);
			}
			else{
				FacultyMin tmpusr=(FacultyMin)str;
				JSONObject tmpobj=new JSONObject();
		
				tmpobj.put(Constants.FIRST_NAME, tmpusr.f_name);
				tmpobj.put(Constants.LAST_NAME,tmpusr.l_name);
				tmpobj.put(Constants.PROFILE_IMAGE, tmpusr.picUrl);
				tmpobj.put(Constants.DEPARTMENT,tmpusr.dep);
				tmpobj.put(Constants.COURSE,tmpusr.course);
				tmpobj.put(Constants.L_ID, tmpusr.l_id);
				obja.put(tmpobj);
			}
		}
//		System.out.println(obja.toString());
		return obja.toString();
	}
	
	public static String sha1(String input){
		try{
	        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
	        byte[] result = mDigest.digest(input.getBytes());
	        StringBuffer sb = new StringBuffer();
	        for (int i = 0; i < result.length; i++) {
	            sb.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));
	        }
	        return sb.toString();
        }
		catch(Exception ex){
			ex.printStackTrace();
        	return null;
        }
    }
	public static String getToken(String input){
		String token=sha1(input);
		return token;
	}
}