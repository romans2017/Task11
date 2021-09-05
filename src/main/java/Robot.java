public class Robot {

    private String previousStep = "left";
    private String previousCatchingHand = "left";
    private String previousReleasedHand = "left";
    private int numberCatchingHands = 2;

    synchronized public void step(String move) throws InterruptedException {
        if (previousStep.equals(move)) {
            wait();
        }
        Thread.sleep(500);
        System.out.println("Step " + move);
        previousStep = move;
        notifyAll();
    }

    synchronized public void handCatch(String move) throws InterruptedException {
        while (previousCatchingHand.equals(move) || numberCatchingHands == 2) {
            wait();
        }
        Thread.sleep(500);
        System.out.println("Hand " + move + " is caught");
        previousCatchingHand = move;
        numberCatchingHands++;
        notifyAll();
    }

    synchronized public void handRelease(String move) throws InterruptedException {
        while (previousReleasedHand.equals(move) || numberCatchingHands == 1) {
            wait();
        }
        Thread.sleep(500);
        System.out.println("Hand " + move + " is released");
        previousReleasedHand = move;
        numberCatchingHands--;
        notifyAll();
    }

    public static void robotStep() throws InterruptedException {
        Robot robot = new Robot();
        Runnable rightStep = () -> {
            while (true) {
                try {
                    robot.step("right");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable leftStep = () -> {
            while (true) {
                try {
                    robot.step("left");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread threadLeftStep = new Thread(leftStep);
        Thread threadRightStep = new Thread(rightStep);

        threadLeftStep.start();
        threadRightStep.start();

        threadLeftStep.join();
        threadRightStep.join();
    }

    public static void robotClimb() throws InterruptedException {
        Robot robot = new Robot();
        Runnable rightHandCatch = () -> {
            while (true) {
                try {
                    robot.handCatch("right");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable leftHandCatch = () -> {
            while (true) {
                try {
                    robot.handCatch("left");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable rightHandRelease = () -> {
            while (true) {
                try {
                    robot.handRelease("right");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Runnable leftHandRelease = () -> {
            while (true) {
                try {
                    robot.handRelease("left");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Thread threadLeftHandCatch = new Thread(leftHandCatch);
        Thread threadRightHandCatch = new Thread(rightHandCatch);
        Thread threadLeftHandRelease = new Thread(leftHandRelease);
        Thread threadRightHandRelease = new Thread(rightHandRelease);

        threadLeftHandCatch.start();
        threadRightHandCatch.start();
        threadLeftHandRelease.start();
        threadRightHandRelease.start();

        threadLeftHandCatch.join();
        threadRightHandCatch.join();
        threadLeftHandRelease.join();
        threadRightHandRelease.join();

    }

    public static void main(String[] args) throws InterruptedException {
        robotStep();
        robotClimb();
    }
}
