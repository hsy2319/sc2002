package Controller;

import java.io.*;

/**
 * Writes serialized object to file and read
 * serialized object from file.
 *
 * @version 1.0
 */
public class DataIO {
    /**
     * Reads serialized object from file.
     * @param filename name of the file
     * @return object read from file. null if file / class is not found.
     * @throws IOException when file is not found
     * @throws ClassNotFoundException when class is not found
     */
    protected static Object readSerializedObject(String filename) throws IOException, ClassNotFoundException {
        Object data;
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        try {
            fileInputStream = new FileInputStream(filename);
            objectInputStream = new ObjectInputStream(fileInputStream);
            data = objectInputStream.readObject();
            objectInputStream.close();
        } catch (EOFException e) {
            return null;
        }

        return data;
    }

    /**
     * Writes serialized object to file.
     * @param filename name of the file
     * @param data the data to be written to the file
     * @throws IOException when file is not found
     */
    protected static void writeSerializedObject(String filename, Object data) throws IOException {
        FileOutputStream fileOutputStream;
        ObjectOutputStream objectOutputStream;

        fileOutputStream = new FileOutputStream(filename);
        objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(data);
        objectOutputStream.close();
    }
}
