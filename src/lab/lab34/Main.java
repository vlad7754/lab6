package lab.lab34;

public class Main {
    public static void main(String[] args) {

        Cosmonaut.SuperCosmonaut superCosmonaut = new Cosmonaut.SuperCosmonaut();
        superCosmonaut.flyPlanet(true, 5);
        if (superCosmonaut.startLocation.rocket.flown) {
            Lord lord = new Lord() {
                @Override
                public void cry() {
                    String name = "Спрутс";
                    System.out.println(name + "  усилить отряды полиции в тех городах, вблизи   которых можно было ожидать появления космонавтов.");
                }

                @Override
                public void findOutTheLocationOfThePolice() {
                    superCosmonaut.planets.police = true;
                    System.out.println("Полиция на местах ");
                }
            };
            lord.cry();
            lord.findOutTheLocationOfThePolice();
        }

        superCosmonaut.findLandingPlace();
        superCosmonaut.actionsOnThePlanet(5);
    }
}
