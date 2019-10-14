package ru.mail.polis.ads.NastyPill.part3;

public class Pair<T, T1> {

    T first;
    T1 second;

    public Pair (T first, T1 second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public T1 getSecond() {
        return second;
    }
}
