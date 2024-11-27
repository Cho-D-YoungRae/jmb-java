package book.jmb.chapter23.runningmedian;


class RNG {

    private final int a;
    private final int b;
    private int seed;

    public RNG(int a, int b) {
        this.seed = 1983;
        this.a = a;
        this.b = b;
    }

    public int next() {
        int ret = seed;
        seed = (int) ((seed * (long) a + b) % 20090711);
        return ret;
    }
}