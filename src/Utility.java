import java.util.Random;

public class Utility {
    DataManager dm;

    public Utility(DataManager dm) {
        this.dm = dm;
    }


    int[][] getMatrix(int Width, int Height) {

        int[][] neighbors = new int[3][3];
        int licznik = 0;
        int licznik2 = 0;

        for (int wi = Width - 1; wi <= Width + 1; wi++) {
            for (int hi = Height - 1; hi <= Height + 1; hi++) {
                neighbors[licznik][licznik2] = dm.pixelLast[wi][hi];
                licznik2++;
            }
            licznik2 = 0;
            licznik++;
        }
        return neighbors;
    }

    int[] getNeighbors(int Width, int Height) {

        int[] neighbors = new int[8];
        int licznik = 0;

        try{
            for (int wi = Width - 1; wi <= Width + 1; wi++) {
                for (int hi = Height - 1; hi <= Height + 1; hi++) {

                    if (wi == Width && hi == Height) {
                    } else {
                        neighbors[licznik] = dm.pixelLast[wi][hi];
                        licznik++;
                    }
                }
            }
        }
        catch (Exception e) {
            if(Width==0&&Height==0)
            {
                neighbors[0]=dm.pixelLast[dm.width-1][dm.height-1];
                neighbors[1]=dm.pixelLast[dm.width-1][Height];
                neighbors[2]=dm.pixelLast[dm.width-1][Height+1];
                neighbors[3]=dm.pixelLast[Width][dm.height-1];
                neighbors[4]=dm.pixelLast[Width][Height+1];
                neighbors[5]=dm.pixelLast[Width+1][dm.height-1];
                neighbors[6]=dm.pixelLast[Width+1][Height];
                neighbors[7]=dm.pixelLast[Width+1][Height+1];
            }

            else if(Width==dm.width-1&&Height==0)
            {
                neighbors[0]=dm.pixelLast[Width-1][dm.height-1];
                neighbors[1]=dm.pixelLast[Width-1][Height];
                neighbors[2]=dm.pixelLast[Width-1][Height+1];
                neighbors[3]=dm.pixelLast[Width][dm.height-1];
                neighbors[4]=dm.pixelLast[Width][Height+1];
                neighbors[5]=dm.pixelLast[0][dm.height-1];
                neighbors[6]=dm.pixelLast[0][Height];
                neighbors[7]=dm.pixelLast[0][Height+1];
            }

            else if(Width==0&&Height==dm.height-1)
            {
                neighbors[0]=dm.pixelLast[dm.width-1][Height-1];
                neighbors[1]=dm.pixelLast[dm.width-1][Height];
                neighbors[2]=dm.pixelLast[dm.width-1][0];
                neighbors[3]=dm.pixelLast[Width][Height-1];
                neighbors[4]=dm.pixelLast[Width][0];
                neighbors[5]=dm.pixelLast[Width+1][Height-1];
                neighbors[6]=dm.pixelLast[Width+1][Height];
                neighbors[7]=dm.pixelLast[Width+1][0];
            }

            else if(Width==dm.width-1&&Height== dm.height-1)
            {
                neighbors[0]=dm.pixelLast[Width-1][Height-1];
                neighbors[1]=dm.pixelLast[Width-1][Height];
                neighbors[2]=dm.pixelLast[Width-1][0];
                neighbors[3]=dm.pixelLast[Width][Height-1];
                neighbors[4]=dm.pixelLast[Width][0];
                neighbors[5]=dm.pixelLast[0][Height-1];
                neighbors[6]=dm.pixelLast[0][Height];
                neighbors[7]=dm.pixelLast[0][0];
            }


            else if(Height== 0)
            {
                neighbors[0]=dm.pixelLast[Width-1][dm.height-1];
                neighbors[1]=dm.pixelLast[Width-1][Height];
                neighbors[2]=dm.pixelLast[Width-1][Height+1];
                neighbors[3]=dm.pixelLast[Width][dm.height-1];
                neighbors[4]=dm.pixelLast[Width][Height+1];
                neighbors[5]=dm.pixelLast[Width+1][dm.height-1];
                neighbors[6]=dm.pixelLast[Width+1][Height];
                neighbors[7]=dm.pixelLast[Width+1][Height+1];
            }

            else if(Height== dm.height-1)
            {
                neighbors[0]=dm.pixelLast[Width-1][Height-1];
                neighbors[1]=dm.pixelLast[Width-1][Height];
                neighbors[2]=dm.pixelLast[Width-1][0];
                neighbors[3]=dm.pixelLast[Width][Height-1];
                neighbors[4]=dm.pixelLast[Width][0];
                neighbors[5]=dm.pixelLast[Width+1][Height-1];
                neighbors[6]=dm.pixelLast[Width+1][Height];
                neighbors[7]=dm.pixelLast[Width+1][0];
            }

            else if(Width== 0)
            {
                neighbors[0]=dm.pixelLast[dm.width-1][Height-1];
                neighbors[1]=dm.pixelLast[dm.width-1][Height];
                neighbors[2]=dm.pixelLast[dm.width-1][Height+1];
                neighbors[3]=dm.pixelLast[Width][Height-1];
                neighbors[4]=dm.pixelLast[Width][Height+1];
                neighbors[5]=dm.pixelLast[Width+1][Height-1];
                neighbors[6]=dm.pixelLast[Width+1][Height];
                neighbors[7]=dm.pixelLast[Width+1][Height+1];
            }

            else if(Width== dm.width-1)
            {
                neighbors[0]=dm.pixelLast[Width-1][Height-1];
                neighbors[1]=dm.pixelLast[Width-1][Height];
                neighbors[2]=dm.pixelLast[Width-1][Height+1];
                neighbors[3]=dm.pixelLast[Width][Height-1];
                neighbors[4]=dm.pixelLast[Width][Height+1];
                neighbors[5]=dm.pixelLast[0][Height-1];
                neighbors[6]=dm.pixelLast[0][Height];
                neighbors[7]=dm.pixelLast[0][Height+1];
            }
        }

        finally{ return neighbors;}
    }

