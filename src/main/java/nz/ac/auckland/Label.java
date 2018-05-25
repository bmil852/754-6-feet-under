package nz.ac.auckland;

import java.util.ArrayList;
import java.util.List;

public class Label {
    private String label="";
    private List<String> keywords = new ArrayList<String>();

    public void formLabel(){
        for(int i=0; i<keywords.size()-1; i++){
            label += keywords.get(i) + " ";
        }
        label += keywords.get(keywords.size()-1);
    }

    public void addKeyword(Keyword k){
        keywords.add(k.word);
    }

    public boolean checkIfKeywordExists(Keyword k){
        return keywords.contains(k.word);
    }

    public String getLabel(){
        return label;
    }
}
