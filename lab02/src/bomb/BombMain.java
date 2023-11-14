package bomb;

import common.IntList;

public class BombMain {
    public static void main(String[] args) {
        int phase = 2;
        if (args.length > 0) {
            phase = Integer.parseInt(args[0]);
        }
        // TODO: Find the correct passwords to each phase using debugging techniques
        Bomb b = new Bomb();
        if (phase >= 0) {
            b.phase0("39291226");
        }
        if (phase >= 1) {
            IntList lst = IntList.of(0,9,3,0,8);
            b.phase1(lst); // Figure this out too
        }
        if (phase >= 2) {
            String strlst = "0";
            int i = 1;
            while(i < 1400){
                if (i == 1337){
                    strlst += " " + "-81201430";
                    i+=1;
                }
                else {
                    strlst += " " + Integer.toString(i);
                    i += 1;
                }
            }
            b.phase2(strlst);
        }
    }
}
