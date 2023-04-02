# PhraseDemoApplication

PhraseDemoApplication is Spring Boot demo application which connects to the [Phrase API](https://cloud.memsource.com/web/docs/api) and allows users to log in to their Phrase account and then list their translation projects.

Technologies used:
1. Java 17
1. Spring Boot 3.0.5
1. Gradle 8.0.2
1. H2 DB (used to store the Phrase account configuration - but the database is in memory only)
1. GSON to parse JSON responses from the Phrase API
1. HTML, CSS, vanilla JS and jQuery on frontend

## Usage

1. Clone this repository.
1. You can either build the JAR artifact yourself, or download it here from resources.
    1. To build it, navigate to the root directory, and run the command:
    ```shell
    gradle build
    ```
    After that, the resulting artifact can be found in build/libs/phrase-demo-0.0.1-SNAPSHOT.jar.
1. Run the backend.
    ```shell
    java -jar phrase-demo-0.0.1-SNAPSHOT.jar
    ```
    The embedded Tomcat application server will be started on port 8080.
1. Open the frontend in the browser. Navigate to the directory frontend and open login.html.
1. Enter valid credentials for your Phrase account. I recommend you use a testing account, not a production, since this application is a proof-of-concept only. The credentials are validated after submitting.
1. After that, you can click Translation projects in the upper navbar and see a list of your projects. The project table is reloaded every 5 seconds.