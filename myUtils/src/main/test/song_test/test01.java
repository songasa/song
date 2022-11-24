package song_test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class test01 {
    public static void main(String[] args) throws JSONException {
        JSONArray itItem = new JSONArray();
        JSONObject put = new JSONObject()
                .put("oaOrderNo", "requestid")
                .put("demandDate", "sqrq");

        JSONObject put2 = new JSONObject()
                .put("oaOrderNo", null)
                .put("demandDate", "sqrq2");

        System.out.println(put.toString());
        System.out.println(put2.toString());

        itItem.put(put);
        itItem.put(put2);

        String data = new JSONObject().put("DATA", itItem).toString();
        String data2 = new JSONObject().put("DATA", put2).toString();
//        System.out.println(data);
//        System.out.println(data2);

        String s = null;
        String s1 = "";
//        System.out.println(s1.equals(s));

    }
}
