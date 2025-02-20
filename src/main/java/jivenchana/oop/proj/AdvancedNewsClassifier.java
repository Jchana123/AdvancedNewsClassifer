package jivenchana.oop.proj;

import org.apache.commons.lang3.time.StopWatch;
import org.deeplearning4j.datasets.iterator.utilty.ListDataSetIterator;
import org.deeplearning4j.nn.conf.MultiLayerConfiguration;
import org.deeplearning4j.nn.conf.NeuralNetConfiguration;
import org.deeplearning4j.nn.conf.WorkspaceMode;
import org.deeplearning4j.nn.conf.layers.DenseLayer;
import org.deeplearning4j.nn.conf.layers.OutputLayer;
import org.deeplearning4j.nn.multilayer.MultiLayerNetwork;
import org.deeplearning4j.nn.weights.WeightInit;
import org.nd4j.linalg.activations.Activation;
import org.nd4j.linalg.api.ndarray.INDArray;
import org.nd4j.linalg.dataset.DataSet;
import org.nd4j.linalg.dataset.api.iterator.DataSetIterator;
import org.nd4j.linalg.factory.Nd4j;
import org.nd4j.linalg.learning.config.Adam;
import org.nd4j.linalg.lossfunctions.LossFunctions;

import java.io.IOException;
import java.util.*;

public class AdvancedNewsClassifier 
{
    public Toolkit myTK = null;
    public static List<NewsArticles> listNews = null;
    public static List<Glove> listGlove = null;
    public List<ArticlesEmbedding> listEmbedding = null;
    public MultiLayerNetwork myNeuralNetwork = null;

    public final int BATCHSIZE = 10;

    public int embeddingSize = 0;
    private static StopWatch mySW = new StopWatch();

    /**
     * Constructor for AdvancedNewsClassifier
     * Initializes the toolkit, loads the news data, & prepares embedding
     *
     * @throws IOException If GloVe file cannot be loaded
     */
    public AdvancedNewsClassifier() throws IOException
    {
        myTK = new Toolkit();
        myTK.loadGlove();
        listNews = myTK.loadNews();
        listGlove = createGloveList();
        listEmbedding = loadData();
    }

    /**
     * Main method to execute the classification workflow
     *
     * @param args -> Command-line arguments
     * @throws Exception -> If any exception occurs during execution
     */
    public static void main(String[] args) throws Exception
    {
        mySW.start();
        AdvancedNewsClassifier myANC = new AdvancedNewsClassifier();

        myANC.embeddingSize = myANC.calculateEmbeddingSize(myANC.listEmbedding);
        myANC.populateEmbedding();
        myANC.myNeuralNetwork = myANC.buildNeuralNetwork(2);
        myANC.predictResult(myANC.listEmbedding);
        myANC.printResults();

        mySW.stop();
        System.out.println("Total elapsed time: " + mySW.getTime());
    }

    /**
     * Creates list of Glove objs from the vocab & vectors
     *
     * @return -> List of Glove objects
     */
    public List<Glove> createGloveList()
    {
        List<Glove> listResult = new ArrayList<>();

        for (int i = 0; i < Toolkit.listVocabulary.size(); i++)
        {
            String word = Toolkit.listVocabulary.get(i);

            if (notContains(Toolkit.STOPWORDS, word))
            {
                Glove glove = new Glove(word, new Vector(Toolkit.listVectors.get(i)));
                listResult.add(glove);
            }
        }
        return listResult;
    }

