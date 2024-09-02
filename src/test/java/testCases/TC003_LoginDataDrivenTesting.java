package testCases;

import org.testng.Assert;
import org.testng.annotations.Test;

import pageObjects.HomePage;
import pageObjects.LoginPage;
import pageObjects.MyAccountPage;
import testBase.BaseClass;
import utilities.DataProviders;

/*
Data is valid --> login success --> test pass --> logout
Data is valid --> login failed --> test fail
Data is invalid --> login success --> test fail --> logout
Data is invalid --> login fail --> test pass
 */

public class TC003_LoginDataDrivenTesting extends BaseClass
{
	@Test(dataProvider="LoginData", dataProviderClass=DataProviders.class, groups="Datadriven")//getting data provider from different class no need if in same class
	public void verify_DataDrivernTesting(String email, String pwd, String exp)
	{
		logger.info("***** Starting TC003_LoginDataDrivenTesting *****");
		
		try
		{
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPassword(pwd);
		lp.clickLogin();
		
		MyAccountPage map=new MyAccountPage(driver);
		boolean targetPage=map.isMyAccountPageExists();
		
		if(exp.equalsIgnoreCase("valid"))
		{
			if(targetPage==true)
			{
				map.clickLogout();
				Assert.assertTrue(true);
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(exp.equalsIgnoreCase("invalid"))
		{
			if(targetPage==true)
			{
				map.clickLogout();
				Assert.assertTrue(false);
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}
		catch(Exception e)
		{
			Assert.fail();
		}
		logger.info("***** Finished TC003_LoginDataDrivenTesting *****");
	}
	

}
