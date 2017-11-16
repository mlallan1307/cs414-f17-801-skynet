Development Manual
=======================

----------

### Contents

 1. Setup
 2. Testing
 3. Repository Management

----------

### 1. Setup

> Note: Setup instructions assume you are using a Windows OS

 1. Install required tools according to their respective instructions:

  * GitHub Desktop (For git interface)
  * Eclipse (Kepler)
  * Doxygen GUI

 2. Clone the repository from using GitHub Desktop (https://github.com/mlallan1307/cs414-f17-801-skynet.git)
 3. Create a new project in Eclipse and open the code in the `src` directory of the repo or copy it to your eclipse working directory
 4. The code is organized into packages as seen in the Package Diagram. You can view the diagrams and documents (e.g. Sequence Diagram, Class Diagram, Domain Model, Glossary, Use Cases, Doxygen) in the repo's Documentation directory to learn more about the structure of the system.
 5. View doxygen by opening it in your web browser. The file to open is located in the repo under (Doxygen\html\index.html)
 5. To bring up the GUI, run the Launcher.java class in the UI-Swing package
 6. Serialized data files may be created by the system running and are saved in the current working directory. 
 7. Use Doxygen (config file provided in the docs directory of the code) to regenerate the Doxygen html web pages

----------

### 2. Testing

 * There is a top level **AllTest.java** file which can be used to run all the JUnit tests
 * There are also JUnit test suite files for each level one package, and most level two packages that must be updated as new tests are added
 * The JUnit test files are with their respective classes

**Running all Tests:**

 1. Locate the **test.edu.colostate.cs.cs414.skynet_gym.AllTest.java** file
 2. In Eclipse, select the file and click the run button. Alternatively, you can right click the file and select run.
 3. You can run it as a Java application (To see text output) or JUnit test (For the full Eclipse JUnit test overview).

----------

### 3. Repository Management

When making changes you must first create a branch off of Master. Be sure to keep the branch up to date if master changes after you branch off. When you have finished making changes you should create a pull request of the github repository. If the changes you make addres an open issue then that should be mentioned in the pull request. A repository admin will approve the pull request or respond with feedback. You should not merge it into the Master branch yourself. Changes to the code will be accepted only if all related documentation updates have been made also.

The latest CircleCI build & test run must pass in order for you pull request to be merged.