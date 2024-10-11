package jivenchana.oop.proj;

import edu.stanford.nlp.ling.*;
import edu.stanford.nlp.pipeline.*;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.factory.Nd4j;

import java.util.Properties;

public class ArticlesEmbedding extends NewsArticles 
{
    private int intSize = -1;
    private String processedText = "";

    private INDArray newsEmbedding = Nd4j.create(0);

    public boolean isEmbedded = false;

    public ArticlesEmbedding(String _title, String _content, NewsArticles.DataType _type, String _label) 
    {
        super(_title, _content, _type, _label);
    }

    public void setEmbeddingSize(int _size) 
    {
        this.intSize = _size;
    }

    public int getEmbeddingSize()
    {
        return intSize;
    }

    @Override
    public String getNewsContent()
    {
        if (processedText.isEmpty())
        {
            processedText = textCleaning(super.getNewsContent());

            StringBuilder sbContent = new StringBuilder();

            Properties props = new Properties();
            props.setProperty("annotators", "tokenize,pos,lemma");
            StanfordCoreNLP pipeLine = new StanfordCoreNLP(props);
            CoreDocument myDocument = pipeLine.processToCoreDocument(processedText);

            for (CoreLabel tok : myDocument.tokens())
            {
                if (notContains(Toolkit.STOPWORDS, tok.lemma().toLowerCase()))
                {
                    sbContent.append(tok.lemma().toLowerCase()).append(" ");
                }
            }
            processedText = sbContent.toString();
        }
        return processedText.trim();
    }
    private static boolean notContains(String[] _arrayTarget, String _searchValue)
    {
        for (String element : _arrayTarget)
        {
            if (_searchValue.equals(element))
            {
                return false;
            }
        }
        return true;
    }

    public INDArray getEmbedding() throws Exception
    {
        if (newsEmbedding.isEmpty())
        {
            if (intSize == -1)
            {
                throw new InvalidSizeException("Invalid size");
            }
            if (processedText.isEmpty())
            {
                throw new InvalidTextException("Invalid text");
            }

            newsEmbedding = Nd4j.create(intSize, AdvancedNewsClassifier.listGlove.get(0).getVector().getVectorSize());

            int i = 0;
            for (String tok : processedText.split(" "))
            {
                Glove myGlove = AdvancedNewsClassifier
                        .listGlove.stream()
                        .filter(glove -> glove.getVocabulary().equals(tok))
                        .findFirst().orElse(null);

                if (i < intSize && myGlove != null)
                {
                    newsEmbedding.putRow(i, Nd4j.create(myGlove.getVector().getAllElements()));
                    i++;
                } else if (i >= intSize)
                {
                    break;
                }
            }
        }
        return Nd4j.vstack(newsEmbedding.mean(1));
    }

    /***
     * Clean the given (_content) text by removing all the characters that are not 'a'-'z', '0'-'9' and white space.
     * @param _content Text that need to be cleaned.
     * @return The cleaned text.
     */
    
    private static String textCleaning(String _content)
    {
        StringBuilder sbContent = new StringBuilder();

        for (char c : _content.toLowerCase().toCharArray())
        {
            if ((c >= 'a' && c <= 'z') || (c >= '0' && c <= '9') || Character.isWhitespace(c))
            {
                sbContent.append(c);
            }
        }
        return sbContent.toString().trim();
    }
}