    /**
     * Checks if a word is not contained in stopwords list
     *
     * @param stopWords -> List of stopwords
     * @param word -> word to check
     * @return True if word is not in stopwords list, otherwise false
     */
    private static boolean notContains(String[] stopWords, String word)
    {
        for (String stopWord : stopWords)
        {
            if (word.equals(stopWord))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * Loads data into list of ArticlesEmbedding objs
     *
     * @return -> List of ArticlesEmbedding objs
     */
    public static List<ArticlesEmbedding> loadData()
    {
        List<ArticlesEmbedding> listEmbedding = new ArrayList<>();
        for (NewsArticles news : listNews)
        {
            ArticlesEmbedding myAE = new ArticlesEmbedding(news.getNewsTitle(), news.getNewsContent(), news.getNewsType(), news.getNewsLabel());
            listEmbedding.add(myAE);
        }
        return listEmbedding;
    }

    /**
     * Calculates median embedding size for list of ArticlesEmbedding objs
     *
     * @param _listEmbedding -> List of ArticlesEmbedding objs
     * @return -> Median embedding size
     */
    public int calculateEmbeddingSize(List<ArticlesEmbedding> _listEmbedding)
    {
        int intMedian = -1;

        List<Integer> listLength = new ArrayList<>();
        for (ArticlesEmbedding embedding : _listEmbedding)
        {
            int i = 0;
            for (String word : embedding.getNewsContent().split(" "))
            {
                if (Toolkit.listVocabulary.contains(word))
                {
                    i++;
                }
            }
            listLength.add(i);
        }
        intMedian = calculateMedian(listLength);

        return intMedian;
    }

    /**
     * Calculates the median value from list of ints
     *
     * @param _list -> list of type int
     * @return -> median value
     */
    private int calculateMedian(List<Integer> _list)
    {
        int size = _list.size();
        _list.sort(null);

        if (size % 2 == 0)
        {
            return (int) ((_list.get(size / 2 + 1) + _list.get(size / 2)) / 2.0);

        } else
        {

            return _list.get((size + 1) / 2);
        }
    }

    /**
     * Populates embedding size for all ArticlesEmbedding objs
     */
    public void populateEmbedding()
    {
        for (int i = 0; i < listEmbedding.size(); i++)
        {
            try
            {
                listEmbedding.get(i).setEmbeddingSize(embeddingSize);
                listEmbedding.get(i).getEmbedding();

            } catch (Exception ex)
            {
                if (ex.getClass().equals(InvalidSizeException.class))
                {
                    listEmbedding.get(i).setEmbeddingSize(embeddingSize);
                } else if (ex.getClass().equals(InvalidTextException.class))
                {
                    listEmbedding.get(i).getNewsContent();
                }
                i--;
            }
        }
    }

    /**
     * Populates training & testing records into DataSet objs for neural network training
     *
     * @param _numberOfClasses -> Number of output classes for the neural network
     * @return DataSetIterator for training neural network
     * @throws Exception If there is an issue in creating the dataset
     */
    public DataSetIterator populateRecordReaders(int _numberOfClasses) throws Exception
    {
        ListDataSetIterator myDataIterator = null;
        List<DataSet> listDS = new ArrayList<>();
        INDArray inputNDArray = null;
        INDArray outputNDArray = null;

        for (ArticlesEmbedding embedding : listEmbedding)
        {
            if (embedding.getNewsType() == NewsArticles.DataType.Training)
            {
                inputNDArray = embedding.getEmbedding();
                outputNDArray = Nd4j.create(1, _numberOfClasses);
                outputNDArray.putScalar(Integer.parseInt(embedding.getNewsLabel()) - 1, 1);

                DataSet myDataSet = new DataSet(inputNDArray, outputNDArray);
                listDS.add(myDataSet);
            }
        }

        return new ListDataSetIterator(listDS, BATCHSIZE);
    }

    /**
     * Builds and trains a neural network for classification
     *
     * @param _numOfClasses -> Number of output classes for neural network
     * @return Trained MultiLayerNetwork
     * @throws Exception If there is an issue in configuring / training the network
     */
    public MultiLayerNetwork buildNeuralNetwork(int _numOfClasses) throws Exception 
    {
        DataSetIterator trainIter = populateRecordReaders(_numOfClasses);
        MultiLayerConfiguration conf = new NeuralNetConfiguration.Builder()
                .seed(42)
                .trainingWorkspaceMode(WorkspaceMode.ENABLED)
                .activation(Activation.RELU)
                .weightInit(WeightInit.XAVIER)
                .updater(Adam.builder().learningRate(0.02).beta1(0.9).beta2(0.999).build())
                .l2(1e-4)
                .list()
                .layer(new DenseLayer.Builder().nIn(embeddingSize).nOut(15)
                        .build())
                .layer(new OutputLayer.Builder(LossFunctions.LossFunction.HINGE)
                        .activation(Activation.SOFTMAX)
                        .nIn(15).nOut(_numOfClasses).build())
                .build();

        MultiLayerNetwork model = new MultiLayerNetwork(conf);
        model.init();

        for (int n = 0; n < 100; n++) 
        {
            model.fit(trainIter);
            trainIter.reset();
        }
        return model;
    }

    /**
     * Predicts result for test articles
     *
     * @param _listEmbedding -> List of ArticlesEmbedding to predict results for
     * @return List of predicted class labels
     * @throws Exception If there is an issue in generating predictions
     */
    public List<Integer> predictResult(List<ArticlesEmbedding> _listEmbedding) throws Exception
    {
        List<Integer> listResult = new ArrayList<>();

        for (ArticlesEmbedding embedding : _listEmbedding)
        {
            if (embedding.getNewsType() == NewsArticles.DataType.Testing)
            {
                int[] result = myNeuralNetwork.predict(embedding.getEmbedding());
                listResult.add(result[0]);
                
                embedding.setNewsLabel(String.valueOf(result[0] + 1));
            }
        }
        return listResult;
    }

    /**
     * Prints classification results, grouping articles by their predicted class labels
     */
    public void printResults()
    {
        List<Integer> listClassNumber = new ArrayList<>();
        for (ArticlesEmbedding embedding : listEmbedding)
        {
            if (!listClassNumber.contains(Integer.parseInt(embedding.getNewsLabel())))
            {
                listClassNumber.add(Integer.parseInt(embedding.getNewsLabel()));
            }
        }
        listClassNumber.sort(null);

        for (int i : listClassNumber) 
        {
            System.out.println("Group " + i);
            
            for (ArticlesEmbedding embedding : listEmbedding.stream().filter(embedding -> embedding.getNewsType() == NewsArticles
                    .DataType.Testing && embedding.getNewsLabel()
                    .equals(String.valueOf(i))).toList())
            {
                System.out.println(embedding.getNewsTitle());
            }
        }
    }
}

