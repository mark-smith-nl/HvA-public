package hva.ads.list;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * This method <description of functionality>
 *
 * @author m.smith
 */
public class ArrayListInspector {

    public static void main(String[] args) {
        System.out.println("Creating ArrayList....");
        ArrayList<String> list = new ArrayList<>();
        printFieldInfo("elementData", list);
        System.out.println("Adding elements....");
        for (int i = 0; i<= 30; i++) {
            list.add("V" + i);
            printFieldInfo("elementData", list);
        }

        System.out.println();
        System.out.println("Remove first element....");
        System.out.println();
        list.remove(0);
        printFieldInfo("elementData", list);

    }

    private static void printFieldInfo(String fieldName, Object instance) {
        try {
            Class<?> clazz = instance.getClass();
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            Class<?> type = field.getType();
            Object value = field.get(instance);
            if (type.isArray()) {
                Class<?> componentType = type.getComponentType();
                System.out.print(field.get(instance).toString().split("@")[1] + " " + fieldName + " ===> " + componentType.getSimpleName() + "[" + Array.getLength(value) + "]\t");
                Object[] objects = (Object[]) value;

                for (int i = 0; i < objects.length; i++) {
                    Object o = objects[i];
                    System.out.printf("%4s", o == null ? "-" : o);
                }
                System.out.println();
            }
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
