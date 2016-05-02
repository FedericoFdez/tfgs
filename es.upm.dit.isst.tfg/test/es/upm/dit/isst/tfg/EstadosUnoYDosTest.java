package es.upm.dit.isst.tfg;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;

public class EstadosUnoYDosTest {
	private WebDriver driver;
	private String baseUrl;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws Exception {
		FirefoxProfile profile = new FirefoxProfile();
		profile.setPreference("browser.privatebrowsing.autostart", true);
		driver = new FirefoxDriver(profile);
		baseUrl = "http://isst-tfg.appspot.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	public void testEstadosUnoYDos() throws Exception {
		
		// TODO configurar cuentas de Google
		String authorEmail = "";
		String authorPassword = "";
		String tutorEmail = "";
		String tutorPassword = "";
		String secretaryEmail = "";

		driver.get(baseUrl + "/");
		driver.findElement(By.linkText("iniciar sesión")).click();
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(authorEmail);
		driver.findElement(By.id("next")).click();
		driver.findElement(By.id("Passwd")).clear();
		// driver.findElement(By.id("Passwd")).sendKeys(authorPassword);
		driver.findElement(By.id("PersistentCookie")).click();
		// driver.findElement(By.id("signIn")).click();
		driver.findElement(By.id("title")).clear();
		driver.findElement(By.id("title")).sendKeys("Mi TFG");
		driver.findElement(By.id("summary")).clear();
		driver.findElement(By.id("summary")).sendKeys(
				"Esto es un resumen de mi tfg");
		driver.findElement(By.id("tutor")).clear();
		driver.findElement(By.id("tutor")).sendKeys(
				tutorEmail);
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		driver.findElement(By.linkText("SALIR")).click();
		driver.findElement(By.linkText("iniciar sesión")).click();
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(tutorEmail);
		driver.findElement(By.id("next")).click();
		driver.findElement(By.id("Passwd")).clear();
		// driver.findElement(By.id("Passwd")).sendKeys(tutorPassword);
		// driver.findElement(By.id("signIn")).click();
		assertEquals("1", driver.findElement(By.cssSelector("td")).getText());
		assertEquals(authorEmail,
				driver.findElement(By.xpath("//td[2]")).getText());
		assertEquals("Mi TFG", driver.findElement(By.xpath("//td[3]"))
				.getText());
		driver.findElement(By.id("secretary")).clear();
		driver.findElement(By.id("secretary")).sendKeys(secretaryEmail);
		driver.findElement(By.cssSelector("input[type=\"submit\"]")).click();
		assertEquals("2", driver.findElement(By.cssSelector("td")).getText());
		assertEquals(authorEmail,
				driver.findElement(By.xpath("//td[2]")).getText());
		assertEquals(secretaryEmail,
				driver.findElement(By.xpath("//td[5]")).getText());
		driver.findElement(By.linkText("SALIR")).click();
		driver.findElement(By.linkText("iniciar sesión")).click();
		driver.findElement(By.id("Email")).clear();
		driver.findElement(By.id("Email")).sendKeys(authorEmail);
		driver.findElement(By.id("next")).click();
		driver.findElement(By.id("Passwd")).clear();
		// driver.findElement(By.id("Passwd")).sendKeys(authorPassword);
		// driver.findElement(By.id("signIn")).click();
		assertEquals(authorEmail,
				driver.findElement(By.cssSelector("td")).getText());
		assertEquals(tutorEmail,
				driver.findElement(By.xpath("//td[4]")).getText());
		assertEquals(secretaryEmail,
				driver.findElement(By.xpath("//td[5]")).getText());
		assertEquals("2", driver.findElement(By.xpath("//td[6]")).getText());
		driver.findElement(By.linkText("SALIR")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
}
