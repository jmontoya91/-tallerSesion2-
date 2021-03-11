package Taller2Movil;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.openqa.selenium.By;

import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

import java.util.concurrent.TimeUnit;


public class AppiumTaller2 {


    // creamos el Driver Appium
    private AppiumDriver driver;
    String Titulo="Prueba";
    String Titulo2="Prueba2";
        @Before
    public void Before() throws MalformedURLException {
            DesiredCapabilities ConfiguracionAppium =new DesiredCapabilities();
            ConfiguracionAppium.setCapability("deviceName","HUAWEI P20 lite");
            ConfiguracionAppium.setCapability("platformVersion","9");
            ConfiguracionAppium.setCapability("appPackage","com.vrproductiveapps.whendo");
            ConfiguracionAppium.setCapability("appActivity",".ui.HomeActivity");
            ConfiguracionAppium.setCapability("platformName","Android");

            // driver apunte a nuestro appiumDesktop
            driver= new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"),ConfiguracionAppium);

            // implicit:
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

    public void Crear_Nota(){
            driver.findElement(By.xpath("//android.widget.ImageButton[@resource-id='com.vrproductiveapps.whendo:id/fab']")).click();
            driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.vrproductiveapps.whendo:id/noteTextTitle']")).sendKeys(Titulo);
            driver.findElement(By.xpath("//android.widget.EditText[@index='2']")).sendKeys("Prueba 1");
            driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Guardar']")).click();
      }
    public void Editar_Nota(){
        Crear_Nota();
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+Titulo+"']")).click();
        driver.findElement(By.xpath("//android.widget.EditText[@resource-id='com.vrproductiveapps.whendo:id/noteTextTitle']")).sendKeys(Titulo2);
        driver.findElement(By.xpath("//android.widget.EditText[@index='2']")).sendKeys("Prueba Editada");
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Guardar']")).click();
    }

    public void Eliminar_Nota(){
        Editar_Nota();
        driver.findElement(By.xpath("//android.widget.TextView[@text='"+Titulo2+"']")).click();
        driver.findElement(By.xpath("//android.widget.TextView[@content-desc='Eliminar']")).click();
        driver.findElement(By.xpath("//android.widget.Button[@text='ELIMINAR']")).click();
    }


    @Test
    public void A(){
            Crear_Nota();
        int Notas=driver.findElements(By.xpath("//android.widget.TextView[@text='"+Titulo+"']")).size();
        Assert.assertEquals("ERROR en contacto",1,Notas);
    }
    @Test
    public void B() {
        Editar_Nota();
        String expectedResult="Prueba2";
        String ResultadoReal=driver.findElement(By.xpath("//android.widget.TextView[@text='"+Titulo2+"']")).getText();
        Assert.assertEquals("Error el resulta es incorrecto",expectedResult,ResultadoReal);
    }

    @Test
    public void C() {
        Eliminar_Nota();
        int Notas=driver.findElements(By.xpath("//android.widget.TextView[@text='"+Titulo2+"']")).size();
        Assert.assertEquals("ERROR en contacto",0,Notas);
        }


        @After
    public void After(){
        driver.quit();

    }



}
