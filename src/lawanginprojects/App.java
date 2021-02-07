package lawanginprojects;

public class App {
    public static void main(String[] args) {
        Grid myGrid = new Grid();

        myGrid.setPosition('X', 2, 0);
        myGrid.setPosition('X', 1, 1);
        myGrid.setPosition('X', 0, 2);

        System.out.println(myGrid.checkForWinner());

        System.out.println(myGrid.toString());
        System.out.println(myGrid.getRow(0));
        System.out.println(myGrid.getRow(1));
    }
}
