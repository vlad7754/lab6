package lab.lab34;

import static lab.lab34.PlanetsEnam.*;


public class Cosmonaut extends Human implements CosmonautAction {


    @Override
    public void joinHands(Cosmonaut[] cosmonauts) {//взяться за руки
        if (cosmonauts.length > 1) {
            System.out.println("взялись за руки");
        }
    }

    @Override
    public void buried(boolean grass) {//утопать
        if (grass) System.out.println("Ноги путешественников утопают в зеленой травке");
    }

    void pleasure(boolean freshAir, boolean spacesuit) {
        if (freshAir == true && spacesuit == false)
            System.out.println("Так приятно после долгого перерыва снова очутиться на свежем воздухе");
    }

    @Override
    public void seePlant(Flower flower) {//увидить цветочик
        if (flower.noticeability > 10)
            System.out.println("заметил цветочик " + flower.flowerType +
                    "\nцветочки были удивительно крошечные, низкорослые, совсем не такие как на далекой Земле");
    }

    @Override
    public void considerPlant() {//разгледеть цветочик
        sit(true);
        System.out.println("разглядеть цветочек");
        sit(false);


    }

    void lookAround(boolean around, Trees trees) {//оглядеться
        if (around) {
            System.out.println("Огляделся по сторонам");
            System.out.println("заметил " + trees.treesType);

        }
    }

    static class SuperCosmonaut extends Cosmonaut {


        static String name = "Знайка";
        static String planet;
        int distances;
        Planet planets = new Planet();
        boolean flown = false;
        boolean survive = false;
        StartLocation startLocation = new StartLocation();

        void flyPlanet(Boolean kay, int namber) {
            final int[] distances = new int[1];
            final String[] planet = new String[1];

            this.startLocation.creatCosmonaut(namber);


            if (kay == true) {
                this.startLocation.rocket.openDoor(true);
                this.startLocation.rocket.includeEngine();
            } else if (kay == false) {
                throw new TrueException("Космонафты потеряли ключи от ракеты и не куда не смогли полетели");
            }

            class Plan {
                void buildPath(PlanetsEnam planetsEnam) {
                    switch (planetsEnam) {
                        case JUPITER:
                            planet[0] = "юпитер";
                            distances[0] = 1000;
                            break;
                        case MARS:
                            planet[0] = "марс";
                            distances[0] = 200;
                            break;
                        case MOON:
                            planet[0] = "луна";
                            distances[0] = 100;
                            break;
                        default:
                            System.out.println("Не могу найти токую плонету, возможно токой плонеты нет в базе или у вас проблемы подключения к галоктической сети");


                    }
                }

            }

            Plan plan = new Plan();
            plan.buildPath(MOON);
            this.distances = distances[0];
            this.planet = planet[0];


            while (!this.flown) {
                planets.wotch(this.flown);
                while (!this.flown) {
                    if (startLocation.rocket.fuel > 0 && startLocation.rocket.Engine) {
                        this.startLocation.rocket.flyPlanet(this.distances);
                    }
                    this.flown = startLocation.rocket.flown;
                }
                if (startLocation.rocket.fuel > 0)
                    planets.wotch(this.flown);
            }

            if (startLocation.rocket.fuel <= 0) {
                try {
                    throw new FuelException("Нехватило топлева долететь до указоной планеты");
                } catch (FuelException e) {
                    e.printStackTrace();
                    startLocation.rocket.flown = false;
                }
            }


        }


        void findLandingPlace() {
            if (startLocation.rocket.fuel>0) {

                if (!planets.police && startLocation.rocket.fuel > 0) {
                    System.out.println("Космонаваты приземлились в городе");
                } else {
                    if (startLocation.rocket.radar.placeFind == false) {
                        startLocation.rocket.radar.findPlace();
                    }
                    startLocation.rocket.radar.showThePlace();

                    startLocation.rocket.flyToPlanet(10, 10);
                    if (startLocation.rocket.fuel > 0) {
                        startLocation.rocket.landed();}
                        if (startLocation.rocket.readyToQuit && startLocation.rocket.fuel > 0) {
                            startLocation.rocket.quitRocket();
                            this.survive = true;

                    }

                }
            }

        }


        void actionsOnThePlanet(int namber) {
            if (survive) {
                startLocation.creatCosmonaut(namber);
                joinHands(startLocation.cosmonauts);
                cry("Ура Ура Ура");
                pleasure(true, false);
                buried(true);
                Flower flower = new Flower();

                flower.creatFlower(FlowerType.ROSE, 10, 5);
                seePlant(flower);
                considerPlant();
                laughter();
                Trees trees = new Trees();
                trees.creatTrees(TreesType.OAK, 13);
                lookAround(true, trees);
                marvel(trees);

            }
        }


    }
}



