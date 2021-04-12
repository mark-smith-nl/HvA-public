package hva.ads.college.week08.functionalinterface;

import java.util.List;

@FunctionalInterface
public interface ChooseOrGetDefaultValue<T> {

    T getValue(T defaultValue, List<T> v1);

 //   T apply(T value);
}
