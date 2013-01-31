grails.project.class.dir = "target/classes"
grails.project.test.class.dir = "target/test-classes"
grails.project.test.reports.dir = "target/test-reports"

//location of the release repository
grails.project.repos.metridocInternalRepo.url = "svn:http://svn2.library.upenn.int/svn/dev/correctFormat/metridoc/plugins/maven/repository/"
//name of the repository
grails.project.repos.default = "metridocInternalRepo"

grails.project.dependency.resolution = {
    // inherit Grails' default dependencies
    inherits("global") {
        // uncomment to disable ehcache
        // excludes 'ehcache'
    }
    log "warn" // log level of Ivy resolver, either 'error', 'warn', 'info', 'debug' or 'verbose'
    repositories {
        grailsCentral()
        // uncomment the below to enable remote dependency resolution
        // from public Maven repositories
        //mavenLocal()
        mavenCentral()

        //mavenRepo "http://snapshots.repository.codehaus.org"
        //mavenRepo "http://repository.codehaus.org"
        //mavenRepo "http://download.java.net/maven/2/"
        mavenRepo "http://metridoc.googlecode.com/svn/trunk/maven/repository/"
    }
    dependencies {
        //get this to play nice with intellij, not actually needed
        build("org.tmatesoft.svnkit:svnkit:1.3.5") {
            excludes "jna", "trilead-ssh2", "sqljet"
        }
        build("com.google.code.maven-svn-wagon:maven-svn-wagon:1.4")
    }

    plugins {
        compile(":metridoc-core:0.53-SNAPSHOT")
        build ":release:$grailsVersion"
        build(":tomcat:$grailsVersion") {
            export = false
        }

    }
}
