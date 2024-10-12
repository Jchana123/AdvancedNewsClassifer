package jivenchana.oop.proj;

public class HtmlParser 
{
    /***
     * Extract the title of the news from the _htmlCode.
     * @param _htmlCode Contains the full HTML string from a specific news. E.g. 01.htm.
     * @return Return the title if it's been found. Otherwise, return "Title not found!".
     */
    public static String getNewsTitle(String _htmlCode) 
    {
        String titleTagOpen = "<title>";
        String titleTagClose = "</title>";

        int titleStart = _htmlCode.indexOf(titleTagOpen) + titleTagOpen.length();
        int titleEnd = _htmlCode.indexOf(titleTagClose);

        if (titleStart != -1 && titleEnd != -1 && titleEnd > titleStart) 
        {
            String strFullTitle = _htmlCode.substring(titleStart, titleEnd);
            return strFullTitle.substring(0, strFullTitle.indexOf(" |"));
        }
        return "Title not found!";
    }

    /***
     * Extract the content of the news from the _htmlCode.
     * @param _htmlCode Contains the full HTML string from a specific news. E.g. 01.htm.
     * @return Return the content if it's been found. Otherwise, return "Content not found!".
     */
    public static String getNewsContent(String _htmlCode) 
    {
        String contentTagOpen = "\"articleBody\": \"";
        String contentTagClose = " \",\"mainEntityOfPage\":";

        int contentStart = _htmlCode.indexOf(contentTagOpen) + contentTagOpen.length();
        int contentEnd = _htmlCode.indexOf(contentTagClose);

        if (contentStart != -1 && contentEnd != -1 && contentEnd > contentStart) 
        {
            return _htmlCode.substring(contentStart, contentEnd).toLowerCase();
        }
        return "Content not found!";
    }

    /**
     * Extracts the data type of the news (Training or Testing) from the provided HTML code.
     * 
     * @param _htmlCode The full HTML string from a specific news.
     * @return The extracted data type (Training or Testing).
     */
    public static NewsArticles.DataType getDataType(String _htmlCode) 
    {
        String dataTypeOpenTag = "<datatype>";
        String dataTypeCloseTag = "</datatype>";

        int indexOpenTag = _htmlCode.indexOf(dataTypeOpenTag);
        int openTagSize = dataTypeOpenTag.length();
        int indexCloseTag = _htmlCode.indexOf(dataTypeCloseTag);

        int dataTypeStart = indexOpenTag + openTagSize;

        if (dataTypeStart != -1 && indexCloseTag != -1 && indexCloseTag > dataTypeStart)
        {
            String pulledOutDT = _htmlCode.substring(dataTypeStart, indexCloseTag);

            if ("Training".equals(pulledOutDT))
            {
                return NewsArticles.DataType.Training;
            } 
            return NewsArticles.DataType.Testing;
        }
        return NewsArticles.DataType.Testing;
    }

    /**
     * Extracts the label of the news from the provided HTML code.
     * 
     * @param _htmlCode The full HTML string from a specific news.
     * @return The extracted label, or -1 if not found.
     */
    public static String getLabel (String _htmlCode) 
    {
        String labelOpenTag = "<label>";
        String labelCloseTag = "</label>";

        int indexLabelOpen = _htmlCode.indexOf(labelOpenTag);
        int labelOpenSize = labelOpenTag.length();

        int labelStart = indexLabelOpen + labelOpenSize;

        int indexLabelClose = _htmlCode.indexOf(labelCloseTag);

        if (labelStart != -1 && indexLabelClose != -1 && indexLabelClose > labelStart)
        {
            return _htmlCode.substring(labelStart, indexLabelClose);
        }
        return Integer.toString(-1);
    }

}
