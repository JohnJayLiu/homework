package recommend;

import vo.StockInfo;
import vo.UserInterest;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

import javafx.util.Pair;

import segmenter.ChineseSegmenterImpl;
import tf_idf.TF_IDFImpl;
public class RecommenderImpl implements Recommender {
	ChineseSegmenterImpl chineseSegmenterImpl=new ChineseSegmenterImpl();
	TF_IDFImpl tf_idfImpl=new TF_IDFImpl();
    /**
     * this function need to calculate stocks' content similarity,and return the similarity matrix
     *
     * @param stocks stock info
     * @return similarity matrix
     */
    @Override
    public double[][] calculateMatrix(StockInfo[] stocks) {
        //TODO: write your code here
    	double [][]matrix=new double[stocks.length][stocks.length];
    	List <String>wordsList=new LinkedList<>();
    	for(int i=0;i<stocks.length;i++) {
    		String string=stocks[i].getContent();
    		List <String >list=new LinkedList<>();
    		try {
				list=chineseSegmenterImpl.getWordsFromString(string);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		Pair <String,Double>[]pairs=tf_idfImpl.getResult(list, stocks);
    		for(int j=0;j<20;j++)wordsList.add(pairs[j].getKey());
    	}
    	for(int i=0;i<stocks.length;i++) {
    		for (int j=0;j<stocks.length;j++) {
    			matrix[i][j]=calculateSmimilar(stocks[i].getContent(),stocks[j].getContent(),wordsList);
    		}
    	}

    	
        return matrix;
    }

    double calculateSmimilar(String str1,String str2,List<String >standarList) {
    	List<String >list1=new LinkedList<>();
    	List<String >list2=new LinkedList<>();
    	try {
			list1=chineseSegmenterImpl.getWordsFromString(str1);
			list2=chineseSegmenterImpl.getWordsFromString(str2);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Vector<Double>vector1=new Vector<>();
    	Vector<Double>vector2=new Vector<>();
    	for (String str:standarList) {
    		int count1=0,count2=0;
    		for (String string:list1) {
    			if (str==string)count1++;
    		}
    		for (String string:list2) {
    			if (str==string)count2++;
    		}
    		vector1.add((double)count1/list1.size());
    		vector2.add((double)count2/list2.size());
    	}
    	
    	return cos(vector1, vector2);
    }
    double cos(Vector<Double>vector1,Vector<Double>vector2) {
    	double cosine=0.0;
    	double length1=0,length2=0;
    	for (int i=0;i<vector1.size();i++) {
    		cosine+=vector1.get(i)*vector2.get(i);
    		length1+=vector1.get(i)*vector1.get(i);
    		length2+=vector2.get(i)*vector2.get(i);
    	}
    	cosine=cosine/(Math.sqrt(length2*length1));
    	return cosine;
    }
    /**
     * this function need to recommend the most possibility stock number
     *
     * @param matrix       similarity matrix
     * @param userInterest user interest
     * @return commend stock number
     */
    @Override
    public double[][] recommend(double[][] matrix, UserInterest[] userInterest) {
    	double [][]recommend=new double[userInterest.length][10];
    	for (int i=0;i<userInterest.length;i++) {
    		double []interest=new double [matrix.length];
    		for (int j=0;j<matrix.length;j++) {
    			if(userInterest[i].GetInterest(j)==1)interest[j]=-1;
    			else {
    				interest[j]=0;
    				for (int n=0;n<matrix.length;n++)interest[j]+=(userInterest[i].GetInterest(j)*matrix[j][n]);
    			}
    		}
    		double []count=new  double[10];
    		for(int j=0;j<matrix.length;j++ ) {
    			count [j]=j;
    		}
    		for (int j=0;j<matrix.length;j++) {
    			for (int n=j;n<matrix.length;n++) {
    				if (interest[j]<interest[n]) {
    					double temp=interest[j];
    					interest[j]=interest[n];
    					interest[n]=temp;
    					temp=count [j];
    					count[j]=count[n];
    					count[n]=temp;
    				}
    			}
    		}
    		for(int j=0;j<10;j++) {
    			recommend[i][j]=interest[j];
    		}
    	}
        //TODO: write your code here
    	
        return recommend;
    }
}
