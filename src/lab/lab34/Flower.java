package lab.lab34;

public class Flower {

    String flowerType;
    int noticeability;


    void creatFlower(FlowerType flowerType, int namber, int growth) {
        if (growth > 15) {
            try {
                throw new SizeExeption("не бывает токих больших цветков");
            } catch (SizeExeption sizeExeption) {
                sizeExeption.printStackTrace();
                return;
            }
        }

        this.noticeability = namber + growth;

        switch (flowerType) {
            case ROSE:
                this.flowerType = "роза";
                break;
            case ARCHIDEA:
                this.flowerType = "архидея";
                break;
            case TULIP:
                this.flowerType = "тюльпан";
                break;
            default:
                System.out.println("нет токого цветка");
        }


    }

}
