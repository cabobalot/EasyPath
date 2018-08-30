<center>
	<h1>EasyPath</h1>
	<h3>The easiest way to get your robot moving</h3>
</center>

---

### What is this?
EasyPath is a library that allows FRC robots to easily navigate along complex paths during the autonomous period of play. It's dead simple and can often be set up in less than 30 minutes.

### How do I use it?
Right now, the easiest way is to check out the Releases tab on GitHub and download the latest release of EasyPath. You can search online how to include external jar files or libraries in your project based on your different IDE, [such as Eclipse](https://www.google.com/search?q=eclipse%20include%20external%20jar&oq=eclipse%20include%20external%20jar&aqs=chrome..69i57j0l5.5308j0j7&sourceid=chrome&ie=UTF-8). (In 2019, once FRC moves over to the Gradle build system, you should be able to include it in your project by adding just one line to your `build.gradle` file! Work in progress.)

After you've done that, check out the setup instructions at [`docs/Setup.md`](https://github.com/tervay/EasyPath/blob/master/docs/Setup.md)
to learn more about how to set it up on your robot. There are also docs on tuning the P loop and
creating paths inside the `docs/` folder.

### What robots can use this?
Do you use Java? Do you have at least 1 encoder on your drive train? Do you have a gyro? Do you use a tank drive? If you said yes to all of those questions, you can use EasyPath! And yes -- you saw that right. Even if you only have one encoder on your drive train, you can still use it. We actually developed it with only 1 encoder working.

### What can it do?
Some features:

 - Web GUI to generate paths; copy-paste generated code from the website into your robot code and hit run
 - It's extremely easy to tune - it drives with just a single P loop controller. It's a little bit of a weird P loop, but it does boil down to just a P loop. I've yet to see a robot that it can't handle.
 - It's very consistent - during testing, we often found the robot would wind up within 0.5 inches to 1 inch of the same exact spot on every single run - even on far side scale autos. (This is highly dependent on having a fresh battery.)
 - Almost anyone can use it. It doesn't require special speed controllers. It doesn't require fancy hardware. Just 1-2 encoders (2 **highly** recommended), a gyro, and a robot that can move.

### What can't it do?
Full disclosure - this isn't perfect. This is not going to be as accurate or as smooth or as consistent as solutions like FRC 254's Trajectory or Jaci's Pathfinder. However, it is _far_ simpler to set up, configure, and get running - and sometimes you only have little time or resources in order to get your autos working in time for the competition.

Things that EasyPath struggles with:

1. Turning large amounts in a single path. By default (and you are free to override this behavior in your code), we estimate total distance traveled as the average of the readings of your left and right drive train encoders. This obviously isn't perfect, but it's extremely fast and it's "good enough" for _most_ paths. The more you turn in a single path, the larger the error that will accumulate in time, and your robot will end in a different spot than you told it to. It will usually always finish in the same spot, just a different one than the ending position you specified.
2. Turning in place. This doesn't support rotating in place. Sorry. This is a well documented operation and it's best to leave this up to the user, since turning in place is a largely robot specific operation. If you wanted to turn 180 degrees, you'd have to effectively make multiple paths that look like a three-point turn, like [2791 did for this auto.](https://www.thebluealliance.com/match/2018dar_qm108)
3. A dying battery. Easiest solution -- don't run an auto with a dead battery.  ¯\\_(ツ)\_/¯
4. Highly granular, mid-path controls. For example, if you wanted to raise your elevator when you are 80% done with the path... That's not possible. Yet. You _can_ add a command to run it in parallel with the path and start moving it after a certain time, but that's kind of jank. Your best bet is to split your auto up into multiple paths and run commands like raising your elevator in between smaller paths. This is a problem I am actively working to solve, but I am not sure of a timeline for it.

### How does it work?
todo

### What about C++ and LabView? What about swerve drives or H-drives?
C++ won't be available for a little while. I'm hoping to work on a C++ port in summer/fall 2019. I'm not going to create a LabView port, but you're welcome to give it a shot.

Controls for other drive trains are also a work in progress, but I do not have a timeline for any of them.

### I found a bug or I just want to complain about how awkward something is to use.
Tell me what the problem is and I will do my best to fix it ASAP. You can file an Issue on the GitHub project - please be descriptive!

### I want to help / expand EasyPath / add a feature!
You're free to submit a pull request if you made something cool! Just make sure you test it thoroughly, preferably on more than one distinct robot.