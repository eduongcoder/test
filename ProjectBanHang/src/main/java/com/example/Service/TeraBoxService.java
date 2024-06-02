package com.example.Service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

@Service
public class TeraBoxService {

    private WebDriver driver;

    @PostConstruct
    public void init() {
        System.setProperty("webdriver.edge.driver", "path/to/msedgedriver");
        EdgeOptions options = new EdgeOptions();
        options.addArguments("--headless"); // Chạy trình duyệt ở chế độ headless nếu cần
        driver = new EdgeDriver(options);
    }

    public void loginAndUploadFile(String username, String password, String filePath) {
        try {
            driver.get("https://www.terabox.com");

            // Đăng nhập
            WebElement loginButton = driver.findElement(By.id("login_button_id")); // Sửa lại với ID đúng của nút login
            loginButton.click();

            WebElement usernameField = driver.findElement(By.id("username_field_id")); // Sửa lại với ID đúng của trường username
            usernameField.sendKeys(username);

            WebElement passwordField = driver.findElement(By.id("password_field_id")); // Sửa lại với ID đúng của trường password
            passwordField.sendKeys(password);

            WebElement submitButton = driver.findElement(By.id("submit_button_id")); // Sửa lại với ID đúng của nút submit
            submitButton.click();

            // Chờ đăng nhập hoàn tất
            Thread.sleep(5000); // Chờ 5 giây để đảm bảo đăng nhập thành công, có thể điều chỉnh

            // Upload file
            WebElement uploadButton = driver.findElement(By.id("upload_button_id")); // Sửa lại với ID đúng của nút upload
            uploadButton.click();

            WebElement fileInput = driver.findElement(By.cssSelector("input[type='file']")); // Chọn phần tử input file
            fileInput.sendKeys(filePath);

            // Chờ upload hoàn tất
            Thread.sleep(10000); // Chờ 10 giây để đảm bảo upload thành công, có thể điều chỉnh

        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            driver.quit();
        }
    }
}