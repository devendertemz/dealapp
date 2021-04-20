package com.deals.dealapp.Service;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class jsonParser {

    private HashMap<String, String> parsejsonObject(JSONObject object) {
        HashMap<String, String> datalist = new HashMap<>();
        try {

            String name = object.getString("name");
            //Get Latitude from object
            String latitude = object.getJSONObject("geometry")
                    .getJSONObject("location").getString("lat");
            //Get Longtitude from object

            String longitude = object.getJSONObject("geometry").getJSONObject("location").getString("lng");

            //put all value in hash map
            datalist.put("name", name);
            datalist.put("lat", latitude);
            datalist.put("lng", longitude);


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return datalist;


    }


    private List<HashMap<String, String>> parseJsonArray(JSONArray jsonArray) {
        List<HashMap<String, String>> dataList = new ArrayList<>();
        for (int i = 0; i<jsonArray.length(); i++) {
            //initialise hasp map
            try {


                HashMap<String, String> data = parsejsonObject((JSONObject) jsonArray.get(i));
                //Add data in hash map

                dataList.add(data);


            } catch (JSONException e) {

                e.printStackTrace();
            }


        }

        return dataList;

    }

    public List<HashMap<String, String>> parseResult(JSONObject object) {
        //ini json array
        JSONArray jsonArray = null;
        //get Result array
        try {
            jsonArray = object.getJSONArray("results");

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // return array
        return parseJsonArray(jsonArray);

    }
}
