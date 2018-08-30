import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class EasyPathConfig {

  private BiConsumer<Double, Double> setLeftRightDriveSpeedFunction;
  private Supplier<Double> getInchesTraveledFunction;
  private Supplier<Double> getCurrentAngleFunction;
  private Runnable shiftDriveTrainFunction;
  private Runnable resetEncodersFunction;
  private double kP;

  /**
   * A configuration object that represents your drive train's controls. If you are unsure of how to
   * reference functions as a Java parameter/argument, please see the example code that is linked in
   * the readme of the project on GitHub.
   *
   * @param setLeftRightDriveSpeedFunction the function that sets the left and right drive train
   * speeds; takes in two doubles for arguments and returns nothing
   * @param getInchesTraveledFunction the function that returns a double that represents the total
   * distance traveled by the robot in inches; if you do not have one, you can use a function that
   * simply calls {@link PathUtil#defaultLengthDrivenEstimator PathUtil.defaultLengthDrivenEstimator}
   * with the two values of your encoders.
   * @param getCurrentAngleFunction the function that returns a double that represents the current
   * angle of the robot, from 0 degrees to 360 degrees.
   * @param resetEncodersFunction the function that takes no arguments and returns nothing, and
   * resets your encoders and/or your gyro (if necessary)
   * @param kP the kP value used to tune the control loop. Please see the tuning section in the
   * readme for more details on tuning.
   */
  public EasyPathConfig(
      BiConsumer<Double, Double> setLeftRightDriveSpeedFunction,
      Supplier<Double> getInchesTraveledFunction,
      Supplier<Double> getCurrentAngleFunction,
      Runnable resetEncodersFunction,
      double kP
  ) {
    this(setLeftRightDriveSpeedFunction, getInchesTraveledFunction, getCurrentAngleFunction,
        resetEncodersFunction, () -> {
        }, kP);
  }

  /**
   * A configuration object that represents your drive train's controls. If you are unsure of how to
   * reference functions as a Java parameter/argument, please see the example code that is linked in
   * the readme of the project on GitHub.
   *
   * @param setLeftRightDriveSpeedFunction the function that sets the left and right drive train
   * speeds; takes in two doubles for arguments and returns nothing
   * @param getInchesTraveledFunction the function that returns a double that represents the total
   * distance traveled by the robot in inches; if you do not have one, you can use a function that
   * simply calls {@link PathUtil#defaultLengthDrivenEstimator PathUtil.defaultLengthDrivenEstimator}
   * with the two values of your encoders.
   * @param getCurrentAngleFunction the function that returns a double that represents the current
   * angle of the robot, from 0 degrees to 360 degrees.
   * @param shiftDriveTrainFunction the function that takes no arguments and returns nothing, and
   * shifts your drive train into high gear. Use the other constructor if you do not shift.
   * @param resetEncodersFunction the function that takes no arguments and returns nothing, and
   * resets your encoders and/or your gyro (if necessary)
   * @param kP the kP value used to tune the control loop. Please see the tuning section in the
   * readme for more details on tuning.
   */
  public EasyPathConfig(
      BiConsumer<Double, Double> setLeftRightDriveSpeedFunction,
      Supplier<Double> getInchesTraveledFunction,
      Supplier<Double> getCurrentAngleFunction,
      Runnable shiftDriveTrainFunction,
      Runnable resetEncodersFunction,
      double kP
  ) {
    this.setLeftRightDriveSpeedFunction = setLeftRightDriveSpeedFunction;
    this.getInchesTraveledFunction = getInchesTraveledFunction;
    this.getCurrentAngleFunction = getCurrentAngleFunction;
    this.shiftDriveTrainFunction = shiftDriveTrainFunction;
    this.resetEncodersFunction = resetEncodersFunction;
    this.kP = kP;
  }

  /**
   * @return the function that sets the left and right drive train speeds
   */
  public BiConsumer<Double, Double> getSetLeftRightDriveSpeedFunction() {
    return setLeftRightDriveSpeedFunction;
  }

  /**
   * @return the function that returns the total distance traveled, in inches
   */
  public Supplier<Double> getGetInchesTraveledFunction() {
    return getInchesTraveledFunction;
  }

  /**
   * @return the function that returns the current angle of the robot
   */
  public Supplier<Double> getGetCurrentAngleFunction() {
    return getCurrentAngleFunction;
  }

  /**
   * @return the function that resets the encoders of the robot
   */
  public Runnable getResetEncodersFunction() {
    return resetEncodersFunction;
  }

  /**
   * @return the kP value used in the control loop for arcing along the path
   */
  public double getkP() {
    return kP;
  }

  /**
   * @return the function that shifts the drive train of your robot into high gear
   */
  public Runnable getShiftDriveTrainFunction() {
    return shiftDriveTrainFunction;
  }
}
