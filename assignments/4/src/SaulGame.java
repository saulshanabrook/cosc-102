import org.apache.commons.math3.geometry.euclidean.twod.Vector2D;

import java.awt.event.KeyEvent;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class SaulGame {

    //////////////
    // DEFAULTS //
    //////////////

    // Game window should be longer than higher:   x < y
    // (more effectively using space)
    private static final int DEFAULT_NUM_ROWS = 20;
    private static final int DEFAULT_NUM_COLUMNS = 40;

    private static final int DEFAULT_USER_ROW = DEFAULT_NUM_ROWS / 2;
    private static final int DEFAULT_USER_COLUMN = DEFAULT_NUM_COLUMNS / 2;

    // in ms
    private long WAIT_TIME_BETWEEN_MOVES = 10000;

    private static final boolean DEMO = false;


    ////////////
    // STATIC //
    ////////////
    private static final Random random = new Random();
    private static enum Icon {USER, GET, LASER_HORIZONTAL, LASER_VERTICAL, TAIL}
    // http://stackoverflow.com/a/5197741/907060
    private static Map<Icon, String> iconsToFiles = new HashMap<Icon, String>() {{
        put(Icon.USER, "user.gif");
        put(Icon.GET, "get.gif");
        put(Icon.LASER_HORIZONTAL, "laserHorizontal.gif");
        put(Icon.LASER_VERTICAL, "laserVertical.gif");
        put(Icon.TAIL, "tail.png");
        put(null, null);
    }};

    private static Map<Icon, Double> iconProbability = new HashMap<Icon, Double>() {{
        put(Icon.GET, 0.1);
    }};


    ///////////
    // STATE //
    ///////////
    // renders  objects
    private Grid grid;

    // what about my tail?
    private ArrayDeque<Vector2DInteger> tailPositions = new ArrayDeque<Vector2DInteger>();
    private int maxTailLength = 0;
    // which direction i am facing
    private Vector2DInteger laserDirection = new Vector2DInteger(1, 0);
    // where i am
    private Vector2DInteger userPosition;

    //should i shift to the right?
    private long timeSinceMove = 0;
    private boolean paused = false;

    // if i hit my own tail, set this to true
    private boolean lost = false;

    public MattGame dg;


    /////////////////
    // CONSTRUCTOR //
    /////////////////

    public SaulGame() {
        this(DEFAULT_NUM_ROWS, DEFAULT_NUM_COLUMNS, DEFAULT_USER_ROW, DEFAULT_USER_COLUMN);
    }

    public SaulGame(int rows, int columns, int userRow, int userColumn) {
        grid = new Grid(rows, columns);

        userPosition = new Vector2DInteger(userColumn, userRow);

        timeSinceMove = System.currentTimeMillis();
        updateTitle();
        setIcon(Icon.USER, userPosition);
    }


    public void play() {

        while (!isGameOver()) {
            handleKeyPress();
            // make everything move in closer every 3 pause times
            if (timeSinceMove >= WAIT_TIME_BETWEEN_MOVES) {
                scrollLeft();
                populateRightEdge();
                timeSinceMove = 0;
            }
            updateTitle();
            renderLaser();
            if (!paused) {
                timeSinceMove++;
            }
        }
    }

    private void moveUser(Vector2DInteger changePosition) {

        /////////////////
        // Laser beam! //
        /////////////////
        changeLaserDirection(changePosition);


        ////////////////
        // Validation //
        ////////////////

        // make sure it can actually move where it wants to
        Vector2DInteger newUserPosition = userPosition.add(changePosition);

        if (!grid.isValid(vectorToLocation(newUserPosition)) || (Icon.TAIL.equals(getIcon(newUserPosition)))) {
            return;
        }


        //////////
        // Tail //
        //////////

        // set the new place to the user
        setIcon(Icon.USER, newUserPosition);

        // move the tail up to the new position
        setIcon(Icon.TAIL, userPosition);
        tailPositions.add(userPosition);

        // set the new place to the user location
        userPosition = newUserPosition;

        // if we now have too long a tail, pop off the end one
        while (tailPositions.size() > maxTailLength) {
            Vector2DInteger endOfTail = tailPositions.pop();
            setIcon(null, endOfTail);
        }


    }

    private void changeLaserDirection(Vector2DInteger newDirection) {
        // remove all the laser beam spots that were previous extending from the shark
        for (Vector2DInteger removeLaserPosition = userPosition.add(laserDirection); grid.isValid(vectorToLocation(removeLaserPosition)); removeLaserPosition = removeLaserPosition.add(laserDirection)) {
            setIcon(null, removeLaserPosition);
        }
        laserDirection = newDirection;
        renderLaser();

    }

    private void renderLaser() {
        for (Vector2DInteger addLaserPosition = userPosition.add(laserDirection); grid.isValid(vectorToLocation(addLaserPosition)); addLaserPosition = addLaserPosition.add(laserDirection)) {
            handleCollision(getIcon(addLaserPosition));
            setIcon(currentLaserIcon(), addLaserPosition);
        }
    }

    public void handleKeyPress() {
        int key = grid.checkLastKeyPressed();

        //use Java constant names for key presses
        //http://docs.oracle.com/javase/7/docs/api/constant-values.html#java.awt.event.KeyEvent.VK_DOWN
        switch (key){
            case KeyEvent.VK_UP:
                moveUser(new Vector2DInteger(0, -1));
                break;
            case KeyEvent.VK_DOWN:
                moveUser(new Vector2DInteger(0, 1));
                break;
            case KeyEvent.VK_LEFT:
                moveUser(new Vector2DInteger(-1, 0));
                break;
            case KeyEvent.VK_RIGHT:
                moveUser(new Vector2DInteger(1, 0));
                break;
            case KeyEvent.VK_Q:
                System.exit(0);
                break;
            case KeyEvent.VK_P:
                paused = !paused;
                break;
            case KeyEvent.VK_COMMA:
                WAIT_TIME_BETWEEN_MOVES /= 2;
                break;
            case KeyEvent.VK_PERIOD:
                WAIT_TIME_BETWEEN_MOVES *= 2;
                break;
        }

    }


    public void populateRightEdge() {
        // last column
        int column = grid.getNumCols() - 1;
        for (int row=0; row < grid.getNumRows(); row++) {
            Icon newIcon = getRandomByProbability(iconProbability);
            setIcon(newIcon, new Location(row, column));
        }

    }

    public void scrollLeft() {
        // first lets clean up the first column
        // we need to do this cause sometimes a null wont come and wipe it out, if it is next to the tail
        for (int row=0; row < grid.getNumRows(); row++) {
            Location originalLocation = new Location(row, 0);
            Icon replacingIcon = getIcon(originalLocation);
            if (replacingIcon == Icon.GET) {
                setIcon(null, originalLocation);
            }
        }

        // then go through the rest of the spots and mvoe them over
        for (int column=1; column < grid.getNumCols(); column++){
            for (int row=0; row < grid.getNumRows(); row++) {
                Vector2DInteger originalPosition = new Vector2DInteger(column, row);
                Icon icon = getIcon(originalPosition);

                // only move the good stuff (and nulls)!
                if (!Icon.GET.equals(icon)) {
                    continue;
                }

                setIcon(null, originalPosition);


                Vector2DInteger newPosition = originalPosition.add(-1, 0);
                Icon replacingIcon = getIcon(newPosition);

                if (replacingIcon != null) {
                    switch (replacingIcon) {
                        case USER:
                            handleCollision(icon);
                            break;
                        case LASER_HORIZONTAL:
                            handleCollision(icon);
                            break;
                        case LASER_VERTICAL:
                            handleCollision(icon);
                            break;
                        case TAIL:
                            handleTailCollision(icon);
                            break;
                    }
                } else {
                    setIcon(icon, newPosition);
                }

            }
        }
    }


    public void handleCollision(Icon icon) {
        if (icon == null) {
            return;
        }
        switch (icon){
            case GET:
                maxTailLength++;
                break;
            case TAIL:
                lost = true;
                break;
        }
    }

    public void handleTailCollision(Icon icon) {
        if (Icon.GET.equals(icon)) {
            if (maxTailLength > 0) {
                maxTailLength--;
            }
        }

    }

    public int getScore() {
        return tailPositions.size();
    }

    public void updateTitle() {
        String title;
        if (lost) {
            title = "You hit your tail! You lose. Your tail was " + getScore() + " long.";
        } else {
            title = "Your tail is " + getScore() + " long!";
        }
        grid.setTitle(title);
    }

    public boolean isGameOver() {

        return lost;
    }

    public static void main(String[] args) {
        if (DEMO) {
            MattGame game = new MattGame(10, 20, 0);
            //or default constructor   (4 by 10)
            //MattGame game = new MattGame();
            game.play();
        } else {
            SaulGame saulGame = new SaulGame();
            saulGame.play();
        }
    }

    private Icon currentLaserIcon() {
        return (laserDirection.getX() != 0) ? Icon.LASER_HORIZONTAL : Icon.LASER_VERTICAL;
    }

    private void setIcon(Icon icon, Vector2DInteger position){
        setIcon(icon, vectorToLocation(position));
    }

    private void setIcon(Icon icon, Location location){
        grid.setImage(location, getFileForIcon(icon));
    }


    private Icon getIcon(Vector2DInteger position){
        return getIcon(vectorToLocation(position));
    }

    private Icon getIcon(Location location){
        return getIconForFile(grid.getImage(location));
    }

    private Location vectorToLocation(Vector2DInteger vector) {
        return new Location(vector.getY(), vector.getX());
    }


    private String getFileForIcon(Icon icon) {
        return iconsToFiles.get(icon);
    }

    private Icon getIconForFile(String fileName) {
        return getKeyByValue(iconsToFiles, fileName);
    }


    // http://stackoverflow.com/a/2904266/907060
    private static <T, E> T getKeyByValue(Map<T, E> map, E value) {
        for (Map.Entry<T, E> entry : map.entrySet()) {
            E checkingValue = entry.getValue();
            if (checkingValue == value || value.equals(checkingValue)) {
                return entry.getKey();
            }
        }
        return null;
    }

    private static <T> T getRandomByProbability(Map<T, Double> probMap) {

        for (Map.Entry<T, Double> entry : probMap.entrySet()) {

            if (random.nextDouble() <= entry.getValue()) {
                return entry.getKey();
            }
        }
        return null;
    }

}


// I couldn't find a (real math) vector class in java
// that was could be used for `Integer`s, so I made
// my own, based off of `Vector2D`
class Vector2DInteger {
    public Vector2D doubleVector;

    public Vector2DInteger(int x, int y) {
        doubleVector = new Vector2D((double)x, (double)y);
    }

    public Vector2DInteger(Vector2D vector) {
        this((int)vector.getX(), (int)vector.getY());
    }

    public Vector2DInteger add(Vector2DInteger vector) {
        return new Vector2DInteger(this.doubleVector.add(vector.doubleVector));
    }

    public Vector2DInteger add(int dx, int dy) {
        return add(new Vector2DInteger(dx, dy));
    }

    public int getX() {
        return (int) doubleVector.getX();
    }

    public int getY() {
        return (int) doubleVector.getY();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vector2DInteger that = (Vector2DInteger) o;

        return !(doubleVector != null ? !doubleVector.equals(that.doubleVector) : that.doubleVector != null);

    }

    @Override
    public int hashCode() {
        return doubleVector != null ? doubleVector.hashCode() : 0;
    }
}

