// Required imports
import javax.script.ScriptEngineManager;
import javax.script.ScriptEngine;
import javax.script.ScriptException;
import java.io.FileReader;
import java.io.FileNotFoundException;

public class Webserver {
  public Webserver() {
    // Obtain an instance of the JavaScript engine
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("nashorn");

    // Load and execute a script from the file 'demo.js'
    try {
        engine.eval(new FileReader("app.js"));
    } catch (FileNotFoundException ex) {
        ex.printStackTrace();
    } catch (ScriptException ex) {
        // This is the generic Exception subclass for the Scripting API
        ex.printStackTrace();
    }
  }
}
// Outcome:
// 'Script from file!' printed on standard output
