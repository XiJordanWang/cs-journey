public class Dessert {

    public Dessert(int flavor, int price) {
        this.flavor = flavor;
        this.price = price;
        numDeserts++;
    }

    int flavor;
    int price;

    static int numDeserts;

    public void printDessert() {
        System.out.println(flavor + " " + price + " " + numDeserts);
    }

    public static void main(String[] args) {
        System.out.println("I love dessert!");
    }
}
