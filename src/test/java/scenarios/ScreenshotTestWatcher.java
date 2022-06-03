package scenarios;

import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.lang.reflect.Method;
import static base.Base.driver;

public class ScreenshotTestWatcher implements AfterTestExecutionCallback {

    @Override
    public void afterTestExecution(ExtensionContext context) throws Exception {
        Method method = context.getRequiredTestMethod();
        if(context.getExecutionException().isPresent()){
            makeScreenshot(method.getName());
        }
    }

    @Attachment(value = "{testName} - screenshot", type = "image/png")
    private byte[] makeScreenshot(String testName){
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
