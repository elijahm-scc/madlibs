package main.java.com.stockcharts.interns.elijahm;

public class Story {
    private int id, adjectiveCount, nounCount, verbCount;
    private String text;

    public Story(int id, int adjectiveCount, int nounCount, int verbCount, String storyText) {
        this.adjectiveCount = adjectiveCount;
        this.nounCount = nounCount;
        this.verbCount = verbCount;
        this.text = storyText;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int getAdjectiveCount() {
        return adjectiveCount;
    }

    public int getNounCount() {
        return nounCount;
    }

    public int getVerbCount() {
        return verbCount;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return "Story{" +
                "adjectiveCount=" + adjectiveCount +
                ", nounCount=" + nounCount +
                ", verbCount=" + verbCount +
                ", storyText='" + text + '\'' +
                '}';
    }
}
