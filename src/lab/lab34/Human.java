package lab.lab34;

public class Human implements HumanAction {
    String name;
    String age;

    boolean sits;
    boolean leans;

    public Human(){}

   public Human(String name, String age){
       this.name = name;
       this.age = age;
   }


    @Override
    public void cry(String phrase) {//кричать
        System.out.println(phrase);
    }

    @Override
    public void laughter() { //смех
        System.out.println("Ха Ха Ха");
    }

    @Override
    public void sit(boolean sit) { //присесть
        this.sits = sit;
        if (sit)
            System.out.println("присел на корточки");

    }

    @Override
    public void lean(boolean lean) { //наклониться
        this.leans = lean;
        System.out.println("пригнулся");
    }

    @Override
    public void marvel(Trees trees) {//удивиться
        System.out.println(trees.description);
    }



    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age='" + age + '\'' +
                ", sits=" + sits +
                ", leans=" + leans +
                '}';
    }
}

