package cn.peopleinvest.model;


import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class IndexList {

    private int listSize;
    private String keyWord;
    private List<Investindex> resultList;

    public IndexList(List<Investindex> list,String keyWord){
        this.resultList = list;
        this.listSize = list.size();
        this.keyWord = keyWord;
        Collections.sort(resultList, (a,b)->{return a.getLocalRank().compareTo(b.getLocalRank());});
        //Comparator.comparing(Investindex::getLocalRank);
        //resultList.forEach(System.out::println);
        //this.rankTrend = resultList.get(1).getLocalRank()-resultList.get(0).getLocalRank();
    }

    public int getListSize() {
        return listSize;
    }

    public void setListSize(int listSize) {
        this.listSize = listSize;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<Investindex> getResultList() {
        return resultList;
    }

    public void setResultList(List<Investindex> resultList) {
        this.resultList = resultList;
    }
    
}
