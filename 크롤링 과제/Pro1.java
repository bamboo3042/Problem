package com.koreait.crawling;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Pro1 {
	public static void main(String[] args) {
		String DRIVER_ID = "webdriver.chrome.driver";
		String DRIVER_PATH = "C:/Users/user/Desktop/얼라 과제/JSP/source/chromedriver.exe";
		
		System.setProperty(DRIVER_ID, DRIVER_PATH);
		WebDriver driver = new ChromeDriver();
		
		String base_url = "https://www.banapresso.com/store";
		
		try {
			driver.get(base_url);
			int i = 2;
			
			while(true) {
				Thread.sleep(1000);
				
				List<WebElement> elements = driver.findElements(By.className("store_name_map"));
				for(WebElement el : elements) {
					String[] temp = el.getText().split("\n");
					System.out.println(temp[1]);
					System.out.println(temp[2]);
					System.out.println();
				}
				
				if(i%6 == 0) {
					try {
						List<WebElement> Nextpages = driver.findElements(By.cssSelector("div.pagination > span > a"));
						for(WebElement el : Nextpages){
							if(el.getText().equals("다음")) {
								el.click();
								i = 2;
								break;
							}
						}
					}catch (Exception e) {
						break;
					}
				}
				else {
					WebElement Nextpage = driver.findElement(By.cssSelector("div.pagination > ul > li:nth-child("+ i +") > a"));
					Nextpage.click();
					i++;
				}
				
			}
		}catch (Exception e) {
			System.out.println("프로그램 종료");
		}		
	}
}
