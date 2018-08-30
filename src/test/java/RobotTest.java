import org.junit.Test;

class Motor {

  Motor(int id) {
  }

  void set(double val) {
  }
}

class Encoder {

  Encoder(int id) {
  }

  double getInches() {
    return 1.0;
  }

  void reset() {
  }
}

class Gyro {

  Gyro() {
  }

  double getAngle() {
    return 180.0;
  }
}

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

public class RobotTest {

  @Test
  public void testRobotSetup() {
    DriveTrain dt = new DriveTrain();
    EasyPathConfig config = new EasyPathConfig(
        dt::setLeftRightSpeeds,
        () -> PathUtil.defaultLengthDrivenEstimator(dt.leftEnc::getInches, dt.rightEnc::getInches),
        dt.gyro::getAngle,
        dt::reset,
        0.07
    );

    config.setSwapDrivingDirection(true);
    config.setSwapTurningDirection(true);

    EasyPath.configure(config);

    new FollowPath(PathUtil.createStraightPath(5.0), x -> {
      if (x < 0.5) {
        return 0.2;
      } else {
        return 1.0;
      }
    });

    new FollowPath(
        new Path(
            x -> x * 2, 64.0
        ), 5
    );
  }
}
