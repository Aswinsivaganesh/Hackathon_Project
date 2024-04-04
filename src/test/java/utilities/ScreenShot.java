package utilities;

import java.io.File;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import TestCases.BaseClass;

public class ScreenShot extends BaseClass {
	public void takeScreenShot(String fileName) throws Exception {
		String directoryPath = System.getProperty("user.dir");
		String relativePath = "/Screenshots/" + fileName + ".png";
		File src = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(directoryPath + relativePath);
		FileUtils.copyFile(src, dest);
	}
}
