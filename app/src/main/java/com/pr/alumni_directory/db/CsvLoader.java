package com.pr.alumni_directory.db;

import com.pr.alumni_directory.data.AlumniData;
import com.pr.alumni_directory.data.AlumniSubData;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVPrinter;
import org.apache.commons.csv.CSVRecord;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CsvLoader {

    private final String filePath;

    public CsvLoader(String filePath){

        this.filePath = filePath;
    }

    List<AlumniData> data = new ArrayList<>();

    public void createCSV() throws IOException {
        FileWriter writer = new FileWriter(filePath);

        try (CSVPrinter csvPrinter = new CSVPrinter(
                writer,
                CSVFormat.Builder.create(CSVFormat.DEFAULT)
                        .setHeader("first_name", "last_name", "email", "full_name", "address", "job_title", "name_of_business", "business_address", "business_city", "business_state", "business_zip", "business_phone", "business_email", "business_website", "business_description", "business_category", "alumni_discount", "alumni_discount_description", "business_logo_link", "user_id")
                        .build())
        ) {

        }
    }

    public void readCSV() throws IOException {
        FileReader reader = new FileReader(filePath);

        try (CSVParser csvParser = new CSVParser(reader, CSVFormat.Builder.create(CSVFormat.DEFAULT).setHeader().setSkipHeaderRecord(true).build())) {
            for (CSVRecord record : csvParser) {
                String firstName = record.get("first_name");
                String lastName = record.get("last_name");
                String email = record.get("email");
                String fullName = record.get("full_name");
                String address = record.get("address");
                String jobTitle = record.get("job_title");
                String nameOfBusiness = record.get("name_of_business");
                String businessAddress = record.get("business_address");
                String businessCity = record.get("business_city");
                String businessState = record.get("business_state");
                String businessZip = record.get("business_zip");
                String businessPhone = record.get("business_phone");
                String businessEmail = record.get("business_email");
                String businessWebsite = record.get("business_website");
                String businessDescription = record.get("business_description");
                String businessCategory = record.get("business_category");
                String businessLogo = record.get("business_logo_link");
                String alumniDiscount = record.get("alumni_discount");
                String alumniDisDes = record.get("alumni_discount_description");
                String userId = record.get("user_id");

                AlumniSubData subInfo = new AlumniSubData(userId,nameOfBusiness,businessAddress,businessCity,businessState,businessZip,businessPhone,businessEmail,businessWebsite,businessDescription,businessCategory,alumniDiscount,alumniDisDes,businessLogo);
                AlumniData info = new AlumniData(userId,firstName,lastName,email,fullName,address,jobTitle,subInfo);
                data.add(info);
            }


        }
    }

    public List<AlumniData> getList() {
        return data;
    }

    public void updateCSV(String idToUpdate, String firstName, String lastName, String email, String fullName, String address, String jobTitle, String nameOfBusiness, String businessAddress, String businessCity, String businessState, String businessZip, String businessPhone, String businessEmail, String businessWebsite, String businessDescription, String businessCategory, String alumniDiscount, String alumniDiscountDescription, String businessLogoLink) throws IOException {
        List<CSVRecord> csvRecords;

        try (FileReader reader = new FileReader(filePath);
             CSVParser csvParser = new CSVParser(reader,
                     CSVFormat.Builder.create(CSVFormat.DEFAULT)
                             .setHeader()
                             .setSkipHeaderRecord(true)
                             .build())) {
            csvRecords = new ArrayList<>(csvParser.getRecords());
        }

        try (CSVPrinter csvPrinter = new CSVPrinter(
                new FileWriter(filePath),
                CSVFormat.Builder.create(CSVFormat.DEFAULT)
                        .setHeader("first_name", "last_name", "email", "full_name", "address", "job_title", "name_of_business", "business_address", "business_city", "business_state", "business_zip", "business_phone", "business_email", "business_website", "business_description", "business_category", "alumni_discount", "alumni_discount_description", "business_logo_link", "user_id")
                        .build())
        ) {

            for (CSVRecord record : csvRecords) {
                if (record.get("user_id").equals(idToUpdate)) {
                    csvPrinter.printRecord(firstName, lastName, email, fullName, address, jobTitle, nameOfBusiness, businessAddress, businessCity, businessState, businessZip, businessPhone, businessEmail, businessWebsite, businessDescription, businessCategory, alumniDiscount, alumniDiscountDescription, businessLogoLink, record.get("user_id"));
                } else {
                    csvPrinter.printRecord(record);
                }
            }
        }
    }

    public void deleteFromCSV(String filePath, String idToDelete) throws IOException {
        List<CSVRecord> csvRecords;

        try (CSVParser csvParser = new CSVParser(new FileReader(filePath), CSVFormat.Builder.create(CSVFormat.DEFAULT)
                .setHeader()
                .setSkipHeaderRecord(true)
                .build())) {
            csvRecords = new ArrayList<>(csvParser.getRecords());
        }

        try (CSVPrinter csvPrinter = new CSVPrinter(new FileWriter(filePath), CSVFormat.Builder.create(CSVFormat.DEFAULT)
                .setHeader("first_name", "last_name", "email", "full_name", "address", "job_title", "name_of_business", "business_address", "business_city", "business_state", "business_zip", "business_phone", "business_email", "business_website", "business_description", "business_category", "alumni_discount", "alumni_discount_description", "business_logo_link", "user_id")
                .build())) {
            for (CSVRecord record : csvRecords) {
                if (!record.get("ID").equals(idToDelete)) {
                    csvPrinter.printRecord(record);
                }
            }
        }
    }

    public void insertCSV(String firstName, String lastName, String email, String fullName, String address, String jobTitle, String nameOfBusiness, String businessAddress, String businessCity, String businessState, String businessZip, String businessPhone, String businessEmail, String businessWebsite, String businessDescription, String businessCategory, String alumniDiscount, String alumniDiscountDescription, String businessLogoLink, String userId) throws IOException {
        // Open the file in append mode (true passed to FileWriter)
        FileWriter writer = new FileWriter(filePath, true);


        try (CSVPrinter csvPrinter = new CSVPrinter(
                writer,
                CSVFormat.Builder.create(CSVFormat.DEFAULT)
                        .setHeader()
                        .setSkipHeaderRecord(true)
                        .build())
        ) {

            csvPrinter.printRecord(firstName, lastName, email, fullName, address, jobTitle, nameOfBusiness, businessAddress, businessCity, businessState, businessZip, businessPhone, businessEmail, businessWebsite, businessDescription, businessCategory, alumniDiscount, alumniDiscountDescription, businessLogoLink, userId );

        }

    }
}
