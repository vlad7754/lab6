package lab.lab34;

public class Trees {
    String treesType;
    String description;


    void creatTrees(TreesType treesType, int growth) {
        if (growth > 30) {
            try {
                throw new SizeExeption("не бывает токих больших деревьев");
            } catch (SizeExeption sizeExeption) {
                sizeExeption.printStackTrace();
                return;
            }
        }




        switch (treesType) {
            case OAK:
                this.treesType = "дуб";
                this.description = "Он такой же раскидистый, как и наш, с таким же растрескавшимся,\n" +
                        "морщинистым стволом, с такими же узловатыми веточками, с такими же по форме листочками,\n" +
                        "но очень крошечными; такие же крошки желуди растут на нем.\n" +
                        "Вообразите, что такой дубочек растет у вас в комнате на окне в цветочном горшке вместо комнатного цветка";
                break;
            case BIRCH:
                this.treesType = "береза";
                this.description = ".....";
                break;
            case WILLOW:
                this.treesType = "ива";
                this.description = ".....";
                break;
            case PINE:
                this.treesType = "сщстна";
                this.description = "....";
                break;
            default:
                System.out.println("нет токого цветка");
        }


    }

}
