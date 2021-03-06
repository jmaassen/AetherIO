/* $Id: SerializationFactory.java 15123 2013-07-02 13:40:20Z ceriel $ */

package nl.esciencecenter.aether.io;

import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

/**
 * A factory for creating serialization streams.
 */
public class SerializationFactory {

    /**
     * Returns the implementation name for the specified nickname.
     * For now, this is hardcoded, but it could be driven by for instance
     * a configuration or properties file.
     * @param name the nickname of the serialization type.
     * @return the implementation name.
     */
    private static String implName(String name, Properties props) {
	String dflt = "ibis";
	if (props != null) {
	    String v = props.getProperty(IOProperties.s_serialization_default);
	    if ("sun".equals(v)) {
		dflt = "sun";
	    }
	}
	if (name == null || name.equals("object")) {
	    name = dflt;
	}
        if (name.equals("ibis")) {
            return "ibis.io.IbisSerialization";
        }
        if (name.equals("sun")) {
            return "ibis.io.SunSerialization";
        }
        if (name.equals("data")) {
            return "ibis.io.DataSerialization";
        }
        if (name.equals("byte")) {
            return "ibis.io.ByteSerialization";
        }
        return name;
    }

    /**
     * Creates a {@link SerializationInput} as specified by the name.
     * @param name the nickname for this serialization type.
     * @param in   the underlying input stream.
     * @return the serialization input stream.
     */
    public static SerializationInput createSerializationInput(String name,
            DataInputStream in, Properties props) throws IOException {
        String impl = implName(name, props) + "InputStream";
        try {
            Class<?> cl = Class.forName(impl);
            Constructor<?> cons =
                    cl.getConstructor(new Class[] {DataInputStream.class});
            return (SerializationInput) cons.newInstance(new Object[] {in});
        } catch(ClassNotFoundException e) {
            throw new IOException("No such class: " + impl, e);
        } catch(NoSuchMethodException e) {
            throw new IOException(
                    "No suitable constructor in class: " + impl, e);
        } catch(IllegalArgumentException e) {
            throw new IOException(
                    "No suitable constructor in class: " + impl, e);
        } catch(InstantiationException e) {
            throw new IOException("class " + impl + " is abstract", e);
        } catch(InvocationTargetException e) {
            throw new IOException(
                    "constructor of " + impl + " threw an exception", e.getCause());
        } catch(IllegalAccessException e) {
            throw new IOException(
                    "access to constructor of " + impl + " is denied", e);
        } catch(Throwable e) {
            throw new IOException("got unexpected error", e);
        }
    }

    /**
     * Creates a {@link SerializationOutput} as specified by the name.
     * @param name the nickname for this serialization type.
     * @param out   the underlying output stream.
     * @return the serialization output stream.
     */
    public static SerializationOutput createSerializationOutput(String name,
            DataOutputStream out, Properties props) throws IOException {
        String impl = implName(name, props) + "OutputStream";
        try {
            Class<?> cl = Class.forName(impl);
            Constructor<?> cons =
                    cl.getConstructor(new Class[] {DataOutputStream.class});
            return (SerializationOutput) cons.newInstance(new Object[] {out});
        } catch(ClassNotFoundException e) {
            throw new IOException("No such class: " + impl, e);
        } catch(NoSuchMethodException e) {
            throw new IOException(
                    "No suitable constructor in class: " + impl, e);
        } catch(IllegalArgumentException e) {
            throw new IOException(
                    "No suitable constructor in class: " + impl, e);
        } catch(InstantiationException e) {
            throw new IOException("class " + impl + " is abstract", e);
        } catch(InvocationTargetException e) {
            throw new IOException(
                    "constructor of " + impl + " threw an exception", e.getCause());
        } catch(IllegalAccessException e) {
            throw new IOException(
                    "access to constructor of " + impl + " is denied", e);
        } catch(Throwable e) {
            throw new IOException("got unexpected error", e);
        }
    }
}
