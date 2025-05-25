dependencies {
    // Core e extensões Kotlin
    implementation "androidx.core:core-ktx:1.13.1" // Verifique se há 1.14.x ou superior
    implementation "androidx.appcompat:appcompat:1.7.0" // Verifique se há 1.8.x

    // Material Design
    implementation "com.google.android.material:material:1.12.0" // Verifique se há 1.13.x ou 1.14.x

    // ConstraintLayout
    implementation "androidx.constraintlayout:constraintlayout:2.1.4" // Verifique se há 2.2.x

    // Navigation
    implementation "androidx.navigation:navigation-fragment-ktx:2.8.3" // Verifique se há 2.9.x ou 3.x
    implementation "androidx.navigation:navigation-ui-ktx:2.8.3" // Mesma versão

    // Lifecycle
    implementation "androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.6"
    implementation "androidx.lifecycle:lifecycle-livedata-ktx:2.8.6"

    // Adicionando SharedPreferences (já incluído no core, mas explicitando para clareza)
    implementation "androidx.preference:preference-ktx:1.2.1" // Para gerenciar configurações do jogo

    // Glide para carregar imagens (ex.: imagens dos casos em res/drawable)
    implementation "com.github.bumptech.glide:glide:4.16.0"
    annotationProcessor "com.github.bumptech.glide:compiler:4.16.0"

    // Room para persistência (opcional, se usar banco de dados)
    implementation "androidx.room:room-ktx:2.6.1"
    ksp "androidx.room:room-compiler:2.6.1"

    // Testes
    testImplementation "junit:junit:4.13.2"
    androidTestImplementation "androidx.test.ext:junit:1.2.1"
    androidTestImplementation "androidx.test.espresso:espresso-core:3.6.1"
}