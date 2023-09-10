package org.firstinspires.ftc.teamcode.opmode.auto;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.command.ParallelCommandGroup;
import com.arcrobotics.ftclib.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.commands.drive.trajectory.sequence.DisplacementCommand;
import org.firstinspires.ftc.teamcode.commands.drive.trajectory.sequence.TrajectorySequenceContainerFollowCommand;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDrive;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDriveConstants;
import org.firstinspires.ftc.teamcode.subsystems.drive.mecDrive.MecDriveSubsystem;
import org.firstinspires.ftc.teamcode.subsystems.pipelines.aprilTagPipeline.TagVision;
import org.firstinspires.ftc.teamcode.util.PoseStorage;
import org.firstinspires.ftc.teamcode.util.teleop.MatchOpMode;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Back;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Forward;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Pose2dContainer;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.SetReversed;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.SplineTo;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.StrafeLeft;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.StrafeRight;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceConstraints;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.TrajectorySequenceContainer;
import org.firstinspires.ftc.teamcode.util.trajectorysequence.container.Turn;
//438247

//@Autonomous
public class ComplexAutoImplementation extends MatchOpMode {
    // Subsystems
    private MecDriveSubsystem mecDriveSubsystem;
    private TagVision tagVision;
    @Config
    public static class RightRegionalAutoConstants {

        public static Speed speed;
        public static class Speed {
            public static double baseVel = MecDriveConstants.MAX_VEL; // value
            public static double baseAccel = MecDriveConstants.MAX_ACCEL; // value
            public static double turnVel = MecDriveConstants.MAX_VEL; // value
            public static double turnAccel = MecDriveConstants.MAX_ANG_ACCEL; // value
            public static double slowVel = baseVel * 0.8; // value
            public static double slowAccel = baseAccel * 0.8; // value
            public static double slowTurnVel = turnVel * 0.8; // value
            public static double slowTurnAccel = turnAccel * 0.8; // value
            static TrajectorySequenceConstraints getPickupConstraints() {
                return new TrajectorySequenceConstraints(
                        (s, a, b, c) -> {
                            if (s > 18) {
                                return baseVel * 0.4;
                            } else {
                                return baseVel;
                            }

                        },
                        (s, a, b, c) -> baseAccel,
                        turnVel,
                        turnAccel
                );
            }
            static TrajectorySequenceConstraints getDropConstraints() {
                return new TrajectorySequenceConstraints(
                        (s, a, b, c) -> {
                            if (s > 20) {
                                return baseVel * 0.65;
                            } else {
                                return baseVel;
                            }

                        },
                        (s, a, b, c) -> baseAccel,
                        turnVel,
                        turnAccel
                );
            }
            static TrajectorySequenceConstraints getPreLoadDropConstraints() {
                return new TrajectorySequenceConstraints(
                        (s, a, b, c) -> {
                            if (s > 48) {
                                return baseVel * 0.6;
                            } else {
                                return baseVel;
                            }

                        },
                        (s, a, b, c) -> baseAccel,
                        turnVel,
                        turnAccel
                );
            }
            static TrajectorySequenceConstraints getParkConstraint() {
                return new TrajectorySequenceConstraints(
                        (s, a, b, c) -> {
                            return baseVel * 0.6;
                        },
                        (s, a, b, c) -> baseAccel,
                        turnVel,
                        turnAccel
                );
            }
            static TrajectorySequenceConstraints getBaseConstraints() {
                return new TrajectorySequenceConstraints(baseVel, baseAccel, turnVel, turnAccel);
            }
            static TrajectorySequenceConstraints getSlowConstraints() {
                return new TrajectorySequenceConstraints(slowVel, slowAccel, slowTurnVel, slowTurnAccel);
            }
        }

        public static Path path;
        public static class Path {
            public static PreLoad apreLoad;
            public static class PreLoad {
                public static Pose2dContainer startPose = new Pose2dContainer(-34.9, -65, 90);
                public static Forward a = new Forward(34);
                public static SplineTo b = new SplineTo(-28, -4, 50);
                static TrajectorySequenceContainer preload = new TrajectorySequenceContainer(Speed::getPreLoadDropConstraints, a, b);
            }

