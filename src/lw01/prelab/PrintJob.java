package lw01.prelab;

public abstract class PrintJob implements Chargeable {
    private final String id;
    private final int pages;

    protected PrintJob(String id, int pages) {
        if (pages <= 0) {
            throw new IllegalArgumentException("pages harus positif");
        }
        this.id = id;
        this.pages = pages;
    }

    public String getId() {
        return id;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public abstract int calculateCharge();

    // Overload: ditulis SEKALI di sini, tidak diulang di anak
    public int calculateCharge(int copies) {
        if (copies <= 0) {
            throw new IllegalArgumentException("copies harus positif");
        }
        return copies * calculateCharge();
    }

    public String label() {
        return "Print";
    }

    // Jangan di-override di subclass
    public String summary() {
        return id + " | " + label() + " | " + calculateCharge();
    }
}