    void animateGameOfLife() {


        for (int wi = 0; wi < dm.width; wi++) {
            for (int hi = 0; hi < dm.height; hi++) {
                dm.pixelLast[wi][hi] = dm.img.getRaster().getSample(wi, hi, 0);
            }
        }

        do {
            for (int wi = 0; wi < dm.width ; wi++) {
                for (int hi = 0; hi < dm.height ; hi++) {


                    int[] localNeighbor = getNeighbors(wi, hi);

                    int localMiddle = dm.img.getRaster().getSample(wi, hi, 0);

                    int licznik = 0;


                    if (localMiddle == 255) {
                        for (int j : localNeighbor) {
                            if (j == 0) {
                                licznik++;
                            }
                        }
                        if (licznik == 3) {
                            dm.img.getRaster().setSample(wi, hi, 0, 0);
                        }
                    }

                    else if (localMiddle == 0) {
                        for (int j : localNeighbor) {
                            if (j == 0) {
                                licznik++;
                            }
                        }

                        if (licznik == 3 || licznik == 2) { }
                        else   { dm.img.getRaster().setSample(wi, hi, 0, 255); }
                    }

                }
            }
            for (int x = 0; x < dm.width; x++) {
                for (int z = 0; z < dm.height; z++) {
                    dm.pixelLast[x][z] = dm.img.getRaster().getSample(x, z, 0);
                }
            }
        } while (true);
    }


    void Initial() {
        for (int wi = 0; wi < dm.width; wi++) {
            for (int hi = 0; hi < dm.height; hi++) {
                Random random=new Random();
                int randomInteger = random.nextInt(250);

                if (randomInteger < 150) {
                    dm.img.getRaster().setSample(wi, hi, 0, 0);
                } else {
                    dm.img.getRaster().setSample(wi, hi, 0, 255);
                }
            }
        }

    }
}