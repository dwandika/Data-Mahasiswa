package kelas;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import kong.unirest.HttpResponse;
import kong.unirest.Unirest;
import org.json.*;


public class Mahasiswa {
    String token = "ufb2a73ed1e2bae2403ea3b3e9b5eb86ed6fdb66b49";
    String query ;
    private static JSONArray dataMhs;
    private static int baris;

    public static JSONArray getDataMhs() {
        return dataMhs;
    }

    public static void setDataMhs(JSONArray dataMhs) {
        Mahasiswa.dataMhs = dataMhs;
    }

    public static int getBaris() {
        return baris;
    }

    public static void setBaris(int baris) {
        Mahasiswa.baris = baris;
    }
   
    public JSONObject getMahasiswa(){
        query = "SELECT * FROM t_mhs WHERE angkatan = 2023 AND deleted = 0 limit 200";
        HttpResponse<String> response = Unirest.post("https://siakad.itmnganjuk.ac.id/api/select")
                .header("Content-Type", "application/json")
                .header("Cookie", "siakaditm1=3aeb2o5ff287t6mf08gnoq544t")
                .body("{\"token\":\""+token+"\",\r\n\"query\":\""+query+"\"}")
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
        return obj;
    }
    public static ImageIcon base64ToImage(String base64String, int width, int height) {
        try {
            if (base64String.contains(",")) {
                base64String = base64String.split(",")[1];
            }
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            ByteArrayInputStream bis = new ByteArrayInputStream(decodedBytes);
            BufferedImage bufferedImage = ImageIO.read(bis);
            ImageIcon image = new ImageIcon(bufferedImage.getScaledInstance(width, height, Image.SCALE_SMOOTH));
            return image;
        } catch (Exception e) {
            e.printStackTrace();
             return null;
        }
    }
}
