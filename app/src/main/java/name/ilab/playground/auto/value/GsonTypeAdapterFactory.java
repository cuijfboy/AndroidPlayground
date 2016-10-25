package name.ilab.playground.auto.value;

import com.google.gson.TypeAdapterFactory;

/**
 * Created by cuijfboy on 16/9/5.
 */
//@com.ryanharter.auto.value.gson.GsonTypeAdapterFactory
public abstract class GsonTypeAdapterFactory implements TypeAdapterFactory {

    public static TypeAdapterFactory create() {
        return null;//new AutoValueGson_GsonTypeAdapterFactory();
    }
}
