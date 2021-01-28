package global_case;
import java.util.regex.Pattern;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import Utilities.Constants;
import Utilities.ExcelUtils;

public class Connexion {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();

	@Before
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\cleme\\Downloads\\geckodriver-v0.29.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	@Test
	// La m�thode testConnexion attend en arguments deux param�tres : login & password
	public void testConnexion(WebDriver driver, String login, String password) throws Exception {
			String URL = Constants.URL;
			driver.get(URL);
			driver.findElement(By.id("edit-name")).click();
			driver.findElement(By.id("edit-name")).clear();
			// On utilise variable login et password cr��es pr�cedemment contenant les informations de connexion
			driver.findElement(By.id("edit-name")).sendKeys(login);
			driver.findElement(By.id("edit-pass")).click();
			driver.findElement(By.id("edit-pass")).clear();
			driver.findElement(By.id("edit-pass")).sendKeys(password);
			driver.findElement(By.id("edit-submit")).click();
			driver.findElement(By.linkText("Mon compte")).click();
			try {
				assertEquals("demo1", driver.findElement(By.xpath("//div[@id='content-inner-inner']/h1")).getText());
			} catch (Error e) {
				verificationErrors.append(e.toString());
			}
		}
	@Test
	public void testDeconnexion(WebDriver driver) throws Exception {
		driver.findElement(By.linkText("Se d�connecter")).click();
	}

	@After
	public void tearDown() throws Exception {
		driver.quit();
		String verificationErrorString = verificationErrors.toString();
		if (!"".equals(verificationErrorString)) {
			fail(verificationErrorString);
		}
	}
 
	private boolean isElementPresent(By by) {
		try {
			driver.findElement(by);
			return true;
		} catch (NoSuchElementException e) {
			return false;
		}
	}

	private boolean isAlertPresent() {
		try {
			driver.switchTo().alert();
			return true;
		} catch (NoAlertPresentException e) {
			return false;
		}
	}

	private String closeAlertAndGetItsText() {
		try {
			Alert alert = driver.switchTo().alert();
			String alertText = alert.getText();
			if (acceptNextAlert) {
				alert.accept();
			} else {
				alert.dismiss();
			}
			return alertText;
		} finally {
			acceptNextAlert = true;
		}
	}
}
