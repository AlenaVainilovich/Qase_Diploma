package tests.base;

import lombok.extern.log4j.Log4j2;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

@Log4j2
public class Retry implements IRetryAnalyzer {

    private static final int MAX_RETRY = 1;
    private int attempt = 1;

    @Override
    public boolean retry(ITestResult iTestResult) {
        if (!iTestResult.isSuccess()) {
            if (attempt < MAX_RETRY) {
                log.info("Retrying once again test '{}', attempt #{}", iTestResult.getName(), attempt);
                attempt++;
                return true;
            } else {
                iTestResult.setStatus(ITestResult.FAILURE);
            }
        } else {
            iTestResult.setStatus(ITestResult.SUCCESS);
        }
        return false;
    }

}
