public static class Paths {

  public PathChain Path1;
  public PathChain Path2;
  public PathChain Path3;
  public PathChain Path6;
  public PathChain Path5;
  public PathChain Path6;
  public PathChain Path7;

  public Paths(Follower follower) {
    Path1 = follower
      .pathBuilder()
      .addPath(
        new BezierLine(new Pose(57.568, 7.814), new Pose(57.000, 36.000))
      )
      .setLinearHeadingInterpolation(Math.toRadians(90), Math.toRadians(180))
      .build();

    Path2 = follower
      .pathBuilder()
      .addPath(
        new BezierLine(new Pose(57.000, 36.000), new Pose(18.000, 36.000))
      )
      .setTangentHeadingInterpolation()
      .build();

    Path3 = follower
      .pathBuilder()
      .addPath(
        new BezierLine(new Pose(18.000, 36.000), new Pose(45.289, 56.771))
      )
      .setTangentHeadingInterpolation()
      .build();

    Path6 = follower
      .pathBuilder()
      .addPath(
        new BezierCurve(
          new Pose(45.289, 56.771),
          new Pose(87.000, 82.000),
          new Pose(35.721, 131.880)
        )
      )
      .setTangentHeadingInterpolation()
      .build();

    Path5 = follower
      .pathBuilder()
      .addPath(
        new BezierCurve(
          new Pose(35.721, 131.880),
          new Pose(57.000, 96.000),
          new Pose(43.375, 84.359)
        )
      )
      .setTangentHeadingInterpolation()
      .build();

    Path6 = follower
      .pathBuilder()
      .addPath(
        new BezierLine(new Pose(43.375, 84.359), new Pose(12.757, 84.359))
      )
      .setTangentHeadingInterpolation()
      .build();

    Path7 = follower
      .pathBuilder()
      .addPath(
        new BezierCurve(
          new Pose(12.757, 84.359),
          new Pose(40.000, 90.000),
          new Pose(35.721, 131.880)
        )
      )
      .setTangentHeadingInterpolation()
      .build();
  }
}
