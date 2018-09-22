# Creating Paths

Pathing site: https://tervay.github.io/pathmaker/

----

### Field-relative or robot-relative?

EasyPath is **robot-relative**. This means you MUST provide EasyPath a way to reset your gyro's
heading to zero. Either that, or you must manually reset the gyro heading to zero after every path
that you run.

----

Following a straight path is easy:

```java
class Robot extends TimedRobot {
  public void autonomousInit() {
    
    // This drives 36 inches in a straight line, driving at 25% speed the first 50% of the path,
    // and 75% speed in the remainder.
    // x is the percentage completion of the path, between 0 and 1.
    m_autonomousCommand = new FollowPath(
        PathUtil.createStraightPath(36.0), x -> {
          if (x < 0.5) return 0.25;
          else return 0.75;
        });
  }
}
```

---

Driving a path in reverse is also easy, simply pass it a negative speed:

```java
class Robot extends TimedRobot {
  public void autonomousInit() {
    m_autonomousCommand = new FollowPath(
        PathUtil.createStraightPath(36.0), x -> {
          if (x < 0.5) return -0.25;
          else return -0.75;
        });
  }
}
```