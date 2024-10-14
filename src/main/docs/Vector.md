# Vector Class Documentation

## Overview
The `Vector` class represents a mathematical vector and provides a set of operations that can be performed on vectors, such as addition, subtraction, dot product, and cosine similarity. It is a fundamental part of the **AdvancedNewsClassifier** project, where vector operations are used in tasks such as GloVe embedding manipulation and neural network computations.

### Key Features
- **Addition and Subtraction** of vectors
- **Dot Product** and **Cosine Similarity** calculations
- **Vector Resizing** for compatibility in operations
- **Equality Check** between two vectors

---

## Mathematical Equations and Explanations

### **Dot Product**

The dot product between two vectors $\mathbf{A}$ and $\mathbf{B}$ of the same size $n$ is given by:

$$
\mathbf{A} \cdot \mathbf{B} = \sum_{i=1}^{n} A_i \times B_i
$$

Where:
- $A_i$ and $B_i$ are the components of vectors $\mathbf{A}$ and $\mathbf{B}$ respectively.

In the `dotProduct` method, this calculation is done by iterating over the elements of both vectors, multiplying corresponding elements, and summing the results.

---

### **Cosine Similarity**

Cosine similarity is a measure of similarity between two vectors based on the cosine of the angle between them. It is defined as:

$$
\text{cosine\ similarity}(A, B) = \frac{A \cdot B}{\|A\| \times \|B\|}
$$

Where:
- $A \cdot B$ is the dot product of vectors $A$ and $B$,
- $\|A\|$ and $\|B\|$ are the magnitudes (or Euclidean norms) of $A$ and $B$, calculated as:

$$
\|A\| = \sqrt{\sum_{i=1}^{n} A_i^2}
$$

Cosine similarity returns a value between -1 and 1, where:
- 1 means the vectors are identical in direction,
- 0 means the vectors are orthogonal (no similarity),
- -1 means the vectors are diametrically opposed.

---

### **Vector Addition**

The addition of two vectors $\mathbf{A}$ and $\mathbf{B}$, both of size $n$, is computed element-wise:

$$
\mathbf{C}_i = \mathbf{A}_i + \mathbf{B}_i
$$

Where:
- $\mathbf{A}_i$ and $\mathbf{B}_i$ are the $i$-th elements of vectors $\mathbf{A}$ and $\mathbf{B}$,
- $\mathbf{C}_i$ is the result of the addition.

This operation is implemented in the `add` method, where the two vectors are resized (if necessary) and their corresponding elements are added.

---

### **Vector Subtraction**

Subtraction of two vectors $\mathbf{A}$ and $\mathbf{B}$ is performed similarly to addition, but element-wise subtraction is applied:

$$
\mathbf{C}_i = \mathbf{A}_i - \mathbf{B}_i
$$

This results in a new vector $\mathbf{C}$ where each element is the difference between the corresponding elements of $\mathbf{A}$ and $\mathbf{B}$.

---

### **Vector Norm (Magnitude)**

The magnitude of a vector $\mathbf{A}$, also known as the Euclidean norm, is calculated as:

$$
\|A\| = \sqrt{\sum_{i=1}^{n} A_i^2}
$$

This value represents the length of the vector and is used in calculating the cosine similarity.

---

## Conclusion

The mathematical operations in the `Vector` class are foundational for working with vectorized representations, such as word embeddings in natural language processing. These vector operations facilitate tasks like measuring similarity (cosine similarity), combining information (addition), and transforming data into forms usable by neural networks in the **AdvancedNewsClassifier**.
