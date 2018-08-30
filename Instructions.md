Once you've installed EasyPath and added it as a library to your project (this is well documented
online -- search how to add JARs to Eclipse/VS Code/IntelliJ/etc), setting it up is a breeze.

You have to provide EasyPath a few different things in order for it to work:

1. A function to set the left and right speeds of your drive train
2. A function that gives EasyPath the total inches traveled of your robot
3. A function that gives EasyPath the current heading of your robot, from 0 to 360
4. A function that resets your encoders to zero
5. A P value for the P loop that EasyPath uses in order to control your robot

In the future, I hope to allow the user to provide a function that allows EasyPath to shift your
drive train. There is the code to allow you to do this at the moment, however, it won't shift, as
the shifting logic has not been implemented. (Sorry! Future release.)

Assume you have a DriveTrain class that looks something like this:

```java
class DriveTrain {

  private Motor left, right;
  Encoder leftEnc, rightEnc;
  Gyro gyro;

  DriveTrain() {
    left = new Motor(0);
    right = new Motor(1);
    leftEnc = new Encoder(0);
    rightEnc = new Encoder(1);
    gyro = new Gyro();
  }

  void setLeftRightSpeeds(double left, double right) {
    this.left.set(left);
    this.right.set(right);
  }

  void reset() {
    this.leftEnc.reset();
    this.rightEnc.reset();
  }
}
```

This is all you need to get going.

Here's what the set up looks like. Inside of robotInit, you can put this:

```java
class Robot extends TimedRobot {
  public void robotInit() {
    DriveTrain dt = new DriveTrain();
    EasyPathConfig config = new EasyPathConfig(
        dt::setLeftRightSpeeds, // function to set left/right speeds
        // function to give EasyPath the length driven
        () -> PathUtil.defaultLengthDrivenEstimator(dt.leftEnc::getInches, dt.rightEnc::getInches),
        dt.gyro::getAngle, // function to give EasyPath the heading of your robot
        dt::reset, // function to reset your encoders to 0
        0.07 // kP value for P loop
    );
    
    EasyPath.configure(config);
  }
}
```

It's as easy as that - around five lines of code.

To follow a path, simply run a `FollowPath` command in autonomous or in your command group:

```java
class Robot extends TimedRobot {
  public void autonomousInit() {
    
    // This drives 36 inches in a straight line, driving at 25% speed the first 50% of the path,
    // and 75% speed in the remainder.
    m_autonomousCommand = new FollowPath(
        PathUtil.createStraightPath(36.0, x -> {
          if (x < 0.5) return 0.25;
          else return 0.75;
        })
    );
    
    // Or another example below:
    
    // The path command is generated from the pathing website. It can be copy-pasted into the code.
    // This is the path that 340 used to score on the right side of the switch, starting from the
    // center of the alliance wall in 2018.
    // This also uses a more aggressively tuned speed function that starts mid-speed, goes fast for
    // most of it, and slows down in the last 25% of it.
    m_autonomousCommand = new FollowPath(
        new Path(t -> 
          /* {"start":{"x":0,"y":100},"mid1":{"x":46,"y":101},"mid2":{"x":51,"y":156},"end":{"x":112,"y":155}} */
          (3 + 324 * t + -330 * Math.pow(t, 2))/ (138 + -246 * t + 291 * Math.pow(t, 2)), 129.0
        ), x -> {
          if (x < 0.15) return 0.6;
          else if (x < 0.75) return 0.8;
          else return 0.25;
        });
  }
}
```

For more examples, be sure to check out any of these sources:

* [340's 2018 autos](https://github.com/Greater-Rochester-Robotics/PowerUp2018-340/tree/master/Team340PowerUp2018/src/org/usfirst/frc/team340/robot/commands/auto)
* [5254's 2018 autos](https://github.com/FRC5254/FRC-5254---BakPak/tree/master/src/org/usfirst/frc/team5254/robot/autocommands)
* [2791's 2018 autos](https://github.com/Team2791/Robot_2018/tree/Climber/src/org/usfirst/frc/team2791/robot/commands/auto/GrrPaths)
