public class Dice {

    public Dice(){

    }

    private int Roll() {
        int number = (int) ((Math.random() * 6) + 1);
        return number;
    }

}
