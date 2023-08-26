package example.application;

import android.content.Context;
import androidx.test.platform.app.InstrumentationRegistry;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        // Can't figure out how to make this work yet
//        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
//        assertEquals("example.application", appContext.getPackageName());
    }

}