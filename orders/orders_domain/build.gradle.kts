apply {
  from("$rootDir/base-module.gradle")
}

dependencies {
  "implementation"(project(Modules.coreDomain))
  "implementation"(Coroutines.coroutines)
  
  "testImplementation"("junit:junit:4.13.2")
  "testImplementation"("org.mockito:mockito-core:4.0.0")
  "testImplementation"("android.arch.core:core-testing:1.1.1")
  "testImplementation"("com.google.truth:truth:1.1.3")
  "testImplementation"("com.google.dagger:hilt-android-testing:2.45")
  "androidTestImplementation"("com.google.dagger:hilt-android-testing:2.45")
  "kaptTest"("com.google.dagger:hilt-android-compiler:2.45")
}