            public static Cycle1Pickup bcycle1Pickup;
            public static class Cycle1Pickup {
                public static SetReversed a = new SetReversed(true);
                public static SplineTo b = new SplineTo(-48, -9.2,180);
                public static Back c = new Back(13.75);
                static TrajectorySequenceContainer cycle1Pickup = new TrajectorySequenceContainer(Speed::getPickupConstraints, a, b, c);
            }

            public static Cycle1Drop ccycle1Drop;
            public static class Cycle1Drop {
                public static SetReversed a = new SetReversed(true);
                public static Forward b = new Forward(16);
                public static SplineTo c = new SplineTo(-30.9, -13.2, -45);
                static TrajectorySequenceContainer cycle1Drop = new TrajectorySequenceContainer(Speed::getDropConstraints, a, b, c);
            }

            public static Cycle2Pickup dcycle2Pickup;
            public static class Cycle2Pickup {
                public static SetReversed a = new SetReversed(true);
                public static SplineTo b = new SplineTo(-49.1, -9.1, 180);
                public static Back c = new Back(12.2);
                static TrajectorySequenceContainer cycle2Pickup = new TrajectorySequenceContainer(Speed::getPickupConstraints, a, b, c);
            }

            public static Cycle2Drop ecycle2Drop;
            public static class Cycle2Drop {
                public static SetReversed a = new SetReversed(true);
                public static Forward b = new Forward(16);
                public static SplineTo c = new SplineTo(-31, -13.3, -45);
                static TrajectorySequenceContainer cycle2Drop = new TrajectorySequenceContainer(Speed::getDropConstraints, a, b, c);
            }

            public static Cycle3Pickup fcycle3Pickup;
            public static class Cycle3Pickup {
                public static SetReversed a = new SetReversed(true);
                public static SplineTo b = new SplineTo(-51.5, -9., 180);
                public static Back c = new Back(11.5);
                static TrajectorySequenceContainer cycle3Pickup = new TrajectorySequenceContainer(Speed::getPickupConstraints, a, b, c);
            }

            public static Cycle3Drop gcycle3Drop;
            public static class Cycle3Drop {
                public static SetReversed a = new SetReversed(true);
                public static Forward b = new Forward(16);
                public static SplineTo c = new SplineTo(-30.9, -12.8, -45);
                static TrajectorySequenceContainer cycle3Drop = new TrajectorySequenceContainer(Speed::getDropConstraints, a, b, c);
            }

//            public static Cycle4Pickup hcycle4Pickup;
//            public static class Cycle4Pickup {
//                public static SetReversed a = new SetReversed(true);
//                public static SplineTo b = new SplineTo(51, -8.83, 0);
//                public static Back c = new Back(14.45);
//                static TrajectorySequenceContainer cycle4Pickup = new TrajectorySequenceContainer(Speed::getPickupConstraints, a, b, c);
//            }
//
//            public static Cycle4Drop icycle4Drop;
//            public static class Cycle4Drop {
//                public static SetReversed a = new SetReversed(true);
//                public static Forward b = new Forward(16);
//                public static SplineTo c = new SplineTo(28, -3.5, 132);
//                static TrajectorySequenceContainer cycle4Drop = new TrajectorySequenceContainer(Speed::getDropConstraints, a, b, c);
////                public static SetReversed a = new SetReversed(true);
////                public static Forward b = new Forward(16);
////                public static SplineTo c = new SplineTo(35.88, -14.34, -135);
////                static TrajectorySequenceContainer cycle4Drop = new TrajectorySequenceContainer(Speed::getDropConstraints, a, b, c);
//            }

