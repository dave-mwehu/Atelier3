package dao;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public final class RuntimeTypeAdapterFactory<T> implements TypeAdapterFactory {
    private final Class<?> baseType;
    private final String typeFieldName;
    private final Map<String, Class<?>> labelToSubtype = new LinkedHashMap<>();
    private final Map<Class<?>, String> subtypeToLabel = new LinkedHashMap<>();

    private RuntimeTypeAdapterFactory(Class<?> baseType, String typeFieldName) {
        this.baseType = baseType;
        this.typeFieldName = typeFieldName;
    }

    public static <T> RuntimeTypeAdapterFactory<T> of(Class<T> baseType, String typeFieldName) {
        return new RuntimeTypeAdapterFactory<>(baseType, typeFieldName);
    }

    public RuntimeTypeAdapterFactory<T> registerSubtype(Class<? extends T> subtype, String label) {
        if (labelToSubtype.containsKey(label)) {
            throw new IllegalArgumentException("Label already registered: " + label);
        }
        labelToSubtype.put(label, subtype);
        subtypeToLabel.put(subtype, label);
        return this;
    }

    @Override
    @SuppressWarnings("unchecked")
    public <R> TypeAdapter<R> create(Gson gson, TypeToken<R> type) {
        if (!baseType.equals(type.getRawType())) {
            return null;
        }

        final Map<String, TypeAdapter<?>> labelToAdapter = new LinkedHashMap<>();
        final Map<Class<?>, TypeAdapter<?>> subtypeToAdapter = new LinkedHashMap<>();

        for (Map.Entry<String, Class<?>> entry : labelToSubtype.entrySet()) {
            TypeAdapter<?> delegate = gson.getDelegateAdapter(this, TypeToken.get(entry.getValue()));
            labelToAdapter.put(entry.getKey(), delegate);
            subtypeToAdapter.put(entry.getValue(), delegate);
        }

        return new TypeAdapter<R>() {
            @Override
            public void write(JsonWriter out, R value) throws IOException {
                Class<?> srcType = value.getClass();
                String label = subtypeToLabel.get(srcType);
                TypeAdapter<R> delegate = (TypeAdapter<R>) subtypeToAdapter.get(srcType);
                JsonObject jsonObj = delegate.toJsonTree(value).getAsJsonObject();
                jsonObj.addProperty(typeFieldName, label);
                gson.toJson(jsonObj, out);
            }

            @Override
            public R read(JsonReader in) throws IOException {
                JsonObject jsonObj = gson.fromJson(in, JsonObject.class);
                JsonElement labelJson = jsonObj.get(typeFieldName);
                if (labelJson == null) {
                    throw new JsonParseException("Missing type field '" + typeFieldName + "'");
                }
                String label = labelJson.getAsString();
                TypeAdapter<R> delegate = (TypeAdapter<R>) labelToAdapter.get(label);
                if (delegate == null) {
                    throw new JsonParseException("Unknown type label: " + label);
                }
                jsonObj.remove(typeFieldName);
                return delegate.fromJsonTree(jsonObj);
            }
        };
    }
}
