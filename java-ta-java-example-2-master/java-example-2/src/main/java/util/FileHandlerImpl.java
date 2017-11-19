package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import vo.StockInfo;
import vo.UserInterest;

public class FileHandlerImpl implements FileHandler {

    /**
     * This func gets stock information from the given interfaces path.
     * If interfaces don't exit,or it has a illegal/malformed format, return NULL.
     * The filepath can be a relative path or a absolute path
     *
     * @param filePath
     * @return the Stock information array from the interfaces,or NULL
     * @throws IOException 
     */
    @Override
   public StockInfo[] getStockInfoFromFile(String filePath) throws IOException  {
    	
    	File readerFile =new File( filePath );
		InputStreamReader icp;
		
			icp = new InputStreamReader(new FileInputStream(readerFile));
		
		BufferedReader reader=new BufferedReader(icp);
		String str=null;
		StockInfo []stoInfo=new StockInfo [60];
		int i=0;
		
			for(;i<stoInfo.length&&(str = reader.readLine())!=null;i++) {
				System.out.println(str); 
					if (i==0)continue;
					else {
						String []stringArray=str.split("\t");
						stoInfo[i-1]=new StockInfo();
						stoInfo[i-1].setID(stringArray [0]);
						stoInfo[i-1].setTitle(stringArray[1]);
						stoInfo[i-1].setAuthor(stringArray[2]);
						stoInfo[i-1].setDate(stringArray[3]);
						stoInfo[i-1].setLastUpdate(stringArray[4]);
						stoInfo[i-1].setContent(stringArray[5]);
						stoInfo[i-1].setAnswerAuthor(stringArray[6]);
						stoInfo[i-1].setAnswer(stringArray[7]);
						
					}
					System.out.println(i);
				
		 
			}
			reader.close();
		
		return stoInfo ;
		}
    /**
     * This func gets user interesting from the given interfaces path.
     * If interfaces don't exit,or it has a illegal/malformed format, return NULL.
     * The filepath can be a relative path or a absolute path
     *
     * @param filePath
     * @return
     */
    @Override
    public UserInterest[] getUserInterestFromFile(String filePath) throws Exception {
		// TODO Auto-generated method stub
    	File readerFile =new File( filePath );
		InputStreamReader icp=new InputStreamReader(new FileInputStream(readerFile));
		BufferedReader reader=new BufferedReader(icp);
		UserInterest[] userInterests=new UserInterest[500];
		char[]chars =new char [60];
		for (int i=0;i<500;i++) {
			String str=reader.readLine();
			chars=str.toCharArray();
			for (int j=0;j<60;j++) {
				userInterests[i].SetInterest(chars[j]-'0', j);
			}
		}
    	reader.close();
        return userInterests;
    }

    /**
     * This function need write matrix to files
     *
     * @param matrix the matrix you calculate
     */
    @Override
    public void setCloseMatrix2File(double[][] matrix) throws Exception{
    	File writeFile =new File("matrix.txt");
		BufferedWriter writer=null;
		writer=new BufferedWriter(new FileWriter(writeFile));
    	for (int i=0;i<matrix.length;i++) {
    		for (int j=0;j<matrix.length;j++) {
    			writer.write(matrix[i][j]+"/t");
    		}
    		writer.write("/n");
    	}
    	writer.close();
        //TODO: write your code here
    }

    /**
     * This function need write recommend to files
     *
     * @param recommend the recommend you calculate
     */
    @Override
    public void setRecommend2File(double[][] recommend) throws Exception{
        //TODO: write your code here
    	File writeFile =new File("recommend.txt");
		BufferedWriter writer=null;
		writer=new BufferedWriter(new FileWriter(writeFile));
    	for (int i=0;i<500;i++) {
    		for (int j=0;j<10;j++) {
    			writer.write(recommend[i][j]+"/t");
    		}
    		writer.write("/n");
    	}
    	writer.close();
    }
}
