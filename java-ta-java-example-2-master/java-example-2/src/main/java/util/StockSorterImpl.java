package util;

import java.util.LinkedList;
import java.util.List;

import javafx.util.Pair;

import vo.StockInfo;

public class StockSorterImpl implements StockSorter {
    /**
     * Accepting series of stock info, sorting them ascending according to their comment length.
     * List.sort() or Arrays.sort() are not allowed.
     * You have to write your own algorithms,Bubble sort、quick sort、merge sort、select sort,etc.
     *
     * @param info stock information
     * @return sorted stock
     */
    @Override
    public StockInfo[] sort(StockInfo[] info) {
        //TODO: write your code here
    	for (int i=0;i<info.length;i++) {
			for (int j=i;j<info.length;j++) {
				if (info[i].getAnswerLength()<info[j].getAnswerLength())
				{
					StockInfo tempInfo=new StockInfo();
					tempInfo=(StockInfo)info[i].clone();
					info[i]=(StockInfo)info[j].clone();
					info[j]=(StockInfo)tempInfo .clone();
				}
			}
		}
		return info;
	
    }


    /**
     * Accepting series of stock info, sorting them ascending, descending order.
     *
     * @param info  stock information
     * @param order true:ascending,false:descending
     * @return sorted stock
     */
    @Override
    public StockInfo[] sort(StockInfo[] info, boolean order) {
        //TODO: write your code here
    	if(order) {
			return sort(info);
		}
		else {
			for (int i=0;i<info.length ;i++) {
				int pos=0;
				StockInfo tempInfo=(StockInfo)info[i].clone();
				for (int j=i;j<info.length;j++) {
					
					if (tempInfo.getAnswerLength()<info[j].getAnswerLength())
					{
						tempInfo =(StockInfo)info[j].clone();
						pos=j;
					}
				}
				info[pos]=(StockInfo)info[i].clone();
				info[i]=(StockInfo )tempInfo.clone();
			}
			return info;
	
    }
}
    public  Pair<String , Double>[]pairSort(Pair<String , Double>[]pairs){
    	Pair<String , Double>temp=null;
    	for (int i=0;i<pairs.length;i++) {
    		for(int j=i;j<pairs.length;j++) {
    			if (pairs[i].getValue()<pairs[j].getValue()) {
    				temp=pairs[i];
    				pairs[i]=pairs[j];
    				pairs[j]=temp;
    			}
    		}
    	}
    	
    	return pairs;
    }
}
