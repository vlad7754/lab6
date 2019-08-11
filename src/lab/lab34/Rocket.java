package lab.lab34;


import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@XmlType(name = "Rocket")
@XmlRootElement

public class Rocket extends Location implements Comparable<Rocket> , Serializable {
   public String name ;
 public    Integer fuel;//топлев
    transient   boolean door = false;
    transient   boolean Engine = false;
    transient   boolean flown = false;
    transient   boolean readyToQuit = false;
    transient   boolean standingAtTheStation;
 transient    OffsetDateTime date = OffsetDateTime.now();
public Rocket(){}
    public Rocket(String name, Integer fuel, boolean standingAtTheStation){
        this.name = name;
        this.fuel = fuel;
        this.standingAtTheStation = standingAtTheStation;


    }




 transient    Radar radar = new Radar();




    void quitRocket() {
        System.out.println("Космонавты один за другим вышли из кабины");
    }

    void landed() {

        System.out.println(Cosmonaut.SuperCosmonaut.name + ":повернул рокету хвостом вниз");
        this.fuel = this.fuel - 5;
        if (fuel < 0) {
            try {
                throw new FuelException("Нехватило топлева, рокета "+ name +" упала при посадки");
            } catch (FuelException e) {
                e.printStackTrace();
                return;
            }
        }
        System.out.println(Cosmonaut.SuperCosmonaut.name + ":начал спуск");
        this.fuel = this.fuel - 5;
        if (fuel <= 0) {
            try {
                throw new FuelException("Нехватило топлева, рокета "+name+" упала при посадки");
            } catch (FuelException e) {
                e.printStackTrace();
                return;
            }
        }


        System.out.println("Ракета встретилась с твердой почвой.\n" + Cosmonaut.SuperCosmonaut.name + ": выключил прибор невесомости.");
        System.out.println("Рокета "+name+" успешно приземлилась");
        readyToQuit = true;
    }

    void flyToPlanet(int rowsOfTrees, int izbas) {
        System.out.println("Пролитает над избушками");
        this.fuel = this.fuel - izbas;
        if (fuel <= 0) {
            try {
                throw new FuelException("Нехватило топлева, рокета упала на избушки");
            } catch (FuelException e) {
                e.printStackTrace();
                return;
            }
        }
        System.out.println("пролитает над лесом");
        this.fuel = this.fuel - rowsOfTrees;
        if (fuel <= 0) {
            try {
                throw new FuelException("Нехватило топлева, рокета упала в лесу");
            } catch (FuelException e) {
                e.printStackTrace();
                return;
            }
        }
        System.out.println(Cosmonaut.SuperCosmonaut.name + ": увидил палянку на которую можно сесть");
        this.fuel = this.fuel - 5;
        if (fuel <= 0) {
            try {
                throw new FuelException("Нехватило топлева, рокета упала перед полянкой");
            } catch (FuelException e) {
                e.printStackTrace();
                return;
            }
        }
        System.out.println("Зделали круг около полянки");
        this.readyToQuit = true;
    }

    void openDoor(boolean door) {//открыть дверь
        this.door = door;
    }

    void includeEngine() {//включить двигатель
        if (fuel > 0) this.Engine = true;
    }

    void flyPlanet(int distances) {//полететь на планету
        System.out.println("подлители к " + Cosmonaut.SuperCosmonaut.planet);
        this.fuel = this.fuel - (distances / 2);
        this.flown = true;
    }

    class Radar {
        boolean placeFind = false;

        void findPlace() {
            System.out.println("Ишу место для посадки");
            this.placeFind = true;
        }

        void showThePlace() {
            if (placeFind) System.out.println("Найдена полянка");
        }
    }

   @Override

    public int compareTo(Rocket o) {
        int result = this.name.compareTo(o.name);


        if (result == 0) {
            result = this.fuel.compareTo(o.fuel);
        }
        return result;
    }


    @Override
    public String toString() {
        return "Rocket{" +
                "name='" + name + '\'' +
                ", fuel=" + fuel + ", standingAtTheStation="
                + standingAtTheStation+", date="+date+
                '}';
    }
}
