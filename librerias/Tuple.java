package librerias;

import java.util.Objects;

public class Tuple<F extends Comparable<F>, S extends Comparable<S>>
        implements Comparable<Tuple<F, S>> {
    public F first;
    public S second;

    public Tuple(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return this.first;
    }

    public S getSecond() {
        return this.second;
    }

    public void setFirst(F replaceFirst) {
        this.first = replaceFirst;
    }

    public void setSecond(S replaceSecond) {
        this.second = replaceSecond;
    }

    @Override
    public String toString() {
        return "(" + first + "," + second + ")";
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null)
            return false;
        if (getClass() != object.getClass())
            return false;
        Tuple<?, ?> other = (Tuple<?, ?>) object;


        if (getFirst().getClass() != other.getFirst().getClass()
                || getSecond().getClass() != other.getSecond().getClass())
            return false;

        if (!this.getFirst().equals(other.getFirst()) || !this.getSecond().equals(other.getSecond()))
            return false;

        return true;
    }

    // @Override
    // public int hashCode() {
    //     final int prime = 31;
    //     int result = 1;
    //     result = prime * result + ((first == null) ? 0 : first.hashCode());
    //     result = prime * result + ((second == null) ? 0 : second.hashCode());
    //     return result;
    // }


    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }


    @Override
    public int compareTo(Tuple<F, S> other) {
        int d = this.first.compareTo(other.first);
        if (d == 0)
          return this.second.compareTo(other.second);
        return d;
      }
}