            public static Park jpark;
            public static class Park {
                public static double leftX = -7;
                public static double midX = -36;
                public static double rightX = -64;
                public static double y = -15.2;
                public static double heading = 90;
                public static double endHeading = 0;
                public enum AutoPosition {
                    lEFT,
                    MID,
                    RIGHT
                }
                public static AutoPosition autoPosition = AutoPosition.MID;
                static TrajectorySequenceContainer getPark(double x) {
                    switch (autoPosition) {
                        case lEFT:
                            return new TrajectorySequenceContainer(
                                    Speed::getParkConstraint,
                                    new Back(4),
                                    new Turn(-45),

                                    new Back(2),
                                    new StrafeRight(30),
                                    new Forward(7)
                            );

                        case MID:
                            return new TrajectorySequenceContainer(
                                    Speed::getParkConstraint,
                                    new Back(6),
                                    new Turn(-45),
                                    new Forward(7)
                            );
                        default:
                        case RIGHT:
                            return new TrajectorySequenceContainer(
                                    Speed::getParkConstraint,
                                    new Back(6),
                                    new Turn(-45),

                                    new Back(2),
                                    new StrafeLeft(29),
                                    new Forward(7)
                            );
                    }
                }
            }
        }
    }



    @Override
    public void robotInit() {
        mecDriveSubsystem = new MecDriveSubsystem(new MecDrive(hardwareMap, telemetry, true), telemetry, hardwareMap);
        mecDriveSubsystem.init();
        tagVision = new TagVision(hardwareMap, telemetry);
        while (!isStarted() && !isStopRequested()) {
            tagVision.updateTagOfInterest();
            tagVision.tagToTelemetry();
            telemetry.update();
        }
        this.matchStart();
    }

    public void matchStart() {
        double finalX = 0;
        switch (tagVision.getTag()) {
            case 1:
//                finalX = RightRegionalAutoConstants.Path.Park.leftX;
                RightRegionalAutoConstants.Path.Park.autoPosition = RightRegionalAutoConstants.Path.Park.AutoPosition.lEFT;
                break;
            case 2:
//                finalX = RightRegionalAutoConstants.Path.Park.midX;
                RightRegionalAutoConstants.Path.Park.autoPosition = RightRegionalAutoConstants.Path.Park.AutoPosition.MID;
                break;
            case 3:
//                finalX = RightRegionalAutoConstants.Path.Park.rightX;
                RightRegionalAutoConstants.Path.Park.autoPosition = RightRegionalAutoConstants.Path.Park.AutoPosition.RIGHT;
                break;
        }
        mecDriveSubsystem.setPoseEstimate(RightRegionalAutoConstants.Path.PreLoad.startPose.getPose());
        PoseStorage.trajectoryPose = RightRegionalAutoConstants.Path.PreLoad.startPose.getPose();
        schedule(
                new SequentialCommandGroup(
                        /* Preload */
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(mecDriveSubsystem, RightRegionalAutoConstants.Path.PreLoad.preload)
                        ),
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(mecDriveSubsystem, RightRegionalAutoConstants.Path.Cycle1Pickup.cycle1Pickup,
                                        new DisplacementCommand(30, new SequentialCommandGroup()))
                        ),
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(mecDriveSubsystem, RightRegionalAutoConstants.Path.Cycle1Drop.cycle1Drop)
                        ),
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(mecDriveSubsystem, RightRegionalAutoConstants.Path.Cycle2Pickup.cycle2Pickup,
                                        new DisplacementCommand(28, new SequentialCommandGroup()))
                        ),
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(mecDriveSubsystem, RightRegionalAutoConstants.Path.Cycle2Drop.cycle2Drop)
                        ),
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(mecDriveSubsystem, RightRegionalAutoConstants.Path.Cycle3Pickup.cycle3Pickup,
                                        new DisplacementCommand(28, new SequentialCommandGroup()))
                        ),
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(mecDriveSubsystem, RightRegionalAutoConstants.Path.Cycle3Drop.cycle3Drop)
                        ),
                        new ParallelCommandGroup(
                                new TrajectorySequenceContainerFollowCommand(mecDriveSubsystem, RightRegionalAutoConstants.Path.Park.getPark(finalX))
                        ),
                        new SequentialCommandGroup(
                        ),

                        /* Save Pose and end opmode*/
                        run(() -> PoseStorage.currentPose = mecDriveSubsystem.getPoseEstimate()),
                        run(this::stop)
                )
        );
    }
}