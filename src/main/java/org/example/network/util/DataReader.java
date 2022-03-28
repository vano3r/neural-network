package org.example.network.util;


import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class DataReader {
    private final List<DataForNeuralNetwork> dataList;
    private final String path;

    public DataReader(String path) {
        dataList = new LinkedList<>();
        this.path = path;
    }

    public List<DataForNeuralNetwork> read() {

        try (CSVReader reader = new CSVReader(
                new FileReader(path))) {

            String[] line;
            while ((line = reader.readNext()) != null) {
                double[] tempArray = new double[line.length - 1];
                for (int i = 1; i < line.length - 1; i++) {
                    tempArray[i] = Double.parseDouble(line[i]) / 255.0 * 0.99 + 0.01;
                }
                dataList.add(new DataForNeuralNetwork(Integer.parseInt(line[0]), tempArray));
            }

        } catch (IOException | CsvException e) {
            e.printStackTrace();
        }

        return dataList;
    }
}
