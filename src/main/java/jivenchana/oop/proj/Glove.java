package jivenchana.oop.proj;

public class Glove 
{
    private String strVocabulary;
    private Vector vecVector;

    public Glove(String _vocabulary, Vector _vector) 
    {
        strVocabulary = _vocabulary;
        vecVector = _vector;
    }

    public String getVocabulary() 
    {
        return this.strVocabulary;
    }

    public Vector getVector() 
    {
        return this.vecVector;
    }

    public void setVocabulary(String _vocabulary) 
    {
        this.strVocabulary = _vocabulary;
    }

    public void setVector(Vector _vector) 
    {
        this.vecVector = _vector;
    }
}
