package song_test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class test02 {
    public static void main(String[] args) throws JSONException {
        JSONObject iHead = new JSONObject();
        JSONArray iItem = new JSONArray();



        iHead
                .put("BSART", "pzlx")//凭证类型
                .put("HTEXT", "bjsm")//背景说明
        ;


        itItem:
        {

                iItem.put(new JSONObject()
                        .put("BNFPO", "id1")//行项目
                        .put("MATNR", "1")//物料编号
                        .put("TXZ01", "gcmc1")//短文本
                );
                iItem.put(new JSONObject()
                        .put("BNFPO", "id2")//行项目
                        .put("MATNR", "2")//物料编号
                        .put("TXZ01", "gcmc2")//短文本
                );

        }


        assemblyData:{
            String params = new JSONObject()
                    .put("I_HEAD",iHead)
                    .put("I_ITEM", iItem)
                    .toString();
            System.out.println(params);
            System.out.println(new JSONObject().toString());
//            setJsonData(params);
//            setAddressAlias("sap_newPurchaseApplication");
        }
    }
}
