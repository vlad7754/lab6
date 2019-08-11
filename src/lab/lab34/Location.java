package lab.lab34;

public abstract class Location {
    int namber;
    Cosmonaut[] cosmonauts;

    void creatCosmonaut(int namber) {
        if (namber > 0) {
            Cosmonaut[] cosmonauts = new Cosmonaut[namber];
            Cosmonaut superCosmonaut = new Cosmonaut.SuperCosmonaut();
            cosmonauts[0] = superCosmonaut;

            for (int nambers = this.namber - 1; nambers > 0; nambers--) {
                Cosmonaut cosmonaut = new Cosmonaut();
                cosmonauts[nambers] = cosmonaut;
            }

            this.cosmonauts = cosmonauts;
        }
    }



}
