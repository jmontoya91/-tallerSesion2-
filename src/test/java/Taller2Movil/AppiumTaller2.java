package Taller2Movil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;


public class AppiumTaller2 {


    // creamos el Driver Appium
    private AppiumDriver driver;

        @Before
    public void Before() throws MalformedURLException {
            DesiredCapabilities ConfiguracionAppium =new DesiredCapabilities();
            ConfiguracionAppium.setCapability("deviceName","HUAWEI P20 lite");
            ConfiguracionAppium.setCapability("platformVersion","9");
            ConfiguracionAppium.setCapability("appPackage","com.android.calculator2");
            ConfiguracionAppium.setCapability("appActivity","Calculator");
            ConfiguracionAppium.setCapability("platformName","Android");

            // driver apunte a nuestro appiumDesktop
            driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),ConfiguracionAppium);

            // implicit:
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);



        }


    @Test
    public void Verificar_calcular(){
        // elemento 1
            driver.findElement(By.id("com.android.calculator2:id/digit_9")).click();
        // suma
          driver.findElement(By.id("com.android.calculator2:id/op_add")).click();
        // elemento 2
        driver.findElement(By.xpath("//android.widget.Button[@text='2']")).click();
         // igual
        driver.findElement(By.id("com.android.calculator2:id/eq")).click();
        String expectedResult="13";

        String actualResult= driver.findElement(By.id("com.android.calculator2:id/formula")).getText();
        Assert.assertEquals("Error el resulta es incorrecto",expectedResult,actualResult);

    }






    @After
    public void After(){
        driver.quit();

    }



}
