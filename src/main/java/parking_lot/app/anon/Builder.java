package parking_lot.app.anon;

// used to handle lack of lambda expression
// for < java 8
public abstract class Builder<T,V>
{
    public abstract T build(V input);
}