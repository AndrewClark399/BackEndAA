package com.lbg.cczone.selenium;

import java.time.Duration;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Sql(scripts = { "classpath:CCZone-schema.sql",
		"classpath:CCZone-data.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)

public class CartTest {

	private RemoteWebDriver driver;

	@BeforeEach
	void init() {
		this.driver = new EdgeDriver();
		this.driver.manage().window().maximize();
		this.driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}

	@Test
	@Order(1)

	void updateItemQuantity() {
		this.driver.get("http://localhost:3000/cart");
		WebElement UpdateItem = this.driver.findElement(By.cssSelector(
				"body > div > div:nth-child(3) > div > div:nth-child(4) > div > div:nth-child(1) > div > div > div.card-text > button:nth-child(2)"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", UpdateItem);
		this.driver.executeScript("arguments[0].click();", UpdateItem);

		WebElement UpdateSelect = this.driver.findElement(
				By.cssSelector("body > div > div:nth-child(3) > div > div:nth-child(10) > div > button:nth-child(3)"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", UpdateSelect);
		this.driver.executeScript("arguments[0].click();", UpdateSelect);

		WebElement newItemQuantity = this.driver.findElement(By.id("itemQuantity"));
		newItemQuantity.sendKeys("8");

		WebElement UpdateSubmit = this.driver
				.findElement(By.cssSelector("body > div > div:nth-child(3) > form > button"));
		this.driver.executeScript("arguments[0].scrollIntoView(true);", UpdateSubmit);
		this.driver.executeScript("arguments[0].click();", UpdateSubmit);

		WebElement CheckQuantity = this.driver.findElement(
				By.cssSelector("body > div > div:nth-child(3) > div > div:nth-child(10) > div > div > p:nth-child(4)"));
		Assertions.assertEquals("QUANTITY: 8", CheckQuantity.getText());
	}

}
