package ADM;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import Utilities.Constants;
import Utilities.ExcelUtils;
import global_case.Connexion;

public class ADM_001_N {
	private WebDriver driver;
	private boolean acceptNextAlert = true;
	private StringBuffer verificationErrors = new StringBuffer();
	// Instanciation de la classe ExcelUtils
	static ExcelUtils excelUtils = new ExcelUtils();
	// R�cup�ration du chemin complet du fichier de data
	static String excelFilePath = Constants.Path_TestData + Constants.File_TestData;
	// On instancie la classe Connexion afin de pouvoir exploiter les m�thodes qui
	// la compose
	static Connexion connexion = new Connexion();

	@Before
	public void setUp() throws InterruptedException {
		System.setProperty("webdriver.gecko.driver",
				"C:\\Users\\cleme\\Downloads\\geckodriver-v0.29.0-win64\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);

		// R�cup�ration du document de donn�es
		// Try permet d'essayer de r�aliser l'action entre {}
		// Si l'action �choue, catch est appell� et affiche un message d'erreur, sinon
		// le script continue
		try {
			excelUtils.setExcelFile(excelFilePath, "loginAdmin");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Test
	public void test() {
		// For d�marre une boucle avec comme param�tres :
		// - la variable i qui d�bute � 0
		// - tant que i est plus petit que le nombre lignes du data sheet, on reste dans
		// la boucle
		// - i s'incr�mente 1 � chaque passage dans la boucle
		for (int i = 0; i < excelUtils.getRowCountInSheet(); i++) {
			// On r�cup�re le login � la ligne i qu'on impl�mente dans la variable login
			String login = excelUtils.getCellData(i + 1, 0);
			// On r�cup�re le password � la ligne i qu'on impl�mente dans la variable
			// password
			String password = excelUtils.getCellData(i + 1, 1);
			try {
				// On appelle la m�thode testGoogleSearch() appartenant � la classe instanci�e
				// Connexion

				// On appelle la m�thode testConnexion appartenant � la classe instanci�e
				// Connexion avec en param�tres le login et le password demand� par les
				// arguments de la m�thode
				connexion.testConnexion(driver, login, password);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				connexion.testDeconnexion(driver);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}