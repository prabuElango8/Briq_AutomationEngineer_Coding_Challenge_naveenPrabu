package com.briq.seleniumautomation;

	import java.awt.Robot;
	import java.awt.AWTException;
	import java.awt.*;
	import java.time.Duration;
	import java.awt.Toolkit;
	import java.awt.datatransfer.StringSelection;
	import java.util.ArrayList;
	import java.util.List;
	import org.openqa.selenium.By;
	import org.openqa.selenium.WebDriver;
	import org.openqa.selenium.WebElement;
	import org.openqa.selenium.chrome.ChromeDriver;
	import org.openqa.selenium.devtools.v113.input.Input.DispatchKeyEventType;
	import org.openqa.selenium.support.ui.Select;

	import com.sun.glass.events.KeyEvent;

	import io.github.bonigarcia.wdm.WebDriverManager;

	public class UploadAndDownloadAttachments {

		public static void main(String[] args) throws InterruptedException, AWTException {

			WebDriverManager.chromedriver().setup();
			WebDriver driver = new ChromeDriver();
			// Navigate to the webpage
			driver.get("https://the-internet.herokuapp.com/download");

			// Find all elements with download links
			List<WebElement> downloadLinks = driver.findElements(By.cssSelector("a[href^='download/']"));

			// Filter out PNG image links
			List<WebElement> nonPngLinks = new ArrayList<>();
			for (WebElement link : downloadLinks) {
				String href = link.getAttribute("href");
				if (!href.endsWith(".png")) {
					nonPngLinks.add(link);
				}
			}
			String href="";
			// Download non-PNG attachments
			for (WebElement link : nonPngLinks) {
				 href = link.getAttribute("href");
				link.click(); // This should initiate the download

				System.out.println("Downloaded file: " + href);
			}
			Thread.sleep(3000);
			// File uploading

			driver.navigate().to("https://the-internet.herokuapp.com/upload");
			WebElement chooseFile = driver.findElement(By.id("file-upload"));
			chooseFile.click();

			Robot robot = new Robot();
			StringSelection slection = new StringSelection(href);
			Toolkit.getDefaultToolkit().getSystemClipboard().setContents(slection, null);
			Thread.sleep(4000);
			robot.keyPress(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_V);
			robot.keyRelease(KeyEvent.VK_CONTROL);
			robot.keyPress(KeyEvent.VK_ENTER);

		}

	}
