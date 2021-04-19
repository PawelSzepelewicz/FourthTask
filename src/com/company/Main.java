package com.company;

import java.io.IOException;
import java.util.List;

public class Main {

    public static void main(String[] args) throws IOException {
        FakerCsv strings = new FakerCsv();
        strings.convertToCsv(strings.testDataCsvStringsList(Integer.parseInt(args[0]), args[1]));
    }
}
