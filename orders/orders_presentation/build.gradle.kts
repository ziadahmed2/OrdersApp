apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.ordersDomain))
    
    "implementation"(Google.gson)
}