apply {
  from("$rootDir/base-module.gradle")
}

dependencies {
  "implementation"(project(Modules.coreDomain))
  "implementation"(project(Modules.corePresentation))
  "implementation"(project(Modules.ordersDomain))
  
  "implementation"(Google.gson)
  
  "implementation"(AndroidX.swipeToRefresh)
  
  "implementation"(Glide.glide)
  "annotationProcessor"(Glide.glideAnnotation)
  
  "implementation"(Shimmer.shimmer)
}