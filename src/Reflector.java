public class Reflector extends Rotor {

    @Override
    public void initialise(String type) {
        Integer[] ReflectorI = {24, 17, 20, 7, 16, 18, 11, 3, 15, 23, 13, 6, 14, 10, 12, 8, 4, 1, 5, 25, 2, 22, 21, 9, 0, 19};
        Integer[] ReflectorII = {5, 21, 15, 9, 8, 0, 14, 24, 4, 3, 17, 25, 23, 22, 6, 2, 19, 10, 20, 16, 18, 1, 13, 12, 7, 11};

        super.initialise(type);

        switch (type) {
            case "ReflectorI":
                mapping = ReflectorI;
                break;
            case "ReflectorII":
                mapping = ReflectorII;
                break;
            default:
                System.err.println("Error: " + type + " is not a valid type of Reflector");
        }
    }

    @Override
    public Integer substitute(int input) {
        return mapping[input];
    }
}
