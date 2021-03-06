package nl.esciencecenter.aether.io;

import java.io.IOException;

/**
 * Abstract class that implements object reads for any kind of object.
 * The idea is that there is a separate reader for each kind of object,
 * so that runtime tests can be avoided.
 */
abstract class ObjectReader {
    abstract Object readObject(IbisSerializationInputStream in,
            AlternativeTypeInfo t, int typeHandle)
            throws IOException, ClassNotFoundException;
}
