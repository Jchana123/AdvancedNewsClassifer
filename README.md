# Advanced News Classifier

Welcome to the **Advanced News Classifier** project! This project leverages machine learning and natural language processing to classify news articles using GloVe embeddings and a neural network.

## Project Overview

This classifier takes news articles, processes their text, and uses **GloVe word embeddings** to generate vectorized representations of each article. The project utilizes **Deeplearning4J** to build a neural network for classifying articles into different groups.

### Motivation

The motivation behind this project is to **improve upon the limitations of traditional TFIDF-based classification models**. While TFIDF captures word frequencies, it often misses out on **contextual relationships** between words, leading to less accurate classification in cases where context is crucial for understanding meaning. 

In this project, the use of **GloVe embeddings** allows for a **more robust representation of word semantics**, enabling better understanding of article content beyond mere word counts. By integrating this contextual understanding, I am looking to **hone my craft** and strengthen my ability to work with **supervised learning models**, which will be valuable for future machine learning and NLP projects. 

This project serves as a foundation for tackling more **complex supervised learning tasks** in various fields, ranging from news classification to sentiment analysis, recommendation systems, and beyond. It aims to show the importance of embedding models for capturing context and improving classification accuracy.


### Features:
- **Text Preprocessing**: Clean, tokenize, and lemmatize news articles using Stanford CoreNLP.
- **GloVe Embeddings**: Utilize pre-trained GloVe embeddings for vector representation of words.
- **Neural Network**: Classify articles using a deep learning model built with Deeplearning4J.
- **PCA Visualizations**: Visualize article embeddings in 2D space with PCA or t-SNE.
  
- **Please note: for more indepth detail and diagrams of the process see the README.md in ```src/main/java/jivenchana/oop/proj```**

## Contextual Differences and Comparison to TFIDF

### Group 1
- Articles in **Group 1** focus on **major personalities, policy decisions, and groundbreaking achievements**. They highlight leadership, innovation, and important societal impacts.
  
### Group 2
- **Group 2** centers around **public health** and **corporate developments** related to the pandemic, emphasizing scientific insights, policy changes, and health-related corporate affairs.

### GloVe Embeddings vs TFIDF
- **GloVe embeddings** capture **contextual relationships** between words, recognizing themes like leadership or public health across different vocabulary. Words that occur in similar contexts across a large corpus share similar vector representations, allowing the model to capture **latent meaning**.
  
- In contrast, **TFIDF** focuses on surface-level word frequencies, treating each word independently. For example, if "COVID" is mentioned across several articles, TFIDF might group them together based solely on word occurrence, ignoring their contextual differences. GloVe would differentiate them based on the semantic content, clustering **policy-focused** articles separately from **scientific ones**.
  
## Demo

Below I have included a demo example showcasing how the Advanced News Classifer clearly demonstrates contextual awareness when classifying news articles
```
/Library/Java/JavaVirtualMachines/jdk-21.jdk/Contents/Home/bin/java -javaagent:/Applications/IntelliJ\ IDEA.app/Contents/lib/idea_rt.jar=50477:/Applications/IntelliJ\ IDEA.app/Contents/bin -Dfile.encoding=UTF-8 -classpath /Users/jivenchana/Documents/AdvancedNewsClassifier/target/classes:/Users/jivenchana/.m2/repository/ org/nd4j/nd4j-native-platform/1.0.0-M2.1/nd4j-native-platform-1.0.0-M2.1.jar uob.oop.AdvancedNewsClassifier

Group 1
Boris Johnson asked if government ’believes in long COVID’, coronavirus inquiry hears
COVID vaccine scientists win Nobel Prize in medicine
Long COVID risks are ’distorted by flawed research’, study finds
Who is Sam Altman? The OpenAI boss and ChatGPT guru who became one of AI’s biggest players
ChatGPT maker OpenAI agrees deal for ousted Sam Altman to return as chief executive
Sam Altman: Ousted OpenAI boss ’committed to ensuring firm still thrives’ as majority of employees threaten to quit
Sam Altman: Sudden departure of ChatGPT guru raises major questions that should concern us all
ChatGPT creator Sam Altman lands Microsoft job after ousting by OpenAI board

Group 2
COVID inquiry: There could have been fewer coronavirus-related deaths with earlier lockdown, scientist says
Up to 200,000 people to be monitored for COVID this winter to track infection rates
Molnupiravir: COVID drug linked to virus mutations, scientists say
How the chaos at ChatGPT maker OpenAI has unfolded as ousted CEO Sam Altman returns - and why it matters

Process finished with exit code 0
```
## How it Works
1. Load GloVe embeddings.
2. Preprocess news articles (cleaning, lemmatization, tokenization).
3. Generate embeddings for each article using GloVe.
4. Train a neural network to classify articles into groups.
5. Visualize article embeddings with PCA.

## Installation

   ```bash
   git clone https://github.com/Jiven-Chana/AdvancedNewsClassifier.git
   cd AdvancedNewsClassifier
   mvn clean install
   java -jar target/AdvancedNewsClassifier.jar
   ```
## References

1.	J. Devlin, M.-W. Chang, K. Lee, and K. Toutanova, “Bert: Pre-training of deep bidirectional transformers for language understanding,” arXiv preprint arXiv:1810.04805, 2018.
2.	C. D. Manning, M. Surdeanu, J. Bauer, J. R. Finkel, S. Bethard, and D. McClosky, “The Stanford CoreNLP Natural Language Processing Toolkit,” in Proceedings of 52nd Annual Meeting of the Association for Computational Linguistics: System Demonstrations, 2014, pp. 55–60.
3.	T. Mikolov, K. Chen, G. Corrado, and J. Dean, “Efficient estimation of word representations in vector space,” 1st International Conference on Learning Representations, ICLR 2013 - Workshop Track Proceedings, 2013. [Online]. Available: http://ronan.collobert.com/senna/
4.	T. Mikolov, Q. V. Le, and I. Sutskever, “Exploiting similarities among languages for machine translation,” ArXiv, 2013. [Online]. Available: http://arxiv.org/abs/1309.4168
5.	T. Mikolov, W. T. Yih, and G. Zweig, “Linguistic regularities in continuous space-word representations,” NAACL HLT 2013 - Conference of the North American Chapter of the Association for Computational Linguistics: Human Language Technologies, 2013, pp. 746–751.
6.	J. Pennington, R. Socher, and C. D. Manning, “GloVe: Global Vectors for Word Representation,” in Empirical Methods in Natural Language Processing (EMNLP), 2014, pp. 1532–1543. [Online]. Available: http://www.aclweb.org/anthology/D14-1162
7.	A. Radford, K. Narasimhan, T. Salimans, I. Sutskever et al., “Improving language understanding by generative pre-training,” 2018.
8.	E. D. D. Team, “DL4J: Deep Learning for Java,” 2016. [Online]. Available: https://github.com/eclipse/deeplearning4j
9.	——, “ND4J: Fast, Scientific and Numerical Computing for the JVM,” 2016. [Online]. Available: https://github.com/eclipse/deeplearning4j
