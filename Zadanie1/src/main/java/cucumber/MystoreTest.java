package cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@ RunWith(Cucumber.class)
@CucumberOptions(features = "src/Cucumber/Features/search.feature",
        plugin = {"pretty","html:out"})  //zapisuje raport do pliku out.html pod target

public class MystoreTest {
    }
