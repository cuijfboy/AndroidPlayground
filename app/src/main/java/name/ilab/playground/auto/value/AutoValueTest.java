package name.ilab.playground.auto.value;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;

import java.util.List;
import java.util.Set;

/**
 * Created by cuijfboy on 16/9/5.
 */
@AutoValue
public abstract class AutoValueTest<T> {

    @SerializedName("userName")
    public abstract String getUserName();

    public abstract String userPassword();

    public abstract int getUserAge();

    public abstract List<String> getStringList();

    public abstract Set<T> getSet();

    // ------------------------------------------------------------------------

    public static AutoValueTest create(String name, String password, int age) {
        return new AutoValue_AutoValueTest<>(name, password, age, null, null);
    }

    public static <T> TypeAdapter<AutoValueTest<T>> typeAdapter(
            Gson gson, TypeToken<? extends AutoValueTest<T>> typeToken) {
        return new AutoValue_AutoValueTest.GsonTypeAdapter(gson, typeToken);
    }
}
