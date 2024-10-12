package jivenchana.oop.proj;

/**
 * This class represents a news article with its title, content, type, and label.
 */
public class NewsArticles 
{
        
    /**
     * Enum representing the data type of the news article, either Training or Testing.
     */
    public enum DataType 
    {
        Training, Testing
    }

    private String newsTitle = "", newsContent = "";
    private DataType newsType = DataType.Testing;
    private String newsLabel = "-1";

    /**
     * Constructor to initialize a NewsArticles object.
     * 
     * @param _title The title of the news article.
     * @param _content The content of the news article.
     * @param _type The type of the news article (Training or Testing).
     * @param _label The label associated with the news article.
     */
    public NewsArticles(String _title, String _content, DataType _type, String _label) 
    {
        newsContent = _content;
        newsTitle = _title;
        newsType = _type;
        newsLabel = _label;
    }

    /**
     * @return -> label of the news article
     */
    public String getNewsLabel() 
    {
         return this.newsLabel;
    }

    /**
     * @return -> type of the news article (Training or Testing)
     */
    public DataType getNewsType() 
    {
        return this.newsType;
    }

    public String getNewsTitle() 
    {
        return this.newsTitle;
    }

    public String getNewsContent() 
    {
        return this.newsContent;
    }

    public void setNewsLabel(String _lable) 
    {
        this.newsLabel = _lable;
    }

    public void setNewsType (DataType _type)
    {
        this.newsType = _type;
    }
}
