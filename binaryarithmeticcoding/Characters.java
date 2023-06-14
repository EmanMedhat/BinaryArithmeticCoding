package binaryarithmeticcoding;


public class Characters {
    
    char ch;
    Double freq, lowerfreq, upperfreq;
    static int sizee = 0;

    public Characters(char c, Double d) {
        ch = c;
        freq = d;
    }

   
    public char getCharacter() {
        return ch;
    }

    public void setCharacter(char symbol) {
        this.ch = symbol;
        sizee++;
    }

    public Double getFreq() {
        return freq;
    }

    public void setFreq(Double freq) {
        this.freq = freq;
    }

    public Double getLowerfreq() {
        return lowerfreq;
    }

    public void setLowerfreq(Double lowerfreq) {
        this.lowerfreq = lowerfreq;
    }

    public Double getUpperfreq() {
        return upperfreq;
    }

    public void setUpperfreq(Double upperfreq) {
        this.upperfreq = upperfreq;
    }

    public int size() {
        return sizee;
    }


}
    

