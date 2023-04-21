package NewDayNewGame.Core;

public class BitMask {
    private byte[] mask;

    public BitMask(byte [] mask) {
        this.mask = mask.clone();
    }

    public BitMask(int [] bits) {
        mask = new byte[bits.length / 8];
        for (int i = 0; i < bits.length; ++i) {
            mask[i / 8] += (bits[i] << (i % 8));
        }
    }

    public int getBit(Integer index) {
        return (mask[index / 8] & (1 << (index % 8))) == 0 ? 0 : 1;
    }
}
