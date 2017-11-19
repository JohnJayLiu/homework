package tf_idf;

import javafx.util.Pair;
import util.StockSorter;
import util.StockSorterImpl;
import vo.StockInfo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TF_IDFImpl implements TF_IDF {
	StockSorterImpl stockSorterImpl=new StockSorterImpl();
    /**
     * this func you need to calculate words frequency , and sort by frequency.
     * you maybe need to use the sorter written by yourself in example 1
     *
     * @param words the word after segment
     * @return a sorted words
     * @see StockSorter
     */
    @Override
    public Pair<String, Double>[] getResult(List<String> words, StockInfo[] stockInfos) {
    	HashMap<String , Double >mapTF=new HashMap<>();
    	HashMap<String , Double>mapTF_IDF=new HashMap<>();
    	for (String str:words) {
    		double value=1.0/(double)words.size();
    		if(mapTF.get(str)!=null)
    		{
    			value=mapTF.get(str)+1.0/(double)words.size();
    		}
    		mapTF.put(str, value);
    	}
    	int count =0;
    	for (String key:words) {
    		if (mapTF_IDF.get(key)!=null)
    			continue;
    		for (int i=0;i<stockInfos.length;i++)
    			if (stockInfos[i].getContent().contains(key)) {
    				count++;
    				}
    		double value=Math.log((double)stockInfos.length/(count+1));
    		mapTF_IDF.put(key, value*mapTF.get(key));
    	}
        //TODO: write your code here
    	Pair<String , Double>[]pairs=new Pair[mapTF_IDF.size()];
    	int n=0;
    	for (Map.Entry<String, Double>entry:mapTF_IDF.entrySet()) {
    		Pair<String,Double >pair=new Pair<String, Double>(entry.getKey(), entry.getValue());
    		pairs[n]=pair;
    		n++;
    	}
    	pairs=stockSorterImpl.pairSort(pairs);
        return pairs;
    }
}
