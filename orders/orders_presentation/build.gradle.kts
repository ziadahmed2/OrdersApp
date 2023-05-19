apply {
    from("$rootDir/base-module.gradle")
}

dependencies {
    "implementation"(project(Modules.coreDomain))
    "implementation"(project(Modules.ordersDomain))
    
    "implementation"(Google.gson)
}