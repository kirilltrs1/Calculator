public class RomanNum {
    enum RomanNumeral {
        I(1), IV(4), V(5), IX(9), X(10), XL(40), L(50), XC(90), C(100);

        final int weight;

        RomanNumeral(int weight) {
            this.weight = weight;
        }
    }
}
