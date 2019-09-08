package parking_lot.app.anon;

// used to handle lack of lambda expression
// for < java 8
public abstract class Validator<T,V>
{
    public abstract boolean compare(T subject, V term);
}