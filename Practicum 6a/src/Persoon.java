import java.util.ArrayList;
import java.time.LocalDate;

class Persoon {
    private String naam;
    private double budget;

    private ArrayList<Game> mijnGames;

    public Persoon(String nm, double bud) {
        this.naam = nm;
        this.budget = bud;
        this.mijnGames = new ArrayList<>();
    }

    public double getBudget() {
        return budget;
    }

    public boolean koop(Game g) {
        if (g == null || budget < g.huidigeWaarde()) {
            return false;
        }

        budget -= g.huidigeWaarde();
        mijnGames.add(g);
        return true;
    }

    public boolean verkoop(Game game, Persoon koper) {
        if (game == null || koper == null || !koper.koop(game)) {
            return false;
        }

        double verkoopPrijs = game.huidigeWaarde();
        budget += verkoopPrijs; // Add the current value to the seller's budget
        mijnGames.remove(game);

        // Create a new game with the original nieuwprijs for the buyer
        Game verkochtGame = new Game(game.getNaam(), LocalDate.now().getYear(), game.huidigeWaarde());
        koper.mijnGames.add(verkochtGame);

        return true;
    }


    @Override
    public String toString() {
        StringBuilder result = new StringBuilder(String.format("%s heeft een budget van €%.2f", naam, budget));
        if (!mijnGames.isEmpty()) {
            result.append(" en bezit de volgende games:");
            for (Game game : mijnGames) {
                result.append("\n").append(game);
            }
        }
        return result.toString();
    }
}
