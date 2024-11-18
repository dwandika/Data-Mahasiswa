package datamahasaiswa;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.*;
/**
 *
 * @author USER
 */
public class DataMahasiswa {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        HttpResponse<String> response = Unirest.post("https://siakad.itmnganjuk.ac.id/api/select")
                .header("Content-Type", "application/json")
                .header("Cookie", "siakaditm1=3aeb2o5ff287t6mf08gnoq544t")
                .body("{\"token\":\"ufb2a73ed1e2bae2403ea3b3e9b5eb86ed6fdb66b49\",\r\n\"query\":\"SELECT * FROM t_mhs WHERE angkatan = 2023 AND deleted = 0 limit 200\"}")
                .asString();
        
        String jsonResponse = response.getBody();
        JSONObject obj = new JSONObject(jsonResponse);
        int rows = obj.getInt("rows");
        JSONArray results =obj.getJSONArray("results");
        if (rows != 0) {
            for (int i = 0; i < results.length(); i++) {
                JSONObject item = results.getJSONObject(i);
                String nama = item.getString("mhs_nama");
                System.out.println(nama);
            }
        }   
//        System.out.println(results);
        

    }
    
}
