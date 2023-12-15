package dev.surya.labs.service.impl;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;
import com.opencsv.CSVWriterBuilder;
import com.opencsv.exceptions.CsvException;

import dev.surya.labs.entity.Content;
import dev.surya.labs.service.MyFileService;

@Service
public class CsvUpdater {
	
	@Autowired
	private MyFileService myFileService;

	public static void main(String[] args) throws CsvException {
		// Replace "yourfile.csv" with the path to your CSV file
		String csvFile = "yourfile.csv";

		try {
			// Step 1: Read the CSV file
			CSVReader reader = new CSVReaderBuilder(new FileReader(csvFile)).build();
			List<String[]> csvData = reader.readAll();
			reader.close();

			// Step 2: Update the data
			int rowToUpdate = 2; // Replace with the index of the row you want to update
			String[] updatedRowData = { "New Data", "Updated Value", "More Data" }; // Replace with the new data

			if (rowToUpdate >= 0 && rowToUpdate < csvData.size()) {
				csvData.set(rowToUpdate, updatedRowData);
			} else {
				System.out.println("Invalid row index");
				return;
			}

			// Step 3: Write the updated data back to CSV
			CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(csvFile)).build();
			writer.writeAll(csvData);
			writer.close();

			System.out.println("Row updated successfully.");

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Content updateData(Content content, String Status, String contentText) {
		Resource resource = myFileService.readFile("data.csv");

		try {
			// Step 1: Read the CSV file
			CSVReader reader = new CSVReaderBuilder(new FileReader(resource.getFile())).build();
			List<String[]> csvData;
				csvData = reader.readAll();
			reader.close();

			// Step 2: Update the data
			int rowToUpdate = content.getId().intValue(); // Replace with the index of the row you want to update
			String updatedContent = (contentText == null?csvData.get(rowToUpdate-1)[1]: contentText);
			String[] updatedRowData = { rowToUpdate+"",updatedContent , Status }; // Replace with the new data
			
			if (rowToUpdate >= 0 && rowToUpdate <= csvData.size()) {
				csvData.set(rowToUpdate-1, updatedRowData);
				content.setContent(updatedContent);
				
			} else {
				System.out.println("Invalid row index");
				return content;
			}
			

			// Step 3: Write the updated data back to CSV
			CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(resource.getFile())).build();
			writer.writeAll(csvData);
			writer.close();

			System.out.println("Row updated successfully.");

		} catch (IOException e) {
			e.printStackTrace();
		}catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;

	}

	public Content createData(String contentText) {
		Content content = new Content(); 
		Resource resource = myFileService.readFile("data.csv");

		try {
			// Step 1: Read the CSV file
			CSVReader reader = new CSVReaderBuilder(new FileReader(resource.getFile())).build();
			List<String[]> csvData;
				csvData = reader.readAll();
			reader.close();

			String newID = csvData.size()+1+"";
			String[] updatedRowData = {newID ,contentText , "Y" }; // Replace with the new data
			content.setActive("Y");
			content.setContent(contentText);
			content.setId(new Long(newID));
			content.setTopic("General");
			csvData.add(csvData.size(), updatedRowData);

			// Step 3: Write the updated data back to CSV
			CSVWriter writer = (CSVWriter) new CSVWriterBuilder(new FileWriter(resource.getFile())).build();
			writer.writeAll(csvData);
			writer.close();

			System.out.println("Row updated successfully.");

		} catch (IOException e) {
			e.printStackTrace();
		}catch (CsvException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return content;
	}
}
