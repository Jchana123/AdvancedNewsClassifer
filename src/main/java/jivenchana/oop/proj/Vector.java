package jivenchana.oop.proj;

/**
 * Represents a mathematical vector and provides methods to manipulate and perform 
 * vector operations such as addition, subtraction, dot product, and cosine similarity.
 * 
 * @author Jiven Chana 
 * @version 1.0
 * @since 2024
 */
public class Vector 
{
    private double[] doubElements;

    /**
     * Constructor to initialize the vector with given elements.
     * 
     * @param _elements Array of elements for the vector
     */
    public Vector(double[] _elements) 
    {
        doubElements = _elements;
    }

    /**
     * Retrieves the element at the specified index in the vector.
     * 
     * @param _index The index of the element
     * @return The value at the specified index, or -1 if the index is out of bounds
     */
    public double getElementatIndex(int _index) 
    {
        if (_index >= 0 && _index < doubElements.length)
            return doubElements[_index];
        else
            return -1;
    }

    /**
     * Sets the element at the specified index to the provided value.
     * If the index is out of bounds, the last element is updated.
     * 
     * @param _value The value to set at the specified index
     * @param _index The index to set the value at
     */
    public void setElementatIndex(double _value, int _index) 
    {
        if (_index >= 0 && _index < doubElements.length)
            doubElements[_index] = _value;
        else
            doubElements[doubElements.length - 1] = _value;
    }

    /**
     * @return An array of all elements in the vector
     */

    public double[] getAllElements() 
    {
        return doubElements;
    }

    /**
     * @return The number of elements in the vector
     */
    public int getVectorSize() 
    {
        return doubElements.length;
    }

    /**
     * Resizes the vector to the specified size. If the new size is larger, the additional 
     * elements are initialized to -1.
     * 
     * @param _size The new size of the vector
     * @return A new resized vector
     */
    public Vector reSize(int _size) 
    {
        if (this.getVectorSize() == _size || _size <= 0)
            return this;

        double[] doubElementNew = new double[_size];

        for (int i = 0; i < (Math.min(this.getVectorSize(), _size)); i++) 
        {
            doubElementNew[i] = this.getElementatIndex(i);
        }
        for (int i = this.getVectorSize(); i < _size; i++) 
        {
            doubElementNew[i] = -1.0;
        }
        return new Vector(doubElementNew);
    }

    /**
     * @param _v The vector to be added
     * @return A new vector which is the sum of this vector and the provided vector
     */
    public Vector add(Vector _v) 
    {
        Vector myVector = this;
        
        if (this.getVectorSize() > _v.getVectorSize())
            _v = _v.reSize(this.getVectorSize());
        else if (this.getVectorSize() < _v.getVectorSize())
            myVector = this.reSize(_v.getVectorSize());

        double[] doubElementNew = new double[_v.getVectorSize()];

        for (int i = 0; i < _v.getVectorSize(); i++) 
        {
            doubElementNew[i] = myVector.getElementatIndex(i) + _v.getElementatIndex(i);
        }
        return new Vector(doubElementNew);
    }

    /**
     * @param _v The vector to be subtracted
     * @return A new vector which is the result of subtracting the provided vector from this vector
     */
    public Vector subtraction(Vector _v) 
    {
        Vector myVector = this;
        
        if (this.getVectorSize() > _v.getVectorSize())
            _v = _v.reSize(this.getVectorSize());
        else if (this.getVectorSize() < _v.getVectorSize())
            myVector = this.reSize(_v.getVectorSize());

        double[] doubElementNew = new double[_v.getVectorSize()];
        for (int i = 0; i < _v.getVectorSize(); i++) 
        {
            doubElementNew[i] = myVector.getElementatIndex(i) - _v.getElementatIndex(i);
        }
        return new Vector(doubElementNew);
    }

    /**
     * @param _v The vector with which to compute the dot product
     * @return The dot product of the two vectors
     */
    public double dotProduct(Vector _v) 
    {
        Vector myVector = this;
        if (this.getVectorSize() > _v.getVectorSize())
            _v = _v.reSize(this.getVectorSize());
        else if (this.getVectorSize() < _v.getVectorSize())
            myVector = this.reSize(_v.getVectorSize());

        double dotProduct = 0;
        
        for (int i = 0; i < _v.getVectorSize(); i++) 
        {
            dotProduct += myVector.getElementatIndex(i) * _v.getElementatIndex(i);
        }
        return dotProduct;
    }

    /**
     * @param _v The vector to compare with
     * @return The cosine similarity value between the two vectors
     */
    public double cosineSimilarity(Vector _v) 
    {
        Vector myVector = this;
        if (this.getVectorSize() > _v.getVectorSize())
            _v = _v.reSize(this.getVectorSize());
        else if (this.getVectorSize() < _v.getVectorSize())
            myVector = this.reSize(_v.getVectorSize());

        double doubDProduct = dotProduct(_v);
        double doubNorm1 = 0;
        double doubNorm2 = 0;
        for (int i = 0; i < _v.getVectorSize(); i++) 
        {
            doubNorm1 += Math.pow(myVector.getElementatIndex(i), 2);
            doubNorm2 += Math.pow(_v.getElementatIndex(i), 2);
        }
        return doubDProduct / (Math.sqrt(doubNorm1) * Math.sqrt(doubNorm2));
    }

    /**
     * @param _obj The object to compare this vector with
     * @return {@code true} if the vectors are equal, {@code false} otherwise
     */
    @Override
    public boolean equals(Object _obj) 
    {
        Vector v = (Vector) _obj;
        boolean boolEquals = true;

        if (this.getVectorSize()!= v.getVectorSize())
            return false;

        for (int i = 0; i < this.getVectorSize(); i++) 
        {
            if (this.getElementatIndex(i) != v.getElementatIndex(i)) 
            {
                boolEquals = false;
                break;
            }
        }
        return boolEquals;
    }

    /**
     * Returns a string representation of the vector.
     * 
     * @return A comma-separated string of vector elements
     */
    @Override
    public String toString() 
    {
        StringBuilder mySB = new StringBuilder();
        for (int i = 0; i < this.getVectorSize(); i++) 
        {
            mySB.append(String.format("%.5f", doubElements[i])).append(",");
        }
        mySB.delete(mySB.length() - 1, mySB.length());
        return mySB.toString();
    }
}

