import java.time.LocalDate;

public class Game {
    private String naam;
    private int releaseJaar;
    private double nieuwprijs;

    public Game(String nm, int rJ, double nwpr) {
        this.naam = nm;
        this.releaseJaar = rJ;
        this.nieuwprijs = nwpr;
    }

    public String getNaam() {
        return naam;
    }

    public double huidigeWaarde() {
        int jarenOud = LocalDate.now().getYear() - releaseJaar;
        double afschrijving = Math.pow(0.7, jarenOud);
        return nieuwprijs * afschrijving;
    }


    @Override
    public boolean equals(Object andereObject) {
        if (this == andereObject) return true;
        if (andereObject == null || getClass() != andereObject.getClass()) return false;
        Game game = (Game) andereObject;
        return releaseJaar == game.releaseJaar &&
                Double.compare(game.nieuwprijs, nieuwprijs) == 0 &&
                naam.equals(game.naam);
    }
    @Override
    public String toString() {
        int currentYear = LocalDate.now().getYear();
        int yearsOld = currentYear - releaseJaar;
        double currentPrice = huidigeWaarde();

        return String.format(
                "%s, uitgegeven in %d; nieuwprijs: €%.2f nu voor: €%.2f",
                naam,
                releaseJaar,
                nieuwprijs,
                currentPrice
        );
    }

}
