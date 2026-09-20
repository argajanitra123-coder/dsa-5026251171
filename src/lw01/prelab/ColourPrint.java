public class ColourPrint extends PrintJob {

    public ColourPrint(String id, int pages) {
        super(id, pages);
    }

    @Override
    public int calculateCharge() {
        int pages = getPages();

        if (pages <= 10) {
            return pages * 1500 + 2000;
        }

        return (10 * 1500) + ((pages - 10) * 1000) + 2000;
    }

    @Override
    public String label() {
        return "Colour";
    }
}