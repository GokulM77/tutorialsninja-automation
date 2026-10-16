package framework.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static final int MAX_RETRIES = 1;
    private int attempt = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (attempt < MAX_RETRIES && isTransient(result.getThrowable())) {
            attempt++;
            System.out.println("[RETRY] " + result.getMethod().getMethodName()
                    + " - retry " + attempt + " of " + MAX_RETRIES);
            return true;
        }
        return false;
    }

    private boolean isTransient(Throwable throwable) {
        while (throwable != null) {
            String message = throwable.getMessage();
            //check for Chrome’s renderer timeout and null
            if (message != null && message.contains("Timed out receiving message from renderer")) {
                return true;
            }
            throwable = throwable.getCause();
        }
        return false;
    }
}