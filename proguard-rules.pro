# Adicione regras específicas do projeto aqui.

# Mantém classes e membros usados por reflexão em Jetpack Compose
-keep class androidx.compose.** { *; }
-dontwarn androidx.compose.**

# Mantém classes e membros do Room
-keep class androidx.room.** { *; }
-dontwarn androidx.room.**

# Mantém classes do Navigation
-keep class androidx.navigation.** { *; }
-dontwarn androidx.navigation.**

# Mantém classes do ViewModel
-keep class androidx.lifecycle.** { *; }
-dontwarn androidx.lifecycle.**

# Mantém classes do Kotlin Coroutines
-keep class kotlinx.coroutines.** { *; }
-dontwarn kotlinx.coroutines.**

# Mantém classes de modelos de dados (ex.: GameState, Case, etc.) para evitar ofuscação
-keep class Supremo.Tribunal.Popula.model.** { *; }

# Mantém classes do banco de dados Room (GameDatabase, GameDao)
-keep class Supremo.Tribunal.Popula.data.** { *; }

# Evita remover métodos e classes usados por reflexão no ViewModel
-keep class Supremo.Tribunal.Popula.viewmodel.** { *; }

# Mantém recursos do Android (ex.: R.drawable, R.string)
-keep class Supremo.Tribunal.Popula.R { *; }
-keep class Supremo.Tribunal.Popula.R$* { *; }

# Evita avisos para bibliotecas AndroidX
-dontwarn androidx.**

# Regras gerais para Kotlin
-keep class kotlin.** { *; }
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**
-keepclassmembers class **$WhenMappings {
    <fields>;
}
-keepclassmembers class kotlin.Metadata {
    public <methods>;
}

# Evita ofuscar métodos nativos
-keepclasseswithmembernames class * {
    native <methods>;
}

# Mantém classes serializáveis
-keepnames class * implements java.io.Serializable
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    !static !transient <fields>;
    !private <fields>;
    !private <methods>;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

# Evita otimizar/remover métodos de inicialização
-keepclassmembers class * {
    void *(**On*Event);
}

# Mantém enumerações
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

# Regras para evitar problemas com JSON (caso use Gson ou Moshi no futuro)
-keepattributes Signature
-keepattributes *Annotation*
-keep class * extends com.google.gson.TypeAdapter
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer