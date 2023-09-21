package com.briq.seleniumautomation;

import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class WebtableToCsvFile {

	public static void main(String[] args) {
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		driver.get("http://the-internet.herokuapp.com/challenging_dom");
		List<List<String>> tableData = new ArrayList<>();
		WebElement table = driver.findElement(By.tagName("table"));
		List<WebElement> rows = table.findElements(By.tagName("tr"));

		for (WebElement row : rows) {
			List<String> rowData = new ArrayList<>();
			List<WebElement> cells = row.findElements(By.tagName("td"));

			for (WebElement cell : cells) {
				rowData.add(cell.getText());
			}
			tableData.add(rowData);
		}

		System.out.println(tableData);
		System.out.println("Column size are " + table.getSize());
		System.out.println("Row size are " + rows.size());

		// Generate csv file name using current time stamp
		String timeStamp = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
		String csvFileName = " C:\\Users\\HP\\Downloads\\briq\\webtable_" + timeStamp + ".csv";

		// creating file writer
		try {
			FileWriter writer = new FileWriter(csvFileName);

			for (List<String> row : tableData) {

				for (String cellData : row) {

					writer.append(cellData);
				}
			}
		}

		catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(csvFileName);
	}

}