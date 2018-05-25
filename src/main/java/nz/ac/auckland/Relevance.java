package nz.ac.auckland;

public enum Relevance {
        NOT_RELEVANT (0.00),
        WEAK_RELEVANT (0.25),
        RELEVANT (0.50),
        VERY_RELEVANT (0.75),
        THE_SAME (1.00);

        double nominal_value;

        Relevance(double nominal_value) {
            this.nominal_value = nominal_value;
        }
}
