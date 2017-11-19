package segmenter;


import vo.StockInfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

import org.ansj.domain.Result;
import org.ansj.domain.Term;
import org.ansj.recognition.impl.StopRecognition;
import org.ansj.splitWord.analysis.ToAnalysis;

public class ChineseSegmenterImpl implements ChineseSegmenter {
	StopRecognition getStopWords() throws IOException {
		StopRecognition recognition=new StopRecognition();
		String stopWordTable="stopWordTable.txt";
		File f = new File(stopWordTable);
		FileInputStream fileInputStream = new FileInputStream(f);
		//读入停用词文件
		BufferedReader StopWordFileBr=new BufferedReader(new InputStreamReader(fileInputStream));
		String stopWord =null;
		List <String >stopWords= new LinkedList<String>();
		for(;(stopWord=StopWordFileBr.readLine())!=null;){
			stopWords.add(stopWord);
			}
		StopWordFileBr.close();
		recognition.insertStopWords(stopWords);
		
		recognition.insertStopNatures("uj");//过滤词性
		recognition.insertStopNatures("ul");//
		recognition.insertStopNatures("null");//过滤空格
		recognition.insertStopNatures("w");//过滤标点符号
		recognition.insertStopRegexes(".*?\\w.*?"); //正则表达式,去掉包含数字和字母的
		recognition.insertStopRegexes("\\s"); //正则表达式,去掉空格
		return recognition;
	}

    /**
     * this func will get chinese word from a list of stocks. You need analysis stocks' answer and get answer word.
     * And implement this interface in the class : ChineseSegmenterImpl
     * Example: 我今天特别开心 result : 我 今天 特别 开心
     *
     * @param stocks stocks info
     * @return chinese word
     * @see ChineseSegmenterImpl
     */
    @Override
    public List<String> getWordsFromInput(StockInfo[] stocks) throws IOException {
    	StopRecognition stopRecognition=getStopWords();
    	List<String>stockWords=new LinkedList<String>();
    	int count =0;

    	while (count<stocks.length) {
    		Result result = ToAnalysis.parse(stocks[count].getAnswer()).recognition(stopRecognition);
    		List <Term>term=result.getTerms();
    		for (int i=0;i<term.size();i++) {
    			String string=term.get(i).getName();
    			stockWords.add(string);
    		}
    		count ++;
    	}
			
        //TODO: write your code here
        return stockWords;
    }
    public List<String> getWordsFromString(String str) throws IOException{
    	List<String>words=new LinkedList<String>();
    	

    	StopRecognition stopRecognition=getStopWords();
    	
    		Result result = ToAnalysis.parse(str).recognition(stopRecognition);
    		List <Term>term=result.getTerms();
    		for (int i=0;i<term.size();i++) {
    			String string=term.get(i).getName();
    			
    			words.add(string);
    	}
    		return words;
    }
}
