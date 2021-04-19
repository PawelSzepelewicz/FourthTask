package com.company;
import au.com.bytecode.opencsv.CSVWriter;
import com.github.javafaker.Address;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import com.github.javafaker.Faker;
import java.util.Random;

import static java.lang.String.format;

public class FakerCsv {

    public List<String[]> testDataCsvStringsList(int count, String locale) {
        List<String[]> strings = new ArrayList<>();
        Faker faker = new Faker(new Locale(locale));
        Random random = new Random();
        for (int i = 0; i < count; i++) {
            String fullName = faker.name().fullName();
            Address address = faker.address();
            String countryCode = locale.substring(3);
            String zipCode = address.zipCode();
            String fullAddress = countryCode.equals("US") ? faker.address().fullAddress() : faker.address().fullAddress().substring(zipCode.length() + 1);
            if (random.nextInt(1000) % 3 == 0) {
                fullAddress = countryCode.equals("US") ? format("%s, %s", countryCode, fullAddress) :
                        format("%s%s", countryCode, fullAddress.substring(fullAddress.split(";")[0].length()));
            }
            fullAddress = countryCode.equals("US") ? fullAddress : format("%s %s", zipCode, fullAddress);

            String phone = faker.phoneNumber().phoneNumber();
            String[] line = new String[]{fullName, fullAddress, phone};
            strings.add(line);
        }
        return strings;
    }

    public void convertToCsv(List<String[]> strings) throws IOException {
        PrintWriter printWriter = new PrintWriter(System.out);
        CSVWriter writer = new CSVWriter(
                printWriter, ';', CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                CSVWriter.DEFAULT_LINE_END);
        writer.writeAll(strings);
        writer.close();
    }
}
