package binaryarithmeticcoding;
import java.util.ArrayList;
import java.util.Scanner;

public class BinaryArithmeticCoding {


    public static void main(String[] args) {

        ArrayList<Characters> sy = new ArrayList<Characters>();
        ArrayList<Character> scaling = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Character (Write . to end) : ");
        String data = sc.next();
        while (data.charAt(0) != '.') {
            System.out.print("It's Propability is: ");
            Double f = sc.nextDouble();
            sy.add(new Characters(data.charAt(0), f));
            System.out.print("Enter Character (Write . to end) : ");
            data = sc.next();

        }
        //set high low range

        Double low = 0.0;
        for (int i = 0; i < sy.size(); i++) {
            Double high = sy.get(i).getFreq() + low;
            sy.get(i).setLowerfreq(low);
            sy.get(i).setUpperfreq(high);
            low = high;
        }


        // the smallest prop for the char
        Double min = sy.get(0).getFreq();
        int k = 1;
        for (int i = 1; i < sy.size(); i++) {
            if (min > sy.get(i).getFreq()) {
                min = sy.get(i).getFreq();
            }
        }

        while ((1 / Math.pow(2, k)) > min) {
            k++;
        }

        Double lower = 0.0;
        Double upper = 1.0;
        Double range;
        System.out.print("Enter String :");
        Scanner a = new Scanner(System.in);
        String input = a.nextLine();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            for (int j = 0; j < sy.size(); j++) {

                if (sy.get(j).getCharacter() == c) {
                    range = upper - lower;
                    upper = lower + range * sy.get(j).getUpperfreq();
                    lower = lower + range * sy.get(j).getLowerfreq();

                    // if 0.5 not in the middle
                    while (!(lower < 0.5 && 0.5 < upper)) {
                        while (upper < 0.5) //scaling E1
                        {
                            upper = upper * 2;
                            lower = lower * 2;
                            scaling.add('0');
                        }
                        while (lower > 0.5) //scaling E2
                        {
                            lower = (lower - 0.5) * 2;
                            upper = (upper - 0.5) * 2;
                            scaling.add('1');
                        }
                    }


                    System.out.println(c);
                    System.out.println(String.format("%.4f",lower));
                    System.out.println(String.format("%.4f",upper));
                    break;

                }
            }

        }
        upper = 1.0;
        lower = 0.0;
        range = 0.0;

        String Compressed_Code = "";
        for (int i = 0; i < scaling.size(); i++) {
            Compressed_Code += scaling.get(i);
        }
        Compressed_Code += "1";
        for (int i = 1; i < k; i++) {
            Compressed_Code += "0";
        }
        System.out.println("Encode is : " + Compressed_Code);

        int x = 0;
        int y = k;

        double code = Integer.parseInt(Compressed_Code.substring(x, y), 2);
        code /= Math.pow(2, k);
        System.out.println(code);
        System.out.println("Original size is : " + input.length()*8);


        System.out.println("Decompression");

        for (int i = 0; i < input.length(); i++) {

            for (int j = 0; j < sy.size(); j++) {

                if (code > sy.get(j).getLowerfreq() && code < sy.get(j).getUpperfreq()) {
                    System.out.println(sy.get(j).getCharacter());
                    range = upper - lower;
                    upper = lower + range * sy.get(j).getUpperfreq();
                    lower = lower + range * sy.get(j).getLowerfreq();


                    while (!(lower < 0.5 && 0.5 < upper)) {
                        while (upper < 0.5) //scaling E1
                        {
                            upper = upper * 2;
                            lower = lower * 2;
                            // to move 1 bit
                            x++;
                            y++;
                            code = Integer.parseInt(Compressed_Code.substring(x, y), 2);
                            code /= Math.pow(2, k);

                        }
                        while (lower > 0.5) //scaling E2
                        {
                            lower = (lower - 0.5) * 2;
                            upper = (upper - 0.5) * 2;
                            // to move 1 bit
                            x++;
                            y++;
                            code = Integer.parseInt(Compressed_Code.substring(x, y), 2);
                            code /= Math.pow(2, k);

                        }
                    }

                    // to get the code
                    code = (code - lower) / (upper - lower);

                    break;
                }

            }
        }

    }

}