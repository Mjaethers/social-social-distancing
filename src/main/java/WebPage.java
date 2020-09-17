import java.io.*;
import java.net.URL;
import java.net.URLConnection;

public class WebPage {
    String pageContents;
    WebPage(String URL){
        StringBuilder stringBuilder = new StringBuilder();
        try{
            String line;
            java.net.URL url = new URL(URL);
            URLConnection connection = url.openConnection();
            //connection.addRequestProperty("User-Agent", "Mozilla");
            connection.setReadTimeout(5000);
            connection.setConnectTimeout(5000);
            connection.connect();
            InputStream is = connection.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            while((line = bufferedReader.readLine()) != null){
                stringBuilder.append(line);
                stringBuilder.append("\n");
            }
            bufferedReader.close();
            pageContents = stringBuilder.toString();
        }catch(Exception e){
            System.out.println(e);
            pageContents = "ded";
        }
    }



    public String readPage(){
        return(pageContents);
    }

    public String readLine(int line) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new StringReader(pageContents));
        for(int i = 0; i<line; i++) {
            bufferedReader.readLine();
        }
        return(bufferedReader.readLine());
    }
}
