#Tick tack toe in scala
work in progress!!!

## Run the application
    shell
    $ sbt
    > run
    $ open http://localhost:9000
	
- Run application like a regular Play app
  - `compile` simply triggers the Scala.js compilation
  - `run` triggers the Scala.js fastOptJS command on page refresh
  - `~compile`, `~run`, continuous compilation is also available
  - `start`, `stage` and `dist` generate the optimised javascript

based on great example [here](https://github.com/vmunier/play-with-scalajs-example)

### Running tests
Run both client and server tests
`$ sbt test`

Run only client tests
`$ sbt client/test`

Run only server tests
`$ sbt server/test`

Can also debug server tests
1. Configure intellj
    1. Run -> Edit Configurations...
    1. Click [+] icon in upper left (Add a configuration)
    1. Remote Configuration
    1. Give it whatever name you want
    1. Add breakpoints to the server code
1. Run the command `sbt server/test -jvm-debug 5005`
    * Note: Can also debug the actual server using `sbt run -jvm-debug 5005`
1. Right after running the command from the previous step execute intellj's 'Debug' command for the Remote Configuration that was setup earlier

Whats going on here is that the command line argument to sbt `-jvm-debug` adds the remote debugging configuration params to the JVM so that intellj can attach to it. Check out http://stackoverflow.com/questions/4150776/debugging-scala-code-with-simple-build-tool-sbt-and-intellij for more details.

I have run into issues with sometimes breakpoints not being hit (in the server only tests), unclear to me why that is happening at the moment.
