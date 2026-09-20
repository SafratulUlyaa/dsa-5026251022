package lw01.prelab;

public class ColourPrint extends PrintJob {

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int firstPages = Math.min(getPages(), 10);  // maksimal 10 halaman @1500
        int extraPages = getPages() - firstPages;   // sisanya @1000
        return (firstPages * 1500) + (extraPages * 1000) + 2000;
    }

    @Override
    public String label() {
        return "Colour";
    }
}