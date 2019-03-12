# Play Starter Project With Gradle 

This is the starter project of play framework with gradle build tool.
## Build and run the project

This project includes all Play components and an Akka HTTP server. The project is also configured with following components:

- slick(postgresql)
- jackson
- asyncHttpClient
- grpc
- zipkin

To build and run the project:

1. Use a command window to change into the example project directory: `cd play-gradle-starter`

2. Run script `bash script/idea.sh` to generate IDEA project.

3. Import project by IDEA.

4. Build the project. Enter: `bash script/run.sh`. The project builds and starts the embedded HTTP server. Since this downloads libraries and dependencies, the amount of time required depends partly on your connection's speed.

5. After the message `Server started, ...` displays, enter the following URL in a browser: <http://localhost:9000>

