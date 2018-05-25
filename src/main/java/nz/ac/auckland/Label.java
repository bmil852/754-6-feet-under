package nz.ac.auckland;

import java.util.ArrayList;
import java.util.List;

public class Label {
    public String label="";
    private List<Keyword> keywords = new ArrayList<Keyword>();

    public void formLabel(){
        for(int i=0; i<keywords.size()-1; i++){
            label += keywords.get(i).word + " ";
        }
        label += keywords.get(keywords.size()-1).word;
    }

    public void addKeyword(Keyword k){
        keywords.add(k);
    }
}
