
import java.util.ArrayList;

public class RelatedWord {
    public static ArrayList<String> usedwords = new ArrayList<String>();
    public ArrayList<String> relatedwords;
    public ArrayList<String> rhymingwords;

    RelatedWord(String Word){
        usedwords.add(Word);
        relatedwords = getRelatedWords(Word);
        rhymingwords = getRhymingWords(Word);
    }

    public ArrayList<String> getRelatedWords(String Word){
        ArrayList<String> relatedwords = new ArrayList<String>();
        return(getWordsFromDataMuse("https://api.datamuse.com/words?rel_trg=" + Word));
    }

    public ArrayList<String> getRhymingWords(String Word){
        ArrayList<String> rhymingwords = new ArrayList<String>();
        return(getWordsFromDataMuse("https://api.datamuse.com/words?rel_rhy=" + Word));
    }

    public ArrayList<String> getWordsFromDataMuse(String url){
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

    public boolean isRelatedTo(String Word){
        return relatedwords.contains(Word);
    }

    public boolean isRhymeWith(String Word){
        return rhymingwords.contains(Word);
    }

}
