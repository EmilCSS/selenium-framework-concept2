package qa.hs.framework.tests;

import java.io.File;
import java.util.List;
import java.util.Map;

import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.xml.XmlTest;

import qa.hs.framework.AutomationTest;
import qa.hs.framework.SeHelper;

/* TODO for SauceLabs job update

String jobID = ((RemoteWebDriver) driver).getSessionId().toString();
String user = "jausten";
String accessKey = "";
Map<String, Object> updates = new HashMap<String, Object>();
updates.put("name", "this job has a name");
//updates.put( "passed", true );
updates.put("build", "c234");
SauceREST client = new SauceREST( user, accessKey );
client.updateJobInfo( jobID, updates );
String jobInfo = client.getJobInfo(args[2]);
System.out.println("Job info: " + jobInfo);
client.jobPassed( jobID );
*/

public class SampleFunctionalTest extends AutomationTest {
	
    @Test(dataProvider = "testdata1")
    public void test1( SeHelper se, XmlTest testArgs ) {
    	navigateTo( appUrl )
        .click( props.get( "autocomplete" ) )
        .enterTextIntoField( props.get( "autocomplete" ), "buttons" )
        .sleep( 1000 )
        .selectFromDropdownByText( props.get( "autocomplete" ), props.get( "suggest" ), "large buttons" )
        .sleep( 1000 )
        .click( props.get( "search" ) )
        .sleep( 1000 );        
    }
    
    @Test(dataProvider = "testdata1")
    public void test2( SeHelper se, XmlTest testArgs ) {
    	navigateTo( appUrl )
        .click( props.get( "autocomplete" ) )
        .enterTextIntoField( props.get( "autocomplete" ), "zippers" )
        .sleep( 1000 )
        .selectFromDropdownByText( props.get( "autocomplete" ), props.get( "suggest" ), "metal zippers" )
        .sleep( 1000 )
        .click( props.get( "search" ) )
        .sleep( 1000 );
    }
    
    @AfterClass
    public void cleanUp() {
    	closeAllWindows();
    }
    
	@DataProvider(name = "testdata1")
	public Object[][] getTestData1( ITestContext context ) {
		// returns 2 args per test contained in the testng.xml file: SeHelper and XmlTest
		List<XmlTest> tests = context.getSuite().getXmlSuite().getTests();
		Map<String, String> suiteParams = context.getSuite().getXmlSuite().getAllParameters();
		String testParam = context.getCurrentXmlTest().getParameter("test_param");
	    return new Object[][] {{ testParam }};
	}
    
}
