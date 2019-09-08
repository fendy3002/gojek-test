package parking_lot.app.exception;

public class SlotIsEmptyException extends Exception
{
    public SlotIsEmptyException(int slot){
        this.slot = slot;
    }
    public int slot;
}