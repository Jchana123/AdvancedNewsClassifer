# Project Architecture of Advanced News Classifier

This document provides an in-depth explanation of the **Advanced News Classifier** project structure. The program is built using object-oriented programming principles, and each class has its own specific responsibilities.

## Overview

The **Advanced News Classifier** classifies news articles by processing their text through GloVe embeddings and machine learning models (neural networks). The project relies on various utility classes to clean and preprocess text, load embeddings, and manage the classification process.

### Key Classes and Their Roles:

1. **Vector**: 
   - This class handles mathematical operations on vectors (such as addition, subtraction, dot products, and cosine similarity). 
   - These operations are vital for comparing word vectors or embeddings when calculating similarities between articles.

2. **Toolkit**: 
   - A utility class responsible for loading GloVe embeddings and news articles. 
   - It manages two key lists: one for vocabulary (`listVocabulary`) and another for their corresponding embeddings (`listVectors`). 
   - It acts as a support class, providing necessary data to the other components of the system.

3. **NewsArticles**:
   - Represents the structure of a news article. 
   - Contains attributes such as `newsTitle`, `newsContent`, `newsLabel`, and `newsType` to differentiate between training and testing data.
   - This class is primarily responsible for storing article data and providing methods to retrieve or modify that data.

4. **ArticlesEmbedding**:
   - Extends `NewsArticles` and is responsible for processing the content of a news article and generating its embedding using GloVe vectors.
   - The class also manages the embedding size and stores the final vectorized representation of the article in an `INDArray`.

5. **HtmlParser**:
   - Responsible for extracting data from HTML files, such as article titles, content, data types (training/testing), and labels.
   - It provides methods to parse the required information from the news articles' HTML structure.

6. **AdvancedNewsClassifier**:
   - The core class of the program that orchestrates the entire news classification pipeline.
   - It loads data, generates embeddings, and builds a neural network using **Deeplearning4J** to classify the articles into groups.
   - This class relies on the `Toolkit`, `ArticlesEmbedding`, and `NewsArticles` classes to process and classify the news articles effectively.

### Class Interactions

The following diagram outlines the relationships and dependencies between the key classes within the project. Each class interacts with one another to perform tasks, such as vector operations, data parsing, and embedding generation.

![mermaid-diagram-2024-10-13-011758](https://github.com/user-attachments/assets/18ccd899-c50b-46be-824c-9b47f8544f29)

## Explanation of Class Interactions

- **Vector → ArticlesEmbedding**: The `Vector` class is used to perform vector operations on embeddings within the `ArticlesEmbedding` class, where article embeddings are calculated.
- **Toolkit → AdvancedNewsClassifier**: The `Toolkit` class serves as a utility to load GloVe embeddings and news articles, providing essential data for the `AdvancedNewsClassifier`.
- **NewsArticles → ArticlesEmbedding**: The `ArticlesEmbedding` class inherits from `NewsArticles` and adds embedding-related functionality on top of the basic news article data.
- **HtmlParser → Toolkit**: The `HtmlParser` class works with the `Toolkit` to parse news article content from HTML files.
- **AdvancedNewsClassifier → ArticlesEmbedding**: The `AdvancedNewsClassifier` is responsible for managing `ArticlesEmbedding` instances, using them to generate embeddings and train the neural network.
- **DataType**: The `NewsArticles` class contains an enum `DataType` to differentiate between Training and Testing data types.

## Detailed Breakdown

1. **Loading GloVe Embeddings**:
   - The `Toolkit` class loads the pre-trained GloVe embeddings (from a CSV file) and stores them in `listVocabulary` and `listVectors`. These embeddings are later used to generate vector representations of the news articles.
   
2. **Processing Articles**:
   - `HtmlParser` extracts the title, content, and labels from the news articles.
   - The `ArticlesEmbedding` class processes the cleaned article text, tokenizes and lemmatizes it, and generates the embeddings using GloVe vectors.
   
3. **Building the Neural Network**:
   - The `AdvancedNewsClassifier` initializes and trains a neural network using **Deeplearning4J**. This network is trained to classify news articles into predefined groups.
   - After training, the network can predict the category of new articles based on their embeddings.

This explanation should provide a clear understanding of the structure and flow of the **Advanced News Classifier** project. Each class is responsible for a specific part of the process, from loading data and processing it, to generating embeddings and classifying articles using a neural network.
