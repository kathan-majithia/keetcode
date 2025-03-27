
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class JDoodleCompiler {
    private static final String CLIENT_ID = "827b70ecab42259e1c64a85d7bdaf8b6"; // Replace with your JDoodle client ID
    private static final String CLIENT_SECRET = "7949a7421a4129909c34b9e25930d96ac954ded2e8c5333d34c35d42bd2a0432"; // Replace with your JDoodle client secret

    public static void main(String[] args) {
        String code = "#include <iostream>\n" +
                      "using namespace std;\n" +
                      "int main() {\n" +
                      "    cout << \"Hello, World!\" << endl;\n" +
                      "    return 0;\n" +
                      "}";
        
        String response = compileAndRunCPlusPlus(code);
        System.out.println("Response from JDoodle: " + response);
    }

    public static String compileAndRunCPlusPlus(String code) {
        String apiUrl = "https://api.jdoodle.com/v1/execute";
        String jsonInputString = String.format(
            "{\"clientId\":\"%s\",\"clientSecret\":\"%s\",\"script\":\"%s\",\"language\":\"cpp\",\"versionIndex\":\"0\"}",
            CLIENT_ID, CLIENT_SECRET, code.replace("\"", "\\\"")
        );

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = jsonInputString.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream(), "utf-8"));
            StringBuilder response = new StringBuilder();
            String responseLine;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}