package DAO;

import model.Flight;

import java.io.*;
import java.util.List;

public class FlightFileDAO implements FlightDAO {
    @Override
    public List<Flight> getFlights() throws IOException, ClassNotFoundException {
        File file = new File("flight");
        InputStream inputStream = new FileInputStream(file);
        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
        return (List<Flight>) objectInputStream.readObject();
    }

    @Override
    public void setFlights(List<Flight> flights) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
        objectOutputStream.writeObject(flights);
        objectOutputStream.close();
        byte[] bytesFlights = byteArrayOutputStream.toByteArray();
        OutputStream outputStream = new FileOutputStream("flights");
        outputStream.write(bytesFlights);
        outputStream.close();
    }
}
