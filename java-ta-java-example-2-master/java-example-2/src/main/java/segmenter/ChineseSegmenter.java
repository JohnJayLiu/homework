package segmenter;

import vo.StockInfo;

import java.io.IOException;
import java.util.List;

public interface ChineseSegmenter {

    /**
     * this func will get chinese word from a list of stocks. You need analysis stocks' answer and get answer word.
     * And implement this interface in the class : ChineseSegmenterImpl
     * Example: 我今天特别开心 return : 我 今天 特别 开心
     * @param stocks stocks info
     * @return chinese word
     * @throws IOException 
     * @see ChineseSegmenterImpl
     */
    List<String> getWordsFromInput(StockInfo[] stocks) throws IOException;
    List<String> getWordsFromString(String str) throws IOException;

}
