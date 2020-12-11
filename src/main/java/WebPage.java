import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

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
    public static ArrayList<String> getWordsFromDataMuse(String url){
        WebPage datamusereference = new WebPage(url);
        ArrayList<String> words = new ArrayList<String>();
        String datamuselist = datamusereference.pageContents;

        int index = datamuselist.indexOf("word\":");
        while(index < datamuselist.lastIndexOf("word\":")){
            words.add(datamuselist.substring(index + 7, datamuselist.indexOf('\"', index + 9)));
            index = datamuselist.indexOf("word\":",index + 1);
        }
        return(words);
    }
    public static ArrayList<String> getRelatedWords(String Word){
        ArrayList<String> relatedwords = new ArrayList<String>();
        return(getWordsFromDataMuse("https://api.datamuse.com/words?rel_trg=" + Word));
    }

    public static ArrayList<String> getRhymingWords(String Word){
        ArrayList<String> rhymingwords = new ArrayList<String>();
        return(getWordsFromDataMuse("https://api.datamuse.com/words?rel_rhy=" + Word));
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

