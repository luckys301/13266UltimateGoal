package org.firstinspires.ftc.teamcode.opmode.teleop;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.arcrobotics.ftclib.command.button.GamepadButton;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.commands.drive.teleop.mec.DefaultMecDriveCommand;
import org.firstinspires.ftc.teamcode.commands.drive.trajectory.TurnToCommand;
import org.firstinspires.ftc.teamcode.subsystems.Claw;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDrive;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.pivot.Pivot;
import org.firstinspires.ftc.teamcode.subsystems.slide.Slide;
import org.firstinspires.ftc.teamcode.util.CycleTracker.CycleTracker;
import org.firstinspires.ftc.teamcode.util.NebulaConstants;
import org.firstinspires.ftc.teamcode.util.teleop.GamepadTrigger;
import org.firstinspires.ftc.teamcode.util.teleop.MatchOpMode;

@TeleOp
public class TeleOpMain extends MatchOpMode {
    // Gamepad
    private GamepadEx driverGamepad;
    private GamepadEx operatorGamepad;

    private final CycleTracker cycleTracker = new CycleTracker(telemetry);

    // Subsystems
    private Pivot pivot;
    private Claw claw;
    private MecDriveSubsystem mecDriveSubsystem;
    private Slide slide;

    @Override

    public void robotInit() {
        driverGamepad = new GamepadEx(gamepad1);
//          driverGamepad.gamepad.type = Gamepad.Type.SONY_PS4;
//          driverGamepad.gamepad.runRumbleEffect(Gamepad.RumbleEffect.Step);
        operatorGamepad = new GamepadEx(gamepad2);

        pivot = new Pivot(telemetry, hardwareMap, true);
        claw = new Claw(telemetry, hardwareMap, true);
        mecDriveSubsystem = new MecDriveSubsystem(new MecDrive(hardwareMap, telemetry, true), telemetry, hardwareMap);
        mecDriveSubsystem.init();
        slide = new Slide(telemetry, hardwareMap, true);
//        pivot.resetOffset();
        pivot.moveInitializationPosition();
    }

    @Override
    public void configureButtons() {
        mecDriveSubsystem.setDefaultCommand(
            new DefaultMecDriveCommand(mecDriveSubsystem, driverGamepad, true));


//        cycleTracker.resetTimer();
//        //Can Rumble Gamepads; If need to Rumble both Gamepads, might need to use queueEffect - Doesn't WORK
//        driverGamepad.gamepad.rumble(1,1, 100);

        (new GamepadButton(driverGamepad, GamepadKeys.Button.A))
            .whenPressed(new InstantCommand(mecDriveSubsystem::reInitializeIMU));
        new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_DOWN)//TODO:Test
            .toggleWhenPressed(new InstantCommand(()-> NebulaConstants.Gamepad.overrideSafety = true),
                new InstantCommand(()-> NebulaConstants.Gamepad.overrideSafety = false));

        //Ways to use buttons
//        new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER)
//            .and(new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)).negate()
//            .whileActiveContinuous(new SequentialCommandGroup());
//        new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER)
//            .and(new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.LEFT_TRIGGER))
//            .whileActiveContinuous(new SequentialCommandGroup());

        new GamepadButton(driverGamepad, GamepadKeys.Button.DPAD_DOWN)//To Test
            .whileHeld(new TurnToCommand(mecDriveSubsystem, 180)) //KInda Works as it turns and stuff
//            .whenPressed(new TurnToTeleop(mecDriveSubsystem,180, telemetry)) //doesn't work
            .whenReleased(new InstantCommand(()->mecDriveSubsystem.stop()));

        (new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.LEFT_TRIGGER))//To Test
            .whileActiveOnce(new InstantCommand(()-> cycleTracker.trackCycle(1)));
//            .whileActiveOnce(new InstantCommand(()->        driverGamepad.gamepad.rumble(1,1, -1)));
        (new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.RIGHT_TRIGGER))
            .whileActiveOnce(new InstantCommand(()-> cycleTracker.trackCycle(2)));
//        new GamepadTrigger(driverGamepad, GamepadKeys.Trigger.LEFT_TRIGGER)
//            .and(new GamepadButton(operatorGamepad, GamepadKeys.Button.LEFT_BUMPER))
//            .whileActiveContinuous(new SlowMecDriveCommand(mecDriveSubsystem, driverGamepad, true));
//        new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER)
//            .and(new GamepadTrigger(operatorGamepad, GamepadKeys.Trigger.LEFT_TRIGGER))
//            .whileActiveContinuous(new SlowMecDriveCommand(mecDriveSubsystem, driverGamepad, true))
//        new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER).or()
//            .whileHeld(new SlowMecDriveCommand(mecDriveSubsystem, driverGamepad, true));
//        new GamepadButton(driverGamepad, GamepadKeys.Button.LEFT_BUMPER).and().negate()
//            .whileHeld(new SlowMecDriveCommand(mecDriveSubsystem, driverGamepad, true));

        (new GamepadButton(operatorGamepad, GamepadKeys.Button.A))  //TODO:TEST
            .toggleWhenPressed(
                new InstantCommand(()-> NebulaConstants.Gamepad.overrideSafety = false),
                new InstantCommand(()-> NebulaConstants.Gamepad.overrideSafety = true));



        mecDriveSubsystem.setDefaultCommand(new DefaultMecDriveCommand(mecDriveSubsystem, driverGamepad, true));
    }

    @Override
    public void matchLoop() {
        telemetry.addData("Override Safety", NebulaConstants.Gamepad.overrideSafety);//TODO:TEST
//        telemetry.addLine("dfijfhjdehfhsejf");
        cycleTracker.cyclePeriodic();
    }
    @Override
    public void disabledPeriodic() { }
    @Override
    public void matchStart() { }
    @Override
    public void robotPeriodic(){
    }
}
