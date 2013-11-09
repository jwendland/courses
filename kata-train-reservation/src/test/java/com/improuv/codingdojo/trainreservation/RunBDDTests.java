package com.improuv.codingdojo.trainreservation;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Running this class with junit will execute all *.feature files.
 *
 * Features for Cucumber are written in the "Gherkin" language.
 * See e.g. http://cukes.info/step-definitions.html to learn how
 * to write those features and the according step-definitions.
 *
 * Step 1: write a valid feature file and throw it into your
 *         resources folder, same package.
 * Step 2: let the test fail, copy the snippets from the output
 *         and adjust them according to your needs.
 * Step 3: add some implementation so that the test does what you want
 */
@RunWith(Cucumber.class)
@Cucumber.Options(format={"pretty", "html:target/cucumber"})
public class RunBDDTests {

}
