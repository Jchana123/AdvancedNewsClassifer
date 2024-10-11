package jivenchana.oop.proj;

public class NewsArticles {
    public enum DataType {
        Training, Testing
    }

    private String newsTitle = "", newsContent = "";
    private DataType newsType = DataType.Testing;
    private String newsLabel = "-1";

    public NewsArticles(String _title, String _content, DataType _type, String _label) {

        newsContent = _content;
        newsTitle = _title;
        newsType = _type;
        newsLabel = _label;

    }

    public String getNewsLabel() {

         return this.newsLabel;

    }

    public DataType getNewsType() {

        return this.newsType;

    }

    public String getNewsTitle() {

        return this.newsTitle;

    }

    public String getNewsContent() {

        return this.newsContent;

    }

    public void setNewsLabel(String _lable) {

        this.newsLabel = _lable;

    }

    public void setNewsType (DataType _type){

        this.newsType = _type;

    }
}
