package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        JSONObject jsonAddress = new JSONObject("{\"home\":\"Moscow\"}");

        List<String> list = new ArrayList<>();
        list.add("Father");
        list.add("Mother");
        JSONArray jsonRelatives = new JSONArray(list);

        final Candidate candidate = new Candidate("Ivan", true, 25, new Address("Moscow"),
                new String[]{"Father", "Mother"});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("name", candidate.getName());
        jsonObject.put("serveInArmy", candidate.isServeInArmy());
        jsonObject.put("age", candidate.getAge());
        jsonObject.put("address", jsonAddress);
        jsonObject.put("relatives", jsonRelatives);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(candidate).toString());
    